package com.api.midd.tvmaze.config;



import com.api.midd.tvmaze.dtos.TvMazeSearchResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class TvMazeApiClient {

    private final RestClient restClient;

    public TvMazeApiClient(RestClient tvMazeRestClient) {
        this.restClient = tvMazeRestClient;
    }

    //puente de comunicación HTTP para construir la URL exacta
    public List<TvMazeSearchResponse> searchShows(String query) {
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search/shows")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}