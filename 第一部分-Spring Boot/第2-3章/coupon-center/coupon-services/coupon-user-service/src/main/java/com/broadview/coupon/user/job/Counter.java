package com.broadview.coupon.user.job;


import lombok.AllArgsConstructor;
import lombok.Data;


import java.util.concurrent.atomic.AtomicInteger;

@Data
@AllArgsConstructor
public class Counter {


    private Long couponId;

    private AtomicInteger count;
}
