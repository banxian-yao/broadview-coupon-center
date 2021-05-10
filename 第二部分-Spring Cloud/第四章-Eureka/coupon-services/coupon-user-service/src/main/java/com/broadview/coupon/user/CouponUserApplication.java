package com.broadview.coupon.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableJpaAuditing
@SpringBootApplication
@ComponentScan(basePackages = {"com.broadview"})
public class CouponUserApplication {


    @Bean
    public RestTemplate register() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(CouponUserApplication.class, args);
    }
}

