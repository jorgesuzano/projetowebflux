package com.br.estudo.webflux.controller;

import com.br.estudo.webflux.controller.dto.ProductResourceDTO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v3")
public class ControllerRequest {

    @GetMapping("/resources")
    public Mono<ProductResourceDTO> getAllProducts(){

        return null;
    }

}
