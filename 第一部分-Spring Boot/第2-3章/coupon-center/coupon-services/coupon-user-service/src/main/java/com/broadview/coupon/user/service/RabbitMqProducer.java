package com.broadview.coupon.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitMqProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${broadview.rabbitmq.exchange}")
    private String exchange;

    @Value("${broadview.rabbitmq.routingkey}")
    private String routingKey;

    public void produceMsg(String msg){
        amqpTemplate.convertAndSend(exchange, routingKey, msg);
        log.debug("Send msg ={} " , msg);
    }
}
