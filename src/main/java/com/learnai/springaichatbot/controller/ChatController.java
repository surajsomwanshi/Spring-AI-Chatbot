package com.learnai.springaichatbot.controller;

import com.learnai.springaichatbot.dto.ChatRequest;
import com.learnai.springaichatbot.dto.ChatResponse;
import com.learnai.springaichatbot.dto.InterviewPromptRequest;
import com.learnai.springaichatbot.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {

        System.out.println("Controller started: " + java.time.LocalTime.now());

        String reply = chatService.getChatResponse(request.message());

        System.out.println("Controller finished: " + java.time.LocalTime.now());


        return new ChatResponse(reply);
    }

    @PostMapping("/interview")
    public ChatResponse generateInterviewAnswer(
            @Valid @RequestBody InterviewPromptRequest request
            ){
        String answer = chatService.generateInterviewAnswer(
                request.topic(),
                request.difficulty()
        );

        return new ChatResponse(answer);
    }
}
