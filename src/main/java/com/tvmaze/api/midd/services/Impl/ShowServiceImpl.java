package com.tvmaze.api.midd.services.Impl;




import com.tvmaze.api.midd.config.TvMazeApiClient;
import com.tvmaze.api.midd.dtos.ShowResponseDto;
import com.tvmaze.api.midd.models.ShowDocument;
import com.tvmaze.api.midd.repositories.ShowRepository;
import com.tvmaze.api.midd.services.ShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {
    private static final Logger log = LoggerFactory.getLogger(ShowServiceImpl.class);

    private final TvMazeApiClient apiClient;
    private final ShowRepository showRepository;

    //inyectamos por constructor
    public ShowServiceImpl(TvMazeApiClient apiClient, ShowRepository showRepository) {
        this.apiClient = apiClient;
        this.showRepository = showRepository;
    }

    // metodo de busqueda - Endpoint A
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

    // Busquda nueva por showId - Endpoint B
    public Map<String, Object> getShowById(Long showId) {
        log.info("Buscando showId con ID: {}", showId);

        //validar el "cache" en la bd de mongo
        Optional<ShowDocument> cachedShow = showRepository.findById(showId);

        if (cachedShow.isPresent()) {
            log.info("ShowId {} encontrado en Mongo, Retornando caché...", showId);
            return cachedShow.get().getData();
        }

        //si no lo encuentra, hay que consumir el API de TV-Maze
        log.info("ShowId {} no encontrado en Mongo, Consumiendo API externa...", showId);
        Map<String, Object> externalResponse = apiClient.getShowById(showId);

        //guardar el resultado en Mongo antes de retornar
        log.info("Guardando showId {} en Mongo para futuras consultas.", showId);
        ShowDocument document = new ShowDocument(showId, externalResponse);
        showRepository.save(document);

        return externalResponse;
    }
}