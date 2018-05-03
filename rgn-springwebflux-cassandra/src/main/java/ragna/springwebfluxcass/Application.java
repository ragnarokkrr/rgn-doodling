package ragna.springwebfluxcass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ragna.springwebfluxcass.client.Client;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        Client client = new Client();

        client.doStuff();
    }

}
