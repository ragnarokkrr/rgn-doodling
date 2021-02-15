package ragna.az.function.hello.functions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ragna.az.function.hello.dto.Greeting;
import ragna.az.function.hello.dto.User;

import java.util.function.Function;

@Component("hello")
@Slf4j
public class HelloFunction implements Function<User, Greeting> {
    @Override
    public Greeting apply(User user) {
        log.info("Function called! {}", user);
        return new Greeting(String.format("Hello, %s!\n", user.getName()));
    }
}
