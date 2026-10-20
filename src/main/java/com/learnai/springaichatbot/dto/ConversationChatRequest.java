package com.learnai.springaichatbot.dto;

import jakarta.validation.constraints.NotBlank;

public record ConversationChatRequest(

        @NotBlank(message = "Conversation ID is required")
        String conversationId,

        @NotBlank(message = "Message cannot be empty")
        String message
) {}
