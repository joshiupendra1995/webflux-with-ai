package com.uj.webflux.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.uj.webflux.entity.Author;
import com.uj.webflux.repository.AuthorRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class AuthorController {

	@Autowired
	private AuthorRepository authorRepository;

	Logger logger = LogManager.getLogger(AuthorController.class);

	@GetMapping(value = "/author", produces = MediaType.APPLICATION_JSON_VALUE)
	public Mono<List<String>> getAllAuthors() {
		logger.info("into getAllAuthors method");
		Mono<List<String>> mono = Flux.fromIterable(authorRepository.findAll()).map(Author::getAuthorName).collectList();
		mono.subscribe(a->logger.info("Author :: {}",a));
		return mono;
	}

}
