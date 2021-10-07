package com.broadview.coupon.user.job.batch;

import com.broadview.coupon.user.entity.Coupon;
import com.broadview.coupon.user.job.Counter;
import org.springframework.batch.item.ItemProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CouponCountProcessor implements ItemProcessor<Coupon, Counter> {
    private Map<Long,Counter> counterMap=new HashMap();

    @Override
    public Counter process(Coupon coupon) throws Exception {
        Long couponId=coupon.getId();
        Counter mCounter=counterMap.get(couponId);
        if(mCounter==null){
            Counter counter=new Counter(couponId,new AtomicInteger(0));
            counterMap.put(couponId,counter);
            return counter;
        }else {
            mCounter.getCount().addAndGet(1);
           return mCounter;
        }
    }
}
