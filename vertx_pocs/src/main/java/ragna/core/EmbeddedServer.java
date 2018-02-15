package ragna.core;

import io.vertx.core.Vertx;

public class EmbeddedServer {
    public static void main(String[] args) {
        Vertx.vertx ()
                .createHttpServer ()
                .requestHandler (req -> req.response ().end("hello world!"))
                .listen (8080);

        // http GET localhost:8080
        // curl -i localhost:8080
    }
}
