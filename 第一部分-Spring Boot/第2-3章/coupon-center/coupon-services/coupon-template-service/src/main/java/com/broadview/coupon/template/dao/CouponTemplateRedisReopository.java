package com.broadview.coupon.template.dao;

import com.broadview.coupon.template.entity.CouponTemplate;
import org.springframework.data.repository.CrudRepository;

public interface CouponTemplateRedisReopository extends CrudRepository<CouponTemplate, Long> {

}
