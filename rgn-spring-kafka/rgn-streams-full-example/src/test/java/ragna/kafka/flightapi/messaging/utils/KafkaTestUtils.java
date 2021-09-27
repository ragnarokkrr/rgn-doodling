package ragna.kafka.flightapi.messaging.utils;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;

import java.util.HashMap;
import java.util.UUID;

@AllArgsConstructor
public class KafkaTestUtils {
  private final EmbeddedKafkaBroker broker;

  public Producer<String, String> createProducer() {
    final var producerProps =
        new HashMap<>(org.springframework.kafka.test.utils.KafkaTestUtils.producerProps(broker));

    return new DefaultKafkaProducerFactory<>(
            producerProps, new StringSerializer(), new StringSerializer())
        .createProducer();
  }

  public Consumer<String, String> createConsumer(String topic) {
    final var consumerProps =
        org.springframework.kafka.test.utils.KafkaTestUtils.consumerProps(
            UUID.randomUUID().toString(), "true", broker);
    final var kafkaConsumerFactory = new DefaultKafkaConsumerFactory<String, String>(consumerProps);
    final var consumer = kafkaConsumerFactory.createConsumer();
    broker.consumeFromEmbeddedTopics(consumer, topic);
    return consumer;
  }

  public ConsumerRecord<String, String> getNextRecord(
      Consumer<String, String> consumer, String topic) {
    return org.springframework.kafka.test.utils.KafkaTestUtils.getRecords(consumer, 15000)
        .records(topic)
        .iterator()
        .next();
  }

  public void sendMessage(String topic, String json) {
    final var producer = createProducer();
    producer.send(new ProducerRecord<>(topic, json));
  }
}
