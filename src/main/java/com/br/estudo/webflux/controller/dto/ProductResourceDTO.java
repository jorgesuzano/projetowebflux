package com.br.estudo.webflux.controller.dto;

import java.util.List;
import java.util.Map;

public record ProductResourceDTO(
        List<ResourceDTO> resources,
        Map<String, String> links,
        Map<String, Object> meta
) { }
