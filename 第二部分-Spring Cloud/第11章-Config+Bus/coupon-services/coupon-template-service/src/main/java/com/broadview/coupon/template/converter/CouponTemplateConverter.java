package com.broadview.coupon.template.converter;

import com.broadview.coupon.shared.beans.TemplateInfo;
import com.broadview.coupon.shared.enums.CouponType;
import com.broadview.coupon.template.beans.TemplateRequest;
import com.broadview.coupon.template.entity.CouponTemplate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

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
}
