package com.learnai.springaichatbot.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatService {

    private final ChatClient chatClient;
    private final ChatMemory chatMemory;

    public ChatService(ChatClient chatClient, ChatMemory chatMemory) {
        this.chatClient = chatClient;
        this.chatMemory = chatMemory;
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


    public String generateInterviewAnswer(String topic, String difficulty){
        String template = """
                Explain {topic} for a Java developer
                preparing for an interview.
                
                Difficulty level: {difficulty}
                
                Follow this structure:
                1. Definition
                2. Practical Java example
                3. Internal working, where relevant
                4. Time and space complexity, where relevant
                5. Common interview questions
                """;

        PromptTemplate promptTemplate =
                new PromptTemplate(template);

        String prompt = promptTemplate.create(
                Map.of(
                        "topic", topic,
                        "difficulty", difficulty
                )
        ).getContents();

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }


    public String getConversationResponse(String conversationId,
                                          String message){
        return chatClient.prompt() // start a new AI request
                .user(message) //provides the user latest message
                .advisors(a->a
                        .advisors(
                                MessageChatMemoryAdvisor  // retrieves the previous messages and save
                                        .builder(chatMemory)
                                        .build()
                        )
                        .param(
                                ChatMemory.CONVERSATION_ID, // identify the conversation based on ID
                                conversationId
                        )
                )
                .call()
                .content(); //send the request to ollama model and return response
    }

}
