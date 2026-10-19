package com.learnai.springaichatbot.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AIConfig {

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder){
        return builder
                .defaultSystem("""
                        You are a helpful AI assistant.
                        Explain Java and Spring Boot concepts clearly.
                        Include simple code examples when appropriate.
                        """)
                .build();
    }
}
