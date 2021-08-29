package com.broadview.coupon.user.service.intf;

import com.broadview.coupon.user.entity.Coupon;
import com.broadview.coupon.user.pojo.RequestCoupon;
import com.broadview.coupon.shared.beans.CouponInfo;
import com.broadview.coupon.shared.beans.PlaceOrder;

import java.util.List;

/**
 * 用户对接服务
 */
public interface CouponUserService {

    /**
     * 查询用户优惠券
     */
    List<CouponInfo> findCoupon(Long userId, Integer status, Long shopId);

    /**
     * 领券接口
     */
    Coupon requestCoupon(RequestCoupon request);

    /**
     * <h2>结算(核销)优惠券</h2>
     * @param info {@link PlaceOrder}
     * @return {@link PlaceOrder}
     * */
    PlaceOrder placeOrder(PlaceOrder info);
}
