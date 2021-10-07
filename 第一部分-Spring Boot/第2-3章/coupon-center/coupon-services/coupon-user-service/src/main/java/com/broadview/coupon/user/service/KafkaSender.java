package com.broadview.coupon.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    String kafkaTopic = "broadview_test_topic";

    public void send(String message) {

        kafkaTemplate.send(kafkaTopic, message);
    }
}
