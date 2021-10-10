package com.broadview.coupon.template.entity;

import com.broadview.coupon.shared.beans.rules.CalculationRule;
import com.broadview.coupon.shared.enums.CouponType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;

/**
 * 优惠券模板
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@RedisHash("Student")
public class CouponTemplate implements Serializable {

    private Long id;

    private Boolean available;

    private Boolean expired;

    private String name;

    private Long shopId;

    private String description;

    private CouponType category;

    private Integer total;

    private Date createdTime;

    private CalculationRule rule;

}
