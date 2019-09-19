### Prerequisite
- Ensure a running kafka exists. The `docker-compose.yaml` can be used for this
- Console kafka client

### Topics
Create a topic for the example:
```
./kafka-topics.sh --create --topic kafka-stream-example-input-topic --partitions 1 --replication-factor 1 --zookeeper localhost:2181
```

### Producing data
```
./kafka-console-producer.sh --broker-list localhost:9092 --topic kafka-stream-example-input-topic
```
