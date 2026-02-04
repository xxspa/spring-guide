package com.newzhxu.application;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
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
        final String securitySchemeName = "formLogin";

        return new OpenAPI()
                .info(new Info().title("我的 API 文档").version("1.0"))
                // 1. 定义安全模式 (Security Scheme)
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("basic") // Form Login 通常在 UI 层面模拟为 Basic 或通过特定 URL
                                        .description("请输入用户名和密码进行登录")))
                // 2. 全局应用该安全需求
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName));
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
