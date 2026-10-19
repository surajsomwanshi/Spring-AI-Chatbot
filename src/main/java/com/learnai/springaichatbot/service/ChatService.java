package com.learnai.springaichatbot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String getChatResponse(String message) {
        long start = System.currentTimeMillis();

        String response = chatClient.prompt()
                .user(message)
                .call()
                .content();

        System.out.println("AI response time: "
                + (System.currentTimeMillis() - start) + " ms");

        return response;
    }
}
