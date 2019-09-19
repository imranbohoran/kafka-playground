package com.tib.kafka.spring;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaPlaygroundListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPlaygroundListener.class);

    @KafkaListener(topics = KafkaPlaygroundConstants.TOPIC_NAME)
    public void messageReceive(ConsumerRecord<String, String> message) {
        LOGGER.info("Message received with content: {}", message.value());
    }
}
