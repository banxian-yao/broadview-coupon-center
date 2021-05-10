package com.broadview.coupon.shared.beans;

import com.broadview.coupon.shared.beans.rules.CalculationRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

/**
 * 通用传递优惠券模板的类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TemplateInfo {

    private Long id;

    private String name;

    private String description;

    private String type;

    private Long shopId;

    private CalculationRule rule;

    private Boolean available;

//    public static void main(String[] args) {
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DAY_OF_MONTH, 366);
//        System.out.println(c.getTimeInMillis());
//    }
}
