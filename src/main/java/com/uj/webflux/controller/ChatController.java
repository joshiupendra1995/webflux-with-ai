package com.uj.webflux.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/chat")
@Slf4j
public class ChatController {
    private final WebClient webClient;
    @Value("${openai.model}")
    private String model;

    private final static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public ChatController(@Qualifier("openaiRestClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @PostMapping("/askQuestion")
    public Mono<JsonNode> sendAnswer(@RequestBody JsonNode jsonNode) {
        String question = jsonNode.get("question").asText();
        ObjectNode objectNode = OBJECT_MAPPER.createObjectNode().put("message", question).set("options",OBJECT_MAPPER.createObjectNode().put("model",model));
        return webClient.post().bodyValue(objectNode).retrieve().bodyToMono(JsonNode.class).flatMap(data->{
            JsonNode messages = data.get("data").get("messages");
            for (JsonNode message : messages) {
                if ("assistant".equals(message.get("role").asText())) {
                    return Mono.just(message.get("content"));
                }
            }
            return Mono.empty();
        });
    }

}
