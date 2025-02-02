package com.br.estudo.webflux.http;

import com.br.estudo.webflux.controller.dto.ResourceDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class CallProductService {
    private static final Logger log = LoggerFactory.getLogger(CallProductService.class);

    private final WebClient webClient;

    public CallProductService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<List<ResourceDTO>> callExternalApi(String scope) {
        log.info("Chamando API para scope: {}", scope);

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/"+scope).build())
                .retrieve()
                .bodyToFlux(ResourceDTO.class)  // Processa a resposta como uma lista de objetos
                .collectList()
                .doOnNext(resources -> log.info("Resposta da API para scope {}: {}", scope, resources))
                .doOnError(error -> log.error("Erro ao chamar API para scope {}: {}", scope, error.getMessage()));
    }
}
