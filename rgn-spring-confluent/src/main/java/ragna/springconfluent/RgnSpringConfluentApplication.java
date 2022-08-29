package ragna.springconfluent;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootApplication
@EnableKafkaStreams
public class RgnSpringConfluentApplication {
	public static void main(String[] args) {
		SpringApplication.run(RgnSpringConfluentApplication.class, args);
	}

	@Bean
	NewTopic hobbit2() {
		return TopicBuilder.name("hobbit2").partitions(15).replicas(3).build();
	}

	@Bean
	NewTopic counts() {
		return TopicBuilder.name("streams-wordcount-output").partitions(6).replicas(3).build();
	}
}

@RequiredArgsConstructor
@Component
class Producer {

	private final KafkaTemplate<Integer, String> template;

	Faker faker;

	@EventListener(ApplicationStartedEvent.class)
	public void generate() {
		faker = Faker.instance();

		final var intervalFlux = Flux.interval(Duration.ofMillis(1_000));

		final var quotesFlux = Flux.fromStream(Stream.generate(() -> faker.hobbit().quote()));

		Flux.zip(intervalFlux, quotesFlux)
				.map(it -> template.send("hobbit", faker.random().nextInt(42), it.getT2())).blockLast();

	}

}

@Component
@Slf4j
class Consumer {

	@KafkaListener(topics = {"hobbit"}, groupId = "spring-boot-kafka")
	public void consumer(ConsumerRecord<Integer, String> record) {
		log.info("received: '{}' with key {}", record.value(), record.key());
	}
}

@Component
class Processor {

	@Autowired
	public void process(StreamsBuilder builder) {
		final var integerSerde = Serdes.Integer();
		final var stringSerde = Serdes.String();
		final var longSerde = Serdes.Long();

		final var textLinesKStream = builder.stream("hobbit", Consumed.with(integerSerde, stringSerde));

		final var wordCountsKStream = textLinesKStream
				.flatMapValues(value -> Arrays.asList(value.toLowerCase().split("\\W+")))
				.groupBy((key, value) -> value, Grouped.with(stringSerde, stringSerde))
				.count(Materialized.as("counts"));

		wordCountsKStream.toStream().to("streams-wordcount-output", Produced.with(stringSerde, longSerde));

	}
}

@RestController
@RequiredArgsConstructor
class RestService {
	private final StreamsBuilderFactoryBean factoryBean;


	@GetMapping("/count/{word}")
	public Long getCount(@PathVariable String word) {
		final var kafkaStreams = factoryBean.getKafkaStreams();
		assert kafkaStreams != null : "";

		final ReadOnlyKeyValueStore<String, Long> counts = kafkaStreams.store(StoreQueryParameters.fromNameAndType("counts", QueryableStoreTypes.keyValueStore()));

		return counts.get(word);
	}
}








