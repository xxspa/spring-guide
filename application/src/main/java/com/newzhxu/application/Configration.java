package com.newzhxu.application;

import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.*;

@Configuration
@Slf4j
public class Configration {
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI();
    }

    @Bean
    AsyncConfigurer asyncConfigurer() {
        return new AsyncConfigurer() {
            @Override
            public @Nullable Executor getAsyncExecutor() {
                return AsyncConfigurer.super.getAsyncExecutor();
            }

            @Override
            public @NonNull AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
                return ((ex, method, params) -> {
                    log.error("Async error in method: " + method.getName() + " with params: " + java.util.Arrays.toString(params), ex);
                });
            }
        };
    }

    @Bean
    Executor asyncExecutor() {
        return new ThreadPoolExecutor(10, 20,
                60L, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
