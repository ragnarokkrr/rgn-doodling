package ragna.domainsockets;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.parsetools.RecordParser;


//https://github.com/vert-x3/vertx-examples/blob/master/core-examples/src/main/java/io/vertx/example/core/net/greeter
//http://vertx.io/docs/vertx-core/java/#_domain_sockets
//http://netty.io/wiki/native-transports.html
//http://vertx.io/docs/vertx-core/java/#_native_transports
//https://github.com/eclipse/vert.x/issues/1674     Support UNIX Domain Sockets in HttpClient/HttpServer
public class ServerDomainSocket extends AbstractVerticle{

    @Override
    public void start() throws Exception {
        vertx.createNetServer ().connectHandler (sock -> {
            RecordParser parser = RecordParser.newDelimited("\n", sock);

            parser
                .endHandler (v -> sock.close ())
                .exceptionHandler ( t-> {
                    t.printStackTrace ();
                    sock.close ();
                })
                .handler(buffer -> {
                    String line = buffer.toString ("UTF-8");
                    System.out.println ("Received: " + line);
                    sock.write ("Received : " + line +  "\n", "UTF-8");
                });

        //}).listen (1234);
        }).listen (SocketAddress.domainSocketAddress("/tmp/testsocket.sock"));
        System.out.println ("Echo domain socket is listening");
    }

    public static void main(String[] args) {
        VertxOptions vertxOptions = new VertxOptions ().
                setPreferNativeTransport (true);
        Vertx vertx = Vertx.vertx (vertxOptions);

        // True when native is available
        boolean usingNative = vertx.isNativeTransportEnabled();
        System.out.println("Running with native: " + usingNative);

        vertx.deployVerticle (new ServerDomainSocket ());

    }

}
