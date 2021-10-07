package com.broadview.coupon.template.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitConsumer {

    @RabbitListener(queues="${broadview.rabbitmq.queue}")
    public void recievedMessage(String msg) {
        log.debug("Recieved Message: {}" , msg);
        //其他业务逻辑
    }
}
