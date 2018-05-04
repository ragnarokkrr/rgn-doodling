package ragna.c06;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

//https://www.codeproject.com/Tips/1040097/Create-a-Simple-Web-Server-in-Java-HTTP-Server
public class MyHttpServer {

    public static void main(String[] args) throws IOException {
        int port = 9000;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Server started at: " + port);

        server.createContext("/", (HttpExchange httpExchange) -> handleRoot(httpExchange));

        server.setExecutor(null);
        server.start();
    }

    private static void handleRoot(HttpExchange he) throws IOException {
        String response = "OMG it works";

        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

}
