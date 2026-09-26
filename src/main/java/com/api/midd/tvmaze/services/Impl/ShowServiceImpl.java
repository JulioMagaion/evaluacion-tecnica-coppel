package com.api.midd.tvmaze.services.Impl;




import com.api.midd.tvmaze.config.TvMazeApiClient;
import com.api.midd.tvmaze.dtos.ShowResponseDto;
import com.api.midd.tvmaze.services.ShowService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowServiceImpl implements ShowService {

    // inyección por constructor
    private final TvMazeApiClient apiClient;

    public ShowServiceImpl(TvMazeApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<ShowResponseDto> searchShows(String query) {
        var externalResponse = apiClient.searchShows(query);

        if (externalResponse == null) {
            return List.of();
        }

        return externalResponse.stream()
                .map(res -> {
                    var show = res.show();

                    //logica para determinar el canal network_name
                    //itera sobre el arreglo de resultados y extrae solo los atributos requeridos (id, name, chanel, summary y genres)
                    String channel = "Desconocido";
                    if (show.network() != null && show.network().name() != null) {
                        channel = show.network().name();
                    } else if (show.webChannel() != null && show.webChannel().name() != null) {
                        channel = show.webChannel().name();
                    }

                    return new ShowResponseDto(
                            show.id(),
                            show.name(),
                            channel,
                            show.summary(),
                            show.genres()
                    );
                })
                .toList();
    }
}