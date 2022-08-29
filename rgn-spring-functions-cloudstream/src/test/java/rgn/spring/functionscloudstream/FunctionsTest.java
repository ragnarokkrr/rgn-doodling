package rgn.spring.functionscloudstream;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionsTest {

    private final Functions functions = new Functions();

    @Test
    void testUppercase() {
        var input = "Spring Cloud";
        var expectedOutput = "SPRING CLOUD";

        var actualOutput = functions.uppercase().apply(input);

        assertThat(actualOutput)
                .isEqualTo(expectedOutput);

    }

    @Test
    void testReverse() {
        var input = "Spring Cloud";
        var expectedOutput = "duolC gnirpS";

        var actualOutput = functions.reverse().apply(input);

        assertThat(actualOutput)
                .isEqualTo(expectedOutput);

    }


    @Test
    void testUppercaseThenReverse() {
        var input = "Spring Cloud";
        var expectedOutput = "DUOLC GNIRPS";

        var actualOutput = functions.uppercase().andThen(functions.reverse()).apply(input);

        assertThat(actualOutput)
                .isEqualTo(expectedOutput);

    }

}