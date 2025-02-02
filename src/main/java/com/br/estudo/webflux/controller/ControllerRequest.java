package com.br.estudo.webflux.controller;

import com.br.estudo.webflux.controller.dto.ProductResourceDTO;
import com.br.estudo.webflux.process.ProcessService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Arrays;

@RestController
@RequestMapping("/v3")
public class ControllerRequest {

    final ProcessService processService;

    public ControllerRequest(ProcessService processService) {
        this.processService = processService;
    }


    @GetMapping("/resources")
    public Mono<ProductResourceDTO> getAllProducts(@RequestParam String scopes){

        var lista = Arrays.asList(scopes.split(" "));
//        var result = processService.getAllProducts(lista);
        return processService.getAllProducts(lista);
    }

}
