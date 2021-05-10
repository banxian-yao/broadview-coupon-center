package com.broadview.coupon.user.mq;

import com.broadview.coupon.user.entity.Coupon;
import com.broadview.coupon.user.pojo.RequestCoupon;
import com.broadview.coupon.user.service.intf.CouponUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class CouponMessageConsumer {

    private AtomicInteger count = new AtomicInteger(1);

    @Autowired
    private CouponUserService couponUserService;

    @StreamListener(RequestCouponQueue.INPUT)
    public void consume(Message<RequestCoupon> message) throws InterruptedException {
        Coupon coupon = couponUserService.requestCoupon(message.getPayload());
        log.info("coupon info {}", coupon);
    }

    // 多次失败触发降级流程
    @ServiceActivator(inputChannel = "request-coupon-topic.coupon-user-serv-group.errors")
    public void fallback(Message<RequestCoupon> message) {
        log.info("fallback logic here");
        throw new RuntimeException("error");
    }
}
