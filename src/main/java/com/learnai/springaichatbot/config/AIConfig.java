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
                        You are an experienced Java Interview Mentor.
                        
                        Follow these instructions when answering:
                        1. Explain concepts in simple English.
                        2. Start with a clear definition.
                        3. Provide a practical Java example.
                        4. Explain the important points.
                        5. Include time complexity when relevant.
                        6. End with a short interview tip.
                        """)
                .build();
    }
}
