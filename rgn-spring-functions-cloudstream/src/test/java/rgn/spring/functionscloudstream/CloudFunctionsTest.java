package rgn.spring.functionscloudstream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.cloud.function.context.test.FunctionalSpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Function;

@FunctionalSpringBootTest
class CloudFunctionsTest {
    @Autowired
    private FunctionCatalog functionCatalog;


    @Test
    void testUppercaseThenReverseWithMessage() {
        var input = "Spring Cloud";
        var expectedOutput = "DUOLC GNIRPS";


        Function<Flux<String>, Flux<String>> function = functionCatalog.lookup(Function.class, "uppercase|reverseReactive");

        StepVerifier.create(function.apply(Flux.just(input)))
                .expectNextMatches(actualOutput -> actualOutput.equals(expectedOutput))
                .verifyComplete();

    }

}