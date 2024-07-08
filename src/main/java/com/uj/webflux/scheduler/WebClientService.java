//package com.uj.webflux.scheduler;
//
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Flux;
//
//@Component
//public class WebClientService {
//
//	@Scheduled(fixedDelay = 1000, initialDelay = 5000)
//	public void batchInit() {
//
//		Flux<String> record = WebClient.create().get().uri("http://localhost:8080/author")
//		.retrieve().bodyToFlux(String.class);
//
//		System.out.println(record.blockFirst());
//
//	}
//
//
//
//
//}
