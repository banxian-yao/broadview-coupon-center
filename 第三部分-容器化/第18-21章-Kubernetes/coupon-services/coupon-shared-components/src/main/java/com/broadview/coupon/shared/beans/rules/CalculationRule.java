package com.broadview.coupon.shared.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 优惠券计算规则
 * @author 姚半仙
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculationRule {

    /** 折扣 */
    private Discount discount;

    // 每个人领券数量
    private Integer limitation;

    private Long deadline;

}
