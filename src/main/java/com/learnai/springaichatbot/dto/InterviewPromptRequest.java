package com.learnai.springaichatbot.dto;

import jakarta.validation.constraints.NotBlank;

public record InterviewPromptRequest(
        @NotBlank(message = "Topic must not be empty")
        String topic,

        @NotBlank(message = "Difficulty must not be empty")
        String difficulty
) {
}
