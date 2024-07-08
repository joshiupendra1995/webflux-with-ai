package com.uj.webflux.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.uj.webflux.model.ChatRequest;
import com.uj.webflux.model.ChatResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/chat")
@Slf4j
public class ChatController {
    private final WebClient webClient;
    @Value("${openai.model}")
    private String model;

    public ChatController(@Qualifier("openaiRestClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @PostMapping("/askQuestion")
    public Mono<ChatResponse> chat(@RequestBody JsonNode jsonNode) {
        String message = jsonNode.get("question").asText();
        ChatRequest request = new ChatRequest(model, message);
        log.info("request :: {}", request);
        return webClient.post().bodyValue(request).retrieve().bodyToMono(ChatResponse.class);
    }

}
