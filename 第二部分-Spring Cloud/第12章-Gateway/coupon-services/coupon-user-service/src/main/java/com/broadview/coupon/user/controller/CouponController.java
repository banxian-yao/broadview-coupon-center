package com.broadview.coupon.user.controller;

import com.broadview.coupon.user.entity.Coupon;
import com.broadview.coupon.user.pojo.RequestCoupon;
import com.broadview.coupon.user.service.intf.CouponUserService;
import com.broadview.coupon.shared.beans.CouponInfo;
import com.broadview.coupon.shared.beans.PlaceOrder;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
