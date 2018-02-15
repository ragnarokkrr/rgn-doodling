package ragna.core.http.simple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class Server extends AbstractVerticle{


    @Override
    public void start() throws Exception {
        vertx.createHttpServer ()
                .requestHandler (req -> {
                    req.response ()
                            .putHeader ("content-type", "text/html")
                            .end("<html><body><h1>Hello from vert.x!</h1></body></html>");
                })
                .listen (8080);

        // http GET localhost:8080
    }

    public static void main(String[] args) {
        Vertx vertx  = Vertx.vertx ();

        vertx.deployVerticle (new Server () );
    }
}

