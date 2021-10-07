package com.broadview.coupon.user.convertor;

import com.broadview.coupon.user.entity.Coupon;
import com.broadview.coupon.shared.beans.CouponInfo;

public class CouponConverter {

    public static CouponInfo convertToCoupon(Coupon coupon) {

        return CouponInfo.builder()
                .id(coupon.getId())
                .templateId(coupon.getTemplateId())
                .status(coupon.getStatus().getCode())
                .userId(coupon.getUserId())
                .shopId(coupon.getShopId())
                .template(coupon.getTemplateInfo())
                .build();
    }
}
