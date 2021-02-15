package ragna.az.function.hello.handlers;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import org.springframework.util.StringUtils;
import ragna.az.function.hello.dto.CustomerResponse;

import java.util.Optional;

@Slf4j
public class CustomerGetHandler extends AzureSpringBootRequestHandler<String, Optional<CustomerResponse>> {

    @FunctionName("customerGet")
    public HttpResponseMessage get(
            @HttpTrigger(name = "customerGetRequest",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<?> request,
            @BindingName("id") String id,
            ExecutionContext context) {
        log.info("CUSTOMER GET BIND: '{}'", id);

        if (!StringUtils.hasText(id)) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST).build();
        }
        return handleRequest(id, context)
                .map(customerResponse -> request.createResponseBuilder(HttpStatus.OK)
                        .body(customerResponse)
                        .build())
                .orElseGet(() -> request.createResponseBuilder(HttpStatus.NOT_FOUND).build())
                ;
    }

}
