package ragna.c08;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;

//https://www.codeproject.com/Tips/1040097/Create-a-Simple-Web-Server-in-Java-HTTP-Server
public class YahooMockHttpServer {

    public static void main(String[] args) throws IOException {
        int port = 10000;

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("Server started at: " + port);

        server.createContext("/table.csv", (HttpExchange httpExchange) -> handleRoot(httpExchange));

        server.setExecutor(null);
        server.start();
    }

    private static void handleRoot(HttpExchange he) throws IOException {

        String query = he.getRequestURI().getQuery().split("=")[1];
        System.out.println(query);
        String response = String.format("OMG it works\n40000,%f\n", new BigDecimal(query.hashCode()));

        he.sendResponseHeaders(200, response.length());
        OutputStream os = he.getResponseBody();
        os.write(response.getBytes());
        os.close();

    }

}
