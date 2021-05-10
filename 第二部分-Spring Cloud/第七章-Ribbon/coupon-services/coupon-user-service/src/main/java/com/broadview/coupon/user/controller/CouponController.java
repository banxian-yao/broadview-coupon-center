package com.broadview.coupon.user.controller;

import com.broadview.coupon.user.entity.Coupon;
import com.broadview.coupon.user.pojo.RequestCoupon;
import com.broadview.coupon.user.service.intf.CouponUserService;
import com.broadview.coupon.shared.beans.CouponInfo;
import com.broadview.coupon.shared.beans.PlaceOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("coupon-user")
public class CouponController {

    @Autowired
    private CouponUserService couponUserService;

    @GetMapping("findCoupon")
    public List<CouponInfo> findCoupon(@RequestParam("userId") Long userId,
                                       @RequestParam(value = "status", required = false) Integer status,
                                       @RequestParam(value = "shopId", required = false) Long shopId) {
        return couponUserService.findCoupon(userId, status, shopId);
    }

    @PostMapping("requestCoupon")
    public Coupon requestCoupon(@Valid @RequestBody RequestCoupon request) {
        return couponUserService.requestCoupon(request);
    }

    @PostMapping("placeOrder")
    public PlaceOrder checkout(@Valid @RequestBody PlaceOrder info) {
        return couponUserService.placeOrder(info);
    }

}
