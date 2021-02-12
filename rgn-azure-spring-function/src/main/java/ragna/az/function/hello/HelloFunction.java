package ragna.az.function.hello;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ragna.az.function.hello.model.Greeting;
import ragna.az.function.hello.model.User;

import java.util.function.Function;

@Component
@Slf4j
public class HelloFunction implements Function<User, Greeting> {
    @Override
    public Greeting apply(User user) {
        log.info("Function called! {}", user);
        return new Greeting(String.format("Hello, %s!\n", user.getName()));
    }
}
