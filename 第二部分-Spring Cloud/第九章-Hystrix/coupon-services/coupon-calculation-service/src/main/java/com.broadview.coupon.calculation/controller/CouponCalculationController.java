package com.broadview.coupon.calculation.controller;

import com.alibaba.fastjson.JSON;
//import com.broadview.coupon.calculation.processor.ExecuteManager;
import com.broadview.coupon.calculation.processor.CouponProcessorFactory;
import com.broadview.coupon.calculation.processor.RuleProcessor;
import com.broadview.coupon.shared.beans.PlaceOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("calculator")
public class CouponCalculationController {

    @Autowired
    private CouponProcessorFactory couponProcessorFactory;

    // 优惠券结算
    @PostMapping("/checkout")
    @ResponseBody
    public PlaceOrder computeRule(@RequestBody PlaceOrder settlement) {
        log.info("do calculation: {}", JSON.toJSONString(settlement));
        RuleProcessor ruleProcessor = couponProcessorFactory.getRuleProcessor(settlement);
        return ruleProcessor.calculate(settlement);
    }
}
