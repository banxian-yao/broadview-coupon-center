package com.broadview.coupon.template.dao;

import com.broadview.coupon.template.entity.CouponTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CouponTemplateRepository
        extends JpaRepository<CouponTemplateEntity, Long> {

    CouponTemplateEntity findByName(String name);

    List<CouponTemplateEntity> findAllByAvailable(Boolean available);


    List<CouponTemplateEntity> findAllByAvailableAndExpired(
            Boolean available, Boolean expired
    );

    /**
     * <h2>根据 expired 标记查找模板记录</h2>
     * where expired = ...
     * */
    List<CouponTemplateEntity> findAllByExpired(Boolean expired);

    /**
     * 根据shop ID + 可用状态查询店铺有多少券模板
     */
    Integer countByShopIdAndAvailable(Long shopId, Boolean available);

    /**
     * 将优惠券设置为不可用
     */
    @Modifying
    @Query("update CouponTemplateEntity c set c.available = 0 where c.id = :id")
    int makeCouponUnavailable(@Param("id") Long id);
}
