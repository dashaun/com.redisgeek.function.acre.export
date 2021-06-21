package com.redisgeek.function.acre.export;

import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.TimerTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

/**
 * Azure Functions with HTTP Trigger.
 */
public class ExportHandler extends FunctionInvoker<Optional<String>, String> {

    @FunctionName("Export")
    public void run(
            // 0 */5 * * * * :: Every 5 minutes
            @TimerTrigger(name = "exportTimerTrigger", schedule = "0 */5 * * * *") String timerInfo,
            final ExecutionContext context) {
        handleRequest(Optional.empty(), context);
    }
}
