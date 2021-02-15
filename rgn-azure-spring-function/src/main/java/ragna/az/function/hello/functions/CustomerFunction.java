package ragna.az.function.hello.functions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import ragna.az.function.hello.dto.CustomerRequest;
import ragna.az.function.hello.dto.CustomerResponse;
import ragna.az.function.hello.service.CustomerService;

import java.util.Optional;
import java.util.function.Function;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class CustomerFunction {
    private final CustomerService customerService;

    @Bean("customerSave")
    public Function<CustomerRequest, CustomerResponse> save(){
        log.info("FUNCTION -> save");
        return customerService::save;
    }

    @Bean("customerGet")
    public Function<String, Optional<CustomerResponse>> findById(){
        log.info("FUNCTION -> find by id");
        return customerService::findById;
    }
}
