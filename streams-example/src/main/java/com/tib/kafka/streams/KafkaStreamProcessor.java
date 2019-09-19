package com.tib.kafka.streams;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import org.apache.kafka.streams.kstream.Printed;

import java.util.Properties;

public class KafkaStreamProcessor {

    private static final String DEFAULT_BOOTSTRAP_SERVERS = "localhost:9092";
    private static final String INPUT_TOPIC = "kafka-stream-example-input-topic";
    private static final String OUTPUT_TOPIC = "kafka-steam-example-output-topic";

    public static void main(String[] args) {
        KafkaStreamProcessor streamProcessor = new KafkaStreamProcessor();

        streamProcessor.run();
    }

    private void run() {
        KafkaStreams notificationStream = setupNotificationStream();

        notificationStream.cleanUp();
        notificationStream.start();

        System.out.println("Running kafka stream processor");
        Runtime.getRuntime().addShutdownHook(new Thread(notificationStream::close));
    }

    private KafkaStreams setupNotificationStream() {
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        KStream<String, String> stream = streamsBuilder.stream(INPUT_TOPIC);

        KStream<String, String> notificationStream = stream.map((KeyValueMapper<String, String, KeyValue<String, String>>) KeyValue::new);
        notificationStream.print(Printed.toSysOut());

        return new KafkaStreams(streamsBuilder.build(), streamProperties());
    }

    private Properties streamProperties() {
        Properties kafkaStreamProperties = new Properties();

        kafkaStreamProperties.put(StreamsConfig.APPLICATION_ID_CONFIG, "new-data-notification");
        kafkaStreamProperties.put(StreamsConfig.CLIENT_ID_CONFIG, "new-data-notification-client");
        kafkaStreamProperties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, DEFAULT_BOOTSTRAP_SERVERS);
        kafkaStreamProperties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        kafkaStreamProperties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        kafkaStreamProperties.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, 10 * 1000);
        kafkaStreamProperties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);

        return kafkaStreamProperties;
    }
}
