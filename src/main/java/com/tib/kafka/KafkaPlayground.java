package com.tib.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KafkaPlayground
{
    public static void main( String[] args ) {
        SpringApplication.run(KafkaPlayground.class);
    }
}
