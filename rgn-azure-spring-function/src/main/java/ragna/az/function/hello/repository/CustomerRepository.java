package ragna.az.function.hello.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ragna.az.function.hello.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends MongoRepository <Customer, String>{
   Optional<Customer> findByFirstName(String firstName);
   public List<Customer> findByLastName(String lastName);
}
