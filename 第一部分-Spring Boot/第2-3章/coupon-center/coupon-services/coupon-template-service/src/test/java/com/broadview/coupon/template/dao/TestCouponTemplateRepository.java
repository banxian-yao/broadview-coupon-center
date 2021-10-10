package com.broadview.coupon.template.dao;

import com.broadview.coupon.shared.enums.CouponType;
import com.broadview.coupon.template.entity.CouponTemplateEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestCouponTemplateRepository {



    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CouponTemplateRepository couponTemplateRepository;

    @Test
    public void testQuery(){
        CouponTemplateEntity t1=new CouponTemplateEntity();
        t1.setName("Test Coupon");
        t1.setCategory(CouponType.DISCOUNT);
        t1.setAvailable(true);
        t1.setDescription("Test coupon");
        t1.setExpired(false);
        t1.setTotal(123);
        entityManager.persistAndFlush(t1);

        CouponTemplateEntity t2=couponTemplateRepository.findById(t1.getId()).get();
        assertEquals(t1,t2);
    }
}
