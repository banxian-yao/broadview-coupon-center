package com.broadview.coupon.shared.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private double price;

    private Integer count;

    private Long shopId;

}
