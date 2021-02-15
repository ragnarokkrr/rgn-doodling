package ragna.az.function.hello.handlers;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import ragna.az.function.hello.dto.CustomerRequest;
import ragna.az.function.hello.dto.CustomerResponse;

import java.util.Optional;

@Slf4j
public class CustomerSaveHandler extends AzureSpringBootRequestHandler<CustomerRequest, CustomerResponse> {

    @FunctionName("customerSave")
    public HttpResponseMessage save(
            @HttpTrigger(name = "customerSaveRequest",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
            HttpRequestMessage<Optional<CustomerRequest>> request,
            ExecutionContext context){
        log.info("CUSTOMER SAVE BIND: '{}'", request);

        if (request.getBody().isEmpty()) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
        }

        return request.getBody()
                .map(customerRequest -> handleRequest(customerRequest, context))
                .map(customerResponse -> request.createResponseBuilder(HttpStatus.OK)
                        .body(customerResponse)
                        .build())
                .orElseGet(() -> request.createResponseBuilder(HttpStatus.NOT_FOUND).build());
    }

}
