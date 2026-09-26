package com.tvmaze.api.midd.controllers;




import com.tvmaze.api.midd.dtos.ShowResponseDto;
import com.tvmaze.api.midd.services.ShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowController {
    //Uso de SL4J para escribir en consola
    private static final Logger log = LoggerFactory.getLogger(ShowController.class);

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }


    //Metodo por busqueda search_query Endopoint A
    @GetMapping("/search")
    public ResponseEntity<List<ShowResponseDto>> searchShows(
            @RequestParam("search_query") String searchQuery) {
        log.info("petición recibida de busqueda para el search_query: {}", searchQuery);

        List<ShowResponseDto> response = showService.searchShows(searchQuery);
        return ResponseEntity.ok(response);
    }

    //Metodo por busqueda showId Endpoint B
    @GetMapping("/{show_id}")
    public ResponseEntity<Map<String, Object>> getShowById(@PathVariable("show_id") Long showId) {
        log.info("Entra en el controller Show para obtener ID: {}", showId);

        //llamamos el servicio para obtener la información del show a partir de su Id
        Map<String, Object> response = showService.getShowById(showId);

        // Retorna el objeto show completo
        return ResponseEntity.ok(response);
    }
}