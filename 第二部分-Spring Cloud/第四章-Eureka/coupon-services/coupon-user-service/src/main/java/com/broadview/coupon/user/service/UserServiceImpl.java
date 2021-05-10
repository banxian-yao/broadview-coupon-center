package com.broadview.coupon.user.service;

import com.broadview.coupon.user.CouponStatus;
import com.broadview.coupon.user.convertor.CouponConverter;
import com.broadview.coupon.user.entity.Coupon;
import com.broadview.coupon.user.dao.CouponDao;
import com.broadview.coupon.user.external.CalculationClient;
import com.broadview.coupon.user.external.TemplateClient;
import com.broadview.coupon.user.pojo.RequestCoupon;
import com.broadview.coupon.user.service.intf.CouponUserService;
import com.broadview.coupon.shared.beans.CouponInfo;
import com.broadview.coupon.shared.beans.TemplateInfo;
import com.broadview.coupon.shared.beans.PlaceOrder;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Example;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl implements CouponUserService {

    @Autowired
    private CouponDao couponDao;

    @Autowired
    private TemplateClient templateClient;

    @Autowired
    private CalculationClient calculationClient;

    @Autowired
    private LoadBalancerClient client;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 用户查询优惠券的接口
     */
    @Override
    public List<CouponInfo> findCoupon(Long userId, Integer status, Long shopId) {
        Coupon example = Coupon.builder()
                .userId(userId)
                .status(CouponStatus.convert(status))
                .shopId(shopId)
                .build();
        List<Coupon> coupons = couponDao.findAll(Example.of(example));
        if (coupons.isEmpty()) {
            return Lists.newArrayList();
        }

        List<Long> templateIds = coupons.stream()
                .map(Coupon::getTemplateId)
                .collect(Collectors.toList());

        // 将templateIds输出为一串逗号分隔的字符串
        String ids = templateIds.stream().map(String::valueOf).collect(Collectors.joining(","));

        // 获取访问地址，设置header参数
        String target = getUrl("coupon-template-service", "template/getBatch");
        HttpEntity requestEntity = buildHttpEntity();

        // 设置返回值类型
        ParameterizedTypeReference<Map<Long, TemplateInfo>> responseType =
                new ParameterizedTypeReference<Map<Long, TemplateInfo>>() {};

        // 发起远程调用
        ResponseEntity<Map<Long, TemplateInfo>> exchange =
                restTemplate.exchange(target+"?ids="+ids, HttpMethod.GET, requestEntity, responseType);

        Map<Long, TemplateInfo> templateMap = exchange.getBody();
        coupons.stream().forEach(e -> e.setTemplateInfo(templateMap.get(e.getTemplateId())));

        return coupons.stream()
                .map(CouponConverter::convertToCoupon)
                .collect(Collectors.toList());
    }

    /**
     * 用户领取优惠券
     */
    @Override
    public Coupon requestCoupon(RequestCoupon request) {
        // 将查询参数拼到URL中
        String append = "/template/get?id="+request.getCouponTemplateId();
        String target = getUrl("coupon-template-service", append);
        // 使用Get方法发起访问
        TemplateInfo templateInfo = restTemplate.getForObject(target, TemplateInfo.class);

        // 模板不存在则报错
        if (templateInfo == null) {
            log.error("invalid template id={}", request.getCouponTemplateId());
            throw new IllegalArgumentException("Invalid template id");
        }

        // 模板不能过期
        long now = Calendar.getInstance().getTimeInMillis();
        Long expTime = templateInfo.getRule().getDeadline();
        if (expTime != null && now >= expTime || BooleanUtils.isFalse(templateInfo.getAvailable())) {
            log.error("template is not available id={}", request.getCouponTemplateId());
            throw new IllegalArgumentException("template is unavailable");
        }

        // 用户领券数量超过上限
        Long count = couponDao.countByUserIdAndTemplateId(request.getUserId(), request.getCouponTemplateId());
        if (count >= templateInfo.getRule().getLimitation()) {
            log.error("exceeds maximum number");
            throw new IllegalArgumentException("exceeds maximum number");
        }

        Coupon coupon = Coupon.builder()
                .templateId(request.getCouponTemplateId())
                .userId(request.getUserId())
                .shopId(templateInfo.getShopId())
                .status(CouponStatus.AVAILABLE)
                .build();
        couponDao.save(coupon);
        return coupon;
    }

    @Override
    public PlaceOrder placeOrder(PlaceOrder order) {
        if (CollectionUtils.isEmpty(order.getProducts())) {
            throw new IllegalArgumentException("empty cart");
        }

        Coupon coupon = null;
        if (order.getCouponId() != null) {
            // 如果有优惠券，验证是否可用，并且是当前客户的
            Coupon example = Coupon.builder()
                    .userId(order.getUserId())
                    .id(order.getCouponId())
                    .status(CouponStatus.AVAILABLE)
                    .build();
            coupon = couponDao.findAll(Example.of(example))
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Coupon not found"));

            CouponInfo couponInfo = CouponConverter.convertToCoupon(coupon);

            // 构造Map参数
            Map<String, String> param = new HashMap();
            param.put("id", String.valueOf(coupon.getTemplateId()));

            String target = getUrl("coupon-template-service", "/template/get?id={id}");
            // 使用Get方法发起访问
            // target中的占位符{id}，将被RestTemplate替换为Map里面的id参数
            TemplateInfo templateInfo = restTemplate.getForObject(target, TemplateInfo.class, param);

            couponInfo.setTemplate(templateInfo);
//            couponInfo.setTemplate(templateClient.getTemplate(coupon.getTemplateId()));
            order.setCouponInfos(Lists.newArrayList(couponInfo));
        }

        // order清算
//        PlaceOrder checkoutInfo = calculationClient.computeRule(order);

        // 将查询参数拼到URL中
        String target = getUrl("coupon-calculator-service", "/calculator/checkout");
        // 使用Get方法发起访问
        PlaceOrder checkoutInfo = restTemplate.postForObject(target, order, PlaceOrder.class);

        // 如果清算结果里没有优惠券，而用户传递了优惠券，报错提示该订单满足不了优惠条件
        if (CollectionUtils.isEmpty(checkoutInfo.getCouponInfos()) && coupon != null) {
            log.error("cannot apply coupponId={} to order", coupon.getId());
            throw new IllegalArgumentException("Not an eligible coupon");
        }

        if (coupon != null) {
            coupon.setStatus(CouponStatus.USED);
            couponDao.save(coupon);
        }
        return checkoutInfo;
    }

    // 拼接访问地址
    private String getUrl(String serviceId, String uri) {
        // 利用Eureka的服务发现机制，从当前可用的服务实例中选择一个
        ServiceInstance instance = client.choose(serviceId);
        // 拼接访问参数
        String target = String.format("http://%s:%s/%s",
                instance.getHost(),
                instance.getPort(),
                uri);
        log.info("uri is {}", target);
        return target;
    }

    private HttpEntity buildHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //设置接收返回值的格式为json
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        headers.setAccept(mediaTypeList);

        HttpEntity requestEntity = new HttpEntity(null, headers);
        return requestEntity;
    }

}
