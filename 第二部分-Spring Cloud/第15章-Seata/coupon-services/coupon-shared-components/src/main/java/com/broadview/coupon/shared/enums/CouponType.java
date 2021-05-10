package com.broadview.coupon.shared.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum CouponType {

    MONEY_OFF("满减券", "1"),
    DISCOUNT("打折", "2");

    private String description;

    // 存在数据库里的最终code
    private String code;

    public static CouponType of(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Coupon Type"));

    }
}
