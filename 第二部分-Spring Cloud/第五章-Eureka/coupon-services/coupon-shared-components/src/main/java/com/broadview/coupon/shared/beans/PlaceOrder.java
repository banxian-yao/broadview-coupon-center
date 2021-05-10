package com.broadview.coupon.shared.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 买单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrder {

    @NotEmpty
    private List<Product> products;

    private Long couponId;

    private double cost;

    // 目前只支持单张优惠券
    private List<CouponInfo> couponInfos;

    @NotNull
    private Long userId;

}
