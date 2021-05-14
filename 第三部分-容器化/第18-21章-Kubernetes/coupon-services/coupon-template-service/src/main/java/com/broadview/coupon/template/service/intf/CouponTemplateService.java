package com.broadview.coupon.template.service.intf;


import com.broadview.coupon.shared.beans.TemplateInfo;
import com.broadview.coupon.template.beans.TemplateRequest;
import com.broadview.coupon.template.entity.CouponTemplate;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CouponTemplateService {

    /**
     * 创建优惠券模板 - 每个商户只能创建100个
     */
    CouponTemplate createTemplate(TemplateRequest request);

    /**
     * 创建优惠券模板 - 每个商户只能创建100个
     */
    List<TemplateInfo> searchTemplate(TemplateRequest request);

    /**
     * 通过ID查询优惠券模板
     */
    TemplateInfo loadTemplateInfo(Long id);

    /**
     * 让优惠券无效
     */
    void inactiveCoupon(Long id);

    /**
     * 批量读取模板
     */
    Map<Long, TemplateInfo> getTemplateInfoMap(Collection<Long> ids);
}
