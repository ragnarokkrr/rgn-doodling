package ragna.az.function.hello;


import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import ragna.az.function.hello.dto.Greeting;
import ragna.az.function.hello.dto.User;
import ragna.az.function.hello.functions.HelloFunction;

import static org.assertj.core.api.Assertions.assertThat;

class HelloFunctionTest {

    @Test
    public void test() {
        final var greeting = new HelloFunction().apply(new User("foo"));
        assertThat(greeting).isNotNull().hasFieldOrPropertyWithValue("message", "Hello, foo!\n");
    }

    @Test
    public void start() throws Exception {
        final var handler = new AzureSpringBootRequestHandler<User, Greeting>(HelloFunction.class);
        final var greeting = handler.handleRequest(new User("foo"), null);
        handler.close();

        assertThat(greeting).isNotNull().hasFieldOrPropertyWithValue("message", "Hello, foo!\n");
    }

}