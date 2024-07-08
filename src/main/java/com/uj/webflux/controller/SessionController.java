package com.uj.webflux.controller;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class SessionController {

    private final Logger logger = LoggerFactory.getLogger(SessionController.class);

    private final WebClient webClient;

    public SessionController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/v1/session")
    public Mono<JsonNode> getSessionData(){
        logger.info("Into session call");
        return webClient.get()
                .uri("/getAndTouch/session_doc/datastore-bucket")
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}
