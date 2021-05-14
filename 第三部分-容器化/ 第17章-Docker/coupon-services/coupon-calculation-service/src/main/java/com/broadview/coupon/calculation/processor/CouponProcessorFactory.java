package com.broadview.coupon.calculation.processor;

import com.broadview.coupon.calculation.processor.impl.DiscountProcessor;
import com.broadview.coupon.calculation.processor.impl.DummyProcessor;
import com.broadview.coupon.calculation.processor.impl.MoneyOffProcessor;
import com.broadview.coupon.shared.beans.CouponInfo;
import com.broadview.coupon.shared.beans.PlaceOrder;
import com.broadview.coupon.shared.beans.TemplateInfo;
import com.broadview.coupon.shared.enums.CouponType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

@Component
@Slf4j
public class CouponProcessorFactory {

    @Autowired
    private MoneyOffProcessor moneyOffProcessor;

    @Autowired
    private DiscountProcessor discountProcessor;

    @Autowired
    private DummyProcessor dummyProcessor;

    public RuleProcessor getRuleProcessor(PlaceOrder settlement) {
        // 不使用优惠券
        if (CollectionUtils.isEmpty(settlement.getCouponInfos())) {
            return dummyProcessor;
        }

        if (settlement.getCouponInfos().size() != 1) {
            log.error("cannot apply multiple coupons to one order");
            throw new IllegalArgumentException("Only one coupon per order");
        }

        // 获取优惠券的类别
        TemplateInfo template = settlement.getCouponInfos().get(0).getTemplate();
        CouponType category = CouponType.of(template.getType());

        if (category == null) {
            log.error("Unsupported coupone type, {}", template.getType());
            throw new IllegalArgumentException("Coupon type not supported");
        }

        switch (category) {
            case MONEY_OFF:
                return moneyOffProcessor;
            case DISCOUNT:
                return discountProcessor;
            default:
                return dummyProcessor;
        }
    }

}
