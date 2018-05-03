package ragna.springreactiverest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GreetingRouterTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    public void testHello() {
        webTestClient
                //GET request
                .get().uri("/hello")
                .accept(MediaType.TEXT_PLAIN)
                .exchange()
                //assertion DSL
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("Hello, Spring!");

    }
}
