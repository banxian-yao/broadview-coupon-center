package com.broadview.coupon.calculation.processor;


import com.broadview.coupon.shared.beans.PlaceOrder;

public interface RuleProcessor {

    // 计算优惠券
    PlaceOrder calculate(PlaceOrder settlement);
}
