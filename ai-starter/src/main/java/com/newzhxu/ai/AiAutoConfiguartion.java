package com.newzhxu.ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@EnableConfigurationProperties(AiProperties.class)

public class AiAutoConfiguartion {
//    @Bean
//    @ConditionalOnMissingBean
//    public OllamaChatModel ollamaChatModel() {
//        return OllamaChatModel.builder().build();
//    }

    @Bean
    ChatClient chatClient(OllamaChatModel ollamaChatModel) {
        return ChatClient.builder(ollamaChatModel)
                .build();
    }
}
