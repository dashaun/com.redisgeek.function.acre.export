package com.redisgeek.function.acre.export;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ExportHandler extends FunctionInvoker<Optional<String>, String> {

    @FunctionName("Export")
    public HttpResponseMessage run(
            @HttpTrigger(
                    name = "req",
                    methods = {HttpMethod.GET, HttpMethod.POST},
                    authLevel = AuthorizationLevel.ANONYMOUS)
                    HttpRequestMessage<Optional<String>> request,
            final ExecutionContext context) {

        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(handleRequest(request.getBody(), context))
                .header("Content-Type", "application/json")
                .build();
    }
}
