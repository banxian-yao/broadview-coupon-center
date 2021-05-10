package com.broadview.coupon.user.controller;

import com.broadview.coupon.user.entity.Coupon;
import com.broadview.coupon.user.mq.RequestCouponQueue;
import com.broadview.coupon.user.pojo.RequestCoupon;
import com.broadview.coupon.user.service.intf.CouponUserService;
import com.broadview.coupon.shared.beans.CouponInfo;
import com.broadview.coupon.shared.beans.PlaceOrder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("coupon-user")
@EnableHystrix
@RefreshScope
public class CouponController {

    @Autowired
    private CouponUserService couponUserService;

    @Value("${request-coupon-disabled:false}")
    private boolean disableRequestingCoupon;

    @Autowired
    private RequestCouponQueue requestCouponQueue;

    @GetMapping("findCoupon")
    public List<CouponInfo> findCoupon(@RequestParam("userId") Long userId,
                                       @RequestParam(value = "status", required = false) Integer status,
                                       @RequestParam(value = "shopId", required = false) Long shopId) {
        return couponUserService.findCoupon(userId, status, shopId);
    }

    @PostMapping("requestCoupon")
    @HystrixCommand(fallbackMethod = "requestCouponFallback",
            commandKey = "requestCouponKey",
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            }
        )
    public Coupon requestCoupon(@Valid @RequestBody RequestCoupon request) throws InterruptedException {
        if (disableRequestingCoupon) {
            log.info("disable requesting coupon");
            return null;
        }
        log.info("request Coupon normal");
        return couponUserService.requestCoupon(request);
    }

    @PostMapping("requestCouponQueue")
    public String requestCouponQueue(@Valid @RequestBody RequestCoupon request) {
        log.info("ready to send message, {}", request);
        Message message = MessageBuilder
                .withPayload(request)
                .setHeader("x-delay", 3 * 1000)
                .build();
        log.info("message is {}", message);

        boolean success = requestCouponQueue.output().send(message);
        log.info("send requestCouponQueue success? {}", success);

        return success ? "请稍后到账户查询" : "发券失败";
    }

    // requestCoupon的降级流程，返回一个空Coupon
    public Coupon requestCouponFallback(RequestCoupon request) {
        log.info("requestCoupon fallback");
        return Coupon.builder().build();
    }

    @PostMapping("placeOrder")
    public PlaceOrder checkout(@Valid @RequestBody PlaceOrder info) {
        return couponUserService.placeOrder(info);
    }

}
