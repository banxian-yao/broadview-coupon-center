package com.broadview.coupon.template.controller;

import com.alibaba.fastjson.JSON;
import com.broadview.coupon.shared.beans.TemplateInfo;
import com.broadview.coupon.template.beans.TemplateRequest;
import com.broadview.coupon.template.entity.CouponTemplate;
import com.broadview.coupon.template.service.intf.CouponTemplateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/template")
public class CouponTemplateController {

    @Autowired
    private CouponTemplateService couponTemplateService;

    /**
     * 创建优惠券
     *
     * localhost:20000/template/add
     */
    @PostMapping("/add")
    @ResponseBody
    public CouponTemplate addTemplate(@Valid @RequestBody TemplateRequest request) {
        log.info("Create coupon template: data={}", request);
        return couponTemplateService.createTemplate(request);
    }

    /**
     * 读取优惠券
     */
    @GetMapping("/get")
    @ResponseBody
    public TemplateInfo getTemplate(@RequestParam("id") Long id){
        log.info("Load template, id={}", id);
        return couponTemplateService.loadTemplateInfo(id);
    }

    /**
     * 批量获取
     */
    @GetMapping("/getBatch")
    @ResponseBody
    public Map<Long, TemplateInfo> getTemplateInBatch(@RequestParam("ids") Collection<Long> ids) {
        log.info("getTemplateInBatch: {}", JSON.toJSONString(ids));
        return couponTemplateService.getTemplateInfoMap(ids);
    }

    /**
     * 搜索模板
     *
     * 可以根据name，shop id，available三样搜索
     */
    @PostMapping("/search")
    public List<TemplateInfo> searchTemplate(@RequestBody TemplateRequest request) {
        log.info("search templates, payload={}", request);
        return couponTemplateService.searchTemplate(request);
    }

    // 优惠券无效化
    @PutMapping("/inactive")
    public void inactiveCoupon(@RequestParam("id") Long id){
        log.info("Load template, id={}", id);
        couponTemplateService.inactiveCoupon(id);
    }
}
