package rgn.spring.functionscloudstream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Function;


@Configuration
@Slf4j
public class Functions {
    @Bean
    Function<String,String> uppercase() {
        return message -> {
            log.info("Converting '{}' to uppercase", message);
            return message.toUpperCase();
        };
    }

    @Bean
    Function<String,String> reverse() {
        return message -> {
            log.info("Reversing '{}'", message);
            return new StringBuilder(message).reverse().toString();
        };
    }

    @Bean
    Function<Flux<String>,Flux<String>> reverseReactive() {
        return flux -> flux
                .doOnNext(message -> log.info("reactively reversing: {}", message))
                .map(message -> new StringBuilder(message).reverse().toString());
    }


    @Bean
    Function<Message,String> reverseWithMessage() {
        return message -> {
            log.info("Reversing '{}'", message.getContent());
            return new StringBuilder(message.getContent()).reverse().toString();
        };
    }

}

@Data
@AllArgsConstructor
@NoArgsConstructor
class Message {
    private String content;
}