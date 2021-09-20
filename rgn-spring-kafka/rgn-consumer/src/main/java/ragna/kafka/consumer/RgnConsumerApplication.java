package ragna.kafka.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.schema.registry.client.EnableSchemaRegistryClient;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@SpringBootApplication
@EnableSchemaRegistryClient
@RestController
public class RgnConsumerApplication {

	private Random random = new Random();

	public static void main(String[] args) {
		SpringApplication.run(RgnConsumerApplication.class, args);
	}

}
