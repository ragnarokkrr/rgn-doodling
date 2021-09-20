package hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloVerticle extends AbstractVerticle {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloVerticle.class);
    private long counter = 1;


    @Override
    public void start() throws Exception {
        vertx.setPeriodic(5_000, id  -> {
            LOGGER.info("tick");
        });

        vertx.createHttpServer()
                .requestHandler(request -> {
                    LOGGER.info("Request #{} from {}", counter ++, request.remoteAddress().host());
                    request.response().end("Hello!");

                })
        .listen(8080);
        LOGGER.info("Open http://localhost:8080/");

    }

    public static void main(String[] args) {
        final var vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());
    }
}
