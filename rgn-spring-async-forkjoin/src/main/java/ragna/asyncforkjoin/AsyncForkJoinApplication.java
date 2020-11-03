package ragna.asyncforkjoin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class AsyncForkJoinApplication {

  public static void main(String[] args) {
    SpringApplication.run(AsyncForkJoinApplication.class, args);
  }

  @Bean
  public Executor taskExecutor() {
    final var executor = new ThreadPoolTaskExecutor();

    executor.setCorePoolSize(4);
    executor.setMaxPoolSize(4);
    executor.setQueueCapacity(500);
    executor.setThreadNamePrefix("RgnAsync");
    executor.initialize();
    return executor;
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
