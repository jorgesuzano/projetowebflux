package com.br.estudo.webflux.process;

import com.br.estudo.webflux.controller.dto.MetaDTO;
import com.br.estudo.webflux.controller.dto.ProductResourceDTO;
import com.br.estudo.webflux.http.CallProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@Service
public class ProcessService {

    private static final Logger log = LoggerFactory.getLogger(ProcessService.class);


    private final CallProductService callProductService;

    public ProcessService(CallProductService callProductService) {
        this.callProductService = callProductService;
    }

    public Mono<ProductResourceDTO> getAllProducts(List<String> scopes){
        log.info("Request recebida com scopes {}", scopes);
        return fetchFromApis(scopes);
    }


    public Mono<ProductResourceDTO> fetchFromApis(List<String> scopes) {
        return Flux.fromIterable(scopes)
                .flatMap(callProductService::callExternalApi) // Chama o serviço para cada scope
                .flatMap(Flux::fromIterable) // Achata listas aninhadas
                .collectList()
                .map(resources -> new ProductResourceDTO(
                        resources,
                        Map.of("self", "/api/generic-search"),
                        new MetaDTO(Instant.now(), resources.size(), 1)
                ));
    }

//    private Mono<ResourceDTO> callExternalApi(String scope) {
//        log.info("Chamando API para scope: {}", scope);
//
//        return webClient.get()
//                .uri(uriBuilder -> uriBuilder.path("/resource")
//                        .queryParam("scope", scope)
//                        .build())
//                .retrieve()
//                .bodyToMono(ResourceDTO.class)
//                .doOnNext(resource -> log.info("Resposta da API para scope {}: {}", scope, resource))
//                .doOnError(error -> log.error("Erro ao chamar API para scope {}: {}", scope, error.getMessage()));
//    }
}
