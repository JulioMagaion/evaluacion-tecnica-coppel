package com.api.midd.tvmaze.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {

    //Bean para consumir el api test utilizando RestClient de java 25
    @Bean
    public RestClient tvMazeRestClient() {
        //Configuramos la URL base indicada en los criterios
        return RestClient.builder()
                .baseUrl("https://api.tvmaze.com")
                .build();
    }
}