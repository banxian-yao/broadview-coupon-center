package com.broadview.coupon.template.beans;

import com.broadview.coupon.shared.beans.rules.CalculationRule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * 创建优惠券模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateRequest {

    @NotNull
    private String name;

    // 优惠券描述
    @NotNull
    private String desc;

    // 优惠券类型
    @NotNull
    private String type;

    // 发券总数
    @NotNull
    private Integer total;

    // 适用门店 - 若无则为全店通用券
    private Long shopId;

    /** 优惠券规则 */
    @NotNull
    private CalculationRule rule;

    private Boolean available;

    private Boolean expired;

}
