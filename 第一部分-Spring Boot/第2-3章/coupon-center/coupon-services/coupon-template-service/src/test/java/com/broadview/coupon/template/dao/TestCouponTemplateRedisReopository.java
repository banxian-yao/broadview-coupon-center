package com.broadview.coupon.template.dao;

import com.broadview.coupon.template.entity.CouponTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestCouponTemplateRedisReopository {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CouponTemplateRedisReopository couponTemplateRedisReopository;

    @Test
    public void testQuery(){
        Long id=999L;
        CouponTemplate t1=new CouponTemplate();
        t1.setName("Test Coupon");
        t1.setId(id);
        couponTemplateRedisReopository.save(t1);
        CouponTemplate t2=couponTemplateRedisReopository.findById(id).get();
        assertEquals(t1,t2);
    }
}
