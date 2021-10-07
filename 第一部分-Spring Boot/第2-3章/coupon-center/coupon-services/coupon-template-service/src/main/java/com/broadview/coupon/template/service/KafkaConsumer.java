package com.broadview.coupon.template.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "broadview_test_topic", groupId = "broadview_test_listener")
    public void consumeMessage(@Payload String kfkMessage,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.OFFSET) int offsets){

        log.info("received  msg={} ,partition={}, offsets={}",kfkMessage,partition,offsets );
        //其他业务逻辑
    }
}
