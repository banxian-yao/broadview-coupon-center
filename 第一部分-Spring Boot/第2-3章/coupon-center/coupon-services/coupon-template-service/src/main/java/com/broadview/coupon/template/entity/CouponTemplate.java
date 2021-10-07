package com.broadview.coupon.template.entity;

import com.broadview.coupon.shared.beans.rules.CalculationRule;
import com.broadview.coupon.shared.enums.CouponType;
import com.broadview.coupon.template.converter.CouponTypeConverter;
import com.broadview.coupon.template.converter.RuleConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
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
