package com.broadview.coupon.shared.beans.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Discount {

    // 满减 - 减掉的钱数
    // 折扣 - 9 = 9折
    private Integer quota;

    // 最低达到多少消费才能用
    private Integer base;
}