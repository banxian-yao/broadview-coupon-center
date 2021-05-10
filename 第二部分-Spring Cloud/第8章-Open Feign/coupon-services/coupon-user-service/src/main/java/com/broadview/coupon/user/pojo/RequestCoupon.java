package com.broadview.coupon.user.pojo;

import com.broadview.coupon.shared.beans.TemplateInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestCoupon {

    // 用户领券
    @NotNull
    private Long userId;

    @NotNull
    private Long couponTemplateId;

    /** 优惠券模板信息 */
    private TemplateInfo templateSDK;
}
