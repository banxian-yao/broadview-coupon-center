package com.broadview.coupon.user.mq;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface RequestCouponQueue {

    String INPUT = "coupon-consumer";

    String OUTPUT = "coupon-producer";

    @Input(INPUT)
    public SubscribableChannel input();

    @Output(OUTPUT)
    public MessageChannel output();
}
