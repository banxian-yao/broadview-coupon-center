package com.broadview.coupon.user.external;

import com.broadview.coupon.shared.beans.PlaceOrder;
import com.broadview.coupon.shared.beans.TemplateInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * 券模板接口，不能使用Feign
 */
@Component
public class CalculationClient {

    @Autowired
    private RestTemplate restTemplate;

    @Value("broadview.calculatserivce.url")
    private String calculationUrl;

    public PlaceOrder computeRule(PlaceOrder settlement){
        return restTemplate.postForEntity(calculationUrl,settlement,PlaceOrder.class).getBody();
    }
}
