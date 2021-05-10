package com.broadview.coupon.calculation.processor;

import com.broadview.coupon.shared.beans.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 定义通用的计算逻辑
 */
public abstract class AbstractRuleProcessor {

    // 计算总价
    protected double goodsCostSum(List<Product> products) {
        return products.stream()
                .mapToDouble(g -> g.getPrice() * g.getCount())
                .sum();
    }

    // 根据门店维度计算每个门店下商品价格
    // key = shopId
    // value = 门店商品总价
    protected Map<Long, Double> sumProductCostIntoGroup(List<Product> products) {
        Map<Long, Double> groups = products.stream()
                .collect(Collectors.groupingBy(m -> m.getShopId(),
                        Collectors.summingDouble(p -> p.getPrice() * p.getCount()))
                );
        return groups;
    }

    protected double convertToDecimal(double value) {
        return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    // 最小支付金额
    protected double minCost() {
        return 0.01;
    }
}
