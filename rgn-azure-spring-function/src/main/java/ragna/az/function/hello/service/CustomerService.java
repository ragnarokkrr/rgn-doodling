package ragna.az.function.hello.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Service;
import ragna.az.function.hello.dto.CustomerRequest;
import ragna.az.function.hello.dto.CustomerResponse;
import ragna.az.function.hello.model.Customer;
import ragna.az.function.hello.repository.CustomerRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {
    private final MapperFacade mapperFacade;
    private final CustomerRepository customerRepository;

    public CustomerResponse save(CustomerRequest customerRequest) {
        final var customer = mapToModel(customerRequest);
        final var savedCustomer = customerRepository.save(customer);
        return mapToResponse(savedCustomer);
    }

    public Optional<CustomerResponse> findById(String id) {
        log.info("SERVICE FIND BY ID: '{}'", id);
        return customerRepository
                .findById(id)
                .map(this::mapToResponse)
                .map(this::log);
    }

    private Customer mapToModel(CustomerRequest customerRequest) {
        return new Customer(customerRequest.getFirstName(), customerRequest.getLastName());
    }

    private CustomerResponse mapToResponse(Customer customer) {
        return new CustomerResponse(
                customer.getId(), customer.getFirstName(), customer.getLastName());
    }

    private CustomerResponse log(CustomerResponse customerResponse) {
        log.info("SERVICE FOUND BY ID: {}", customerResponse);
        return customerResponse;
    }

}
