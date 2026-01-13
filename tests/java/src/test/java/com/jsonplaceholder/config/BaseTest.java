package com.jsonplaceholder.config;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;

/**
 * Classe base para configuração de testes
 * Define as especificações de request reutilizáveis
 */
public class BaseTest {

    protected static final String BASE_URL = "http://localhost:3003";

    /**
     * Retorna uma RequestSpecification configurada
     * com URL base e headers padrão
     * 
     * @return RequestSpecification configurada
     */
    protected RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();
    }
}
