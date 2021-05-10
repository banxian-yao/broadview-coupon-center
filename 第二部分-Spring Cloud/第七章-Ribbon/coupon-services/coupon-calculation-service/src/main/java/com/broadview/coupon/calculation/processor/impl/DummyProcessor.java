package com.broadview.coupon.calculation.processor.impl;

import com.broadview.coupon.calculation.processor.AbstractRuleProcessor;
import com.broadview.coupon.calculation.processor.RuleProcessor;
import com.broadview.coupon.shared.beans.PlaceOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 空实现
 */
@Slf4j
@Component
public class DummyProcessor extends AbstractRuleProcessor implements RuleProcessor {

    @Override
    public PlaceOrder calculate(PlaceOrder order) {
        Double totalAmount = goodsCostSum(order.getProducts());
        order.setCost(totalAmount);
        return order;
    }
}
