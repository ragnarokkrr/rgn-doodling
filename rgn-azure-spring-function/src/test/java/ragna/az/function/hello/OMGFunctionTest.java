package ragna.az.function.hello;


import org.junit.jupiter.api.Test;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import ragna.az.function.hello.dto.ItHappens;
import ragna.az.function.hello.dto.OMG;
import ragna.az.function.hello.functions.OMGFunction;

import static org.assertj.core.api.Assertions.assertThat;

class OMGFunctionTest {

    @Test
    public void test() {
        final var omg = new OMGFunction().apply(new ItHappens("Thanos"));
        assertThat(omg).isNotNull().hasFieldOrPropertyWithValue("message", "OMG! Don't believe Thanos happened!\n");
    }

    @Test
    public void start() throws Exception {
        final var handler = new AzureSpringBootRequestHandler<ItHappens, OMG>(OMGFunction.class);
        final var omg = handler.handleRequest(new ItHappens("foo"), null);
        handler.close();

        assertThat(omg).isNotNull().hasFieldOrPropertyWithValue("message", "OMG! Don't believe foo happened!\n");
    }

}