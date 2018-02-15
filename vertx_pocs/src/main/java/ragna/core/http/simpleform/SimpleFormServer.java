package ragna.core.http.simpleform;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;

public class SimpleFormServer extends AbstractVerticle{

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler (req -> {
            if (req.uri().equals("/")) {
                // send index page
                //req.response().sendFile("index.html");
                String indexFile = System.getenv ("INDEX_HTML");
                req.response().sendFile(indexFile);

            } else if (req.uri ().startsWith ("/form")){
                req.response().setChunked (true);
                req.setExpectMultipart(true);
                req.endHandler ((v) ->{
                   for (String attr : req.formAttributes ().names()) {
                       req.response ()
                               .write ("Got attr " + attr + " : " + req.formAttributes().get(attr) + "\n");
                   }
                   req.response ().end ();
                   //http -f POST localhost:8080/form foo=2 bar=4 o=mg
                });
            } else {
                req.response().setStatusCode (404).end ();
            }
        }).listen (8080);
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx ();
        vertx.deployVerticle (new SimpleFormServer ());
    }
}
