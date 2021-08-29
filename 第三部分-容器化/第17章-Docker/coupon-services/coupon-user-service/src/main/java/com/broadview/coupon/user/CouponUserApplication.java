package com.broadview.coupon.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableFeignClients(basePackages = {"com.broadview"})
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"com.broadview"})
public class CouponUserApplication {


    @Bean
    feign.Logger.Level feignLoggerInfo() {
        return feign.Logger.Level.FULL;
    }

    public static void main(String[] args) {
        SpringApplication.run(CouponUserApplication.class, args);
    }
}

