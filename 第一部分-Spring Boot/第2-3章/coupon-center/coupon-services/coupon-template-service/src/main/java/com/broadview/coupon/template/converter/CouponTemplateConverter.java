package com.broadview.coupon.template.converter;

import com.broadview.coupon.shared.beans.TemplateInfo;
import com.broadview.coupon.template.entity.CouponTemplate;
import com.broadview.coupon.template.entity.CouponTemplateEntity;

public class CouponTemplateConverter {

    public static TemplateInfo convertToTemplateInfo(CouponTemplate template) {

        return TemplateInfo.builder()
                .id(template.getId())
                .name(template.getName())
                .description(template.getDescription())
                .type(template.getCategory().getCode())
                .shopId(template.getShopId())
                .available(template.getAvailable())
                .rule(template.getRule())
                .build();
    }

    public static TemplateInfo convertToTemplateInfo(CouponTemplateEntity template) {

        return TemplateInfo.builder()
                .id(template.getId())
                .name(template.getName())
                .description(template.getDescription())
                .type(template.getCategory().getCode())
                .shopId(template.getShopId())
                .available(template.getAvailable())
                .rule(template.getRule())
                .build();
    }

    public static CouponTemplate convertToTemplate(CouponTemplateEntity template) {

        return CouponTemplate.builder()
                .id(template.getId())
                .name(template.getName())
                .description(template.getDescription())
                .category(template.getCategory())
                .shopId(template.getShopId())
                .available(template.getAvailable())
                .rule(template.getRule())
                .build();
    }
}
