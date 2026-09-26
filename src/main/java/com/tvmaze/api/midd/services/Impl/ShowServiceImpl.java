package com.tvmaze.api.midd.services.Impl;




import com.tvmaze.api.midd.config.TvMazeApiClient;
import com.tvmaze.api.midd.dtos.CommentResponseDto;
import com.tvmaze.api.midd.dtos.ShowResponseDto;
import com.tvmaze.api.midd.models.ShowDocument;
import com.tvmaze.api.midd.repositories.CommentRepository;
import com.tvmaze.api.midd.repositories.ShowRepository;
import com.tvmaze.api.midd.services.ShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {
    private static final Logger log = LoggerFactory.getLogger(ShowServiceImpl.class);

    private final TvMazeApiClient apiClient;
    private final ShowRepository showRepository;
    private final CommentRepository commentRepository;

    //inyectamos por constructor
    public ShowServiceImpl(TvMazeApiClient apiClient, ShowRepository showRepository, CommentRepository commentRepository) {
        this.apiClient = apiClient;
        this.showRepository = showRepository;
        this.commentRepository = commentRepository;
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

                    //Update: Consultar comentarios en MongoDB para este show especifico
                    List<CommentResponseDto> comments = commentRepository.findByShowId(show.id())
                            .stream()
                            .map(c -> new CommentResponseDto(c.getComment(), c.getRating()))
                            .toList();

                    //Update: agregar el arreglo de comentarios a la respuesta
                    return new ShowResponseDto(
                            show.id(), show.name(), channel, show.summary(), show.genres(), comments
                    );
                }).toList();
    }

    // Busquda nueva por showId - Endpoint B
    public Map<String, Object> getShowById(Long showId) {
        Map<String, Object> showData;
        log.info("Buscando showId con ID: {}", showId);

        //validar el "cache" en la bd de mongo
        Optional<ShowDocument> cachedShow = showRepository.findById(showId);

        if (cachedShow.isPresent()) {
            log.info("ShowId {} encontrado en Mongo, Retornando caché...", showId);
            showData = cachedShow.get().getData();
        } else {
        log.info("Show {} no encontrado en Mongo. Consumiendo API externa...", showId);
        showData = apiClient.getShowById(showId);
        ShowDocument document = new ShowDocument(showId, showData);
        showRepository.save(document);
    }

    //Creamos un nuevo Map para evitar problemas de inmutabilidad
        Map<String, Object> finalResponse = new HashMap<>(showData);

        // Consultar los comentarios y agregarlos al objeto de retorno
        List<Map<String, Object>> commentsList = commentRepository.findByShowId(showId)
                .stream()
                .map(c -> {
                    Map<String, Object> commentMap = new HashMap<>();
                    commentMap.put("comment", c.getComment());
                    commentMap.put("rating", c.getRating());
                    return commentMap;
                })
                .toList();

        //Agregamos el arreglo de comentarios
        finalResponse.put("comments", commentsList);

        return finalResponse;
    }
}