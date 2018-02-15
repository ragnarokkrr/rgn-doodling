package ragna.core.http.simple;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class Client extends AbstractVerticle{

    @Override
    public void start() throws Exception {
        vertx.createHttpClient ()
                .getNow(8080, "localhost", "/", resp -> {
                    System.out.println ("Got Response " + resp.statusCode ());
                    resp.bodyHandler (body -> {
                        System.out.println ("Got Data " + body.toString ());
                    });
                });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx ();

        vertx.deployVerticle (new Client ());
    }
}
