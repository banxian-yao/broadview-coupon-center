package com.broadview.coupon.user.external;

import com.broadview.coupon.shared.beans.PlaceOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "coupon-calculator-service")
public interface CalculationClient {

    @RequestMapping(value = "/calculator/checkout", method = RequestMethod.POST)
    PlaceOrder computeRule(@RequestBody PlaceOrder settlement);
}
