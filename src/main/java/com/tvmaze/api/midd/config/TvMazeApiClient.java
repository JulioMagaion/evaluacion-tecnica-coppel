package com.tvmaze.api.midd.config;



import com.tvmaze.api.midd.dtos.TvMazeSearchResponse;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

@Component
public class TvMazeApiClient {

    private final RestClient restClient;

    public TvMazeApiClient(RestClient tvMazeRestClient) {
        this.restClient = tvMazeRestClient;
    }

    //puente de comunicación HTTP para construir la URL exacta
    public List<TvMazeSearchResponse> searchShows(String query) {
        String path = "/search/shows";
        return restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    //Nuevo metodo para consultar el endpoint B
    public Map<String, Object> getShowById(Long showId) {
        String uri = "/shows/{show_id}";
        return restClient.get()
                .uri(uri, showId)
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }
}