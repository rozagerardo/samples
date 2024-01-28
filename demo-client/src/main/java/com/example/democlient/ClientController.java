package com.example.democlient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
public class ClientController {

	@Autowired
	WebClient webClient;

	@GetMapping
	public String clientEndpoint() {
		return "Retrieved secured resource: %s".formatted(
				webClient.get().uri("http://localhost:8081/resource").retrieve().bodyToMono(String.class).block());
	}

}
