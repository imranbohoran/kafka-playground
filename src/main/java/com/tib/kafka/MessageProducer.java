package com.tib.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static com.tib.kafka.KafkaPlaygroundConstants.TOPIC_NAME;

@Component
public class MessageProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageProducer.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final List<String> messageContents;

    @Autowired
    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageContents = Arrays.asList("foo", "bar", "baz", "Gryffindor", "Hufflepuff", "Ravenclaw", "Slytherin");
    }

    @Scheduled(fixedDelay = 10000)
    public void execute() {
        int randindex = ThreadLocalRandom.current().nextInt(0, messageContents.size());
        LOGGER.info("Random index : {}", randindex);
        this.kafkaTemplate.send(TOPIC_NAME, "Message at: "+ System.currentTimeMillis() + " - contents: "+ this.messageContents.get(randindex));
    }
}
