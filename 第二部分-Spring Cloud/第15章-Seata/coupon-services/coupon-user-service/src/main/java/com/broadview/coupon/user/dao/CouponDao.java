package com.broadview.coupon.user.dao;

import com.broadview.coupon.user.CouponStatus;
import com.broadview.coupon.user.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponDao extends JpaRepository<Coupon, Long> {

    long countByUserIdAndTemplateId(Long userId, Long templateId);

    @Modifying
    @Query("update Coupon c set c.status = :status where c.templateId = :id")
    int makeCouponUnavailable(@Param("id") Long id, @Param("status") CouponStatus status);
}
