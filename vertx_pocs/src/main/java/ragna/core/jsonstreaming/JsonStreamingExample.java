package ragna.core.jsonstreaming;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.file.AsyncFile;
import io.vertx.core.file.OpenOptions;
import io.vertx.core.parsetools.JsonEventType;
import io.vertx.core.parsetools.JsonParser;

import java.util.concurrent.atomic.AtomicInteger;

public class JsonStreamingExample extends AbstractVerticle{

    @Override
    public void start() throws Exception {
        String jsonFile = System.getenv ("JSON_FILE");

        vertx.fileSystem ().open (jsonFile, new OpenOptions (), ar ->{

            if (ar.succeeded ()) {
                AsyncFile asyncFile = ar.result ();

                AtomicInteger counter = new AtomicInteger ();

                JsonParser jsonParser = JsonParser.newParser (asyncFile);


                jsonParser.objectValueMode ()
                        .exceptionHandler (t -> {
                            t.printStackTrace ();
                            asyncFile.close ();
                        })
                        .endHandler (v -> {
                            System.out.println ("Done!");
                            asyncFile.close ();
                        })
                        .handler (event -> {
                            if (event.type () == JsonEventType.VALUE) {
                                DataPoint dataPoint = event.mapTo (DataPoint.class);
                                if (counter.incrementAndGet () % 5 == 0){
                                    System.out.println ("Datapoint = " + dataPoint);
                                }
                            }
                        });

            } else {
                ar.cause ().printStackTrace ();
            }
        });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx ();

        vertx.deployVerticle (new JsonStreamingExample ());
    }

}
