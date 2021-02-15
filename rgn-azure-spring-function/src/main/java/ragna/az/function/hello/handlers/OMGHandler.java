package ragna.az.function.hello.handlers;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.AzureSpringBootRequestHandler;
import ragna.az.function.hello.dto.ItHappens;
import ragna.az.function.hello.dto.OMG;

import java.util.Optional;

public class OMGHandler extends AzureSpringBootRequestHandler<ItHappens, OMG> {

    @FunctionName("omg")
    public HttpResponseMessage execute(
            @HttpTrigger(name = "request",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<Optional<ItHappens>> request,
            ExecutionContext context
    ) {
        final var itHappens = request.getBody()
                .filter(itHpns -> itHpns.getMessage() != null)
                .orElseGet(() -> new ItHappens(request.getQueryParameters().getOrDefault("message", "it")));
        context.getLogger().info("It happened itHappens name: " + itHappens);
        return request.createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(itHappens, context))
                .header("Content-Type", "application/json")
                .build();
    }
}
