package com.broadview.coupon.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaAuditing
@ComponentScan(basePackages = {"com.broadview"})
public class CouponTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(CouponTemplateApplication.class, args);
    }
}
