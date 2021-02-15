package ragna.az.function.hello.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import ragna.az.function.hello.model.Customer;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    Customer dave, oliver, carter;

    @BeforeEach
    void setUp() {
        customerRepository.deleteAll();

        dave = customerRepository.save(new Customer("Dave", "Matthews"));
        oliver = customerRepository.save(new Customer("Oliver August", "Matthews"));
        carter = customerRepository.save(new Customer("Carter", "Beauford"));

    }


    @Test
    void setIdOnsave() {
        var dave = customerRepository.save(new Customer("Dave", "Mathews"));

        assertThat(dave)
                .isNotNull()
                .hasNoNullFieldsOrProperties();
    }


    @Test
    void findLastName() {
        var result = customerRepository.findByLastName("Beauford");

        assertThat(result)
                .hasSize(1)
                .extracting("firstName")
                .contains("Carter");
    }


    @Test
    void findByExample() {
        final var example = new Customer(null, "Matthews");

        final var customerList = customerRepository.findAll(Example.of(example));

        assertThat(customerList)
                .hasSize(2)
                .extracting("firstName")
                .contains("Dave", "Oliver August");
    }
}