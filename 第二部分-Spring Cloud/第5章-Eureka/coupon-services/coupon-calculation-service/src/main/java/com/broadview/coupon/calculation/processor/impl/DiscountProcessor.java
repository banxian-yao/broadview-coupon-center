package com.broadview.coupon.calculation.processor.impl;

import com.broadview.coupon.calculation.processor.AbstractRuleProcessor;
import com.broadview.coupon.calculation.processor.RuleProcessor;
import com.broadview.coupon.shared.beans.TemplateInfo;
import com.broadview.coupon.shared.beans.PlaceOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * 打折优惠券
 */
@Slf4j
@Component
public class DiscountProcessor extends AbstractRuleProcessor implements RuleProcessor {

    @Override
    public PlaceOrder calculate(PlaceOrder order) {
        Double totalAmount = goodsCostSum(order.getProducts());
        Map<Long, Double> sumAmount = this.sumProductCostIntoGroup(order.getProducts());

        TemplateInfo template = order.getCouponInfos().get(0).getTemplate();
        double base = (double) template.getRule().getDiscount().getBase();
        double quota = (double) template.getRule().getDiscount().getQuota();

        Long shopId = template.getShopId();
        // 如果是全店打折，基准价格是总价，否则是该门店商品的价格
        Double actualAmount = (shopId == null) ? totalAmount : sumAmount.get(shopId);

        // 如果不符合标准, 则直接返回商品总价
        if (actualAmount == null || actualAmount < base) {
            log.debug("Totals of amount not meet");
            order.setCost(totalAmount);
            order.setCouponInfos(Collections.emptyList());
            return order;
        }

        // 计算新的总价
        Double newCost = (totalAmount - quota) > minCost() ? (totalAmount - quota) : minCost();
        order.setCost(newCost);
        log.debug("original price={}, new price={}", totalAmount, newCost);

        // 计算使用优惠券之后的价格
        Double reducedAmount = convertToDecimal(actualAmount * (1 - quota/100));
        Double newPrice = totalAmount - reducedAmount > minCost() ?  totalAmount - reducedAmount : minCost();
        order.setCost(newPrice);

        return order;
    }
}
