package com.api.midd.tvmaze.controllers;




import com.api.midd.tvmaze.dtos.ShowResponseDto;
import com.api.midd.tvmaze.services.ShowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
public class ShowController {
    //Uso de SL4J para escribir en consola
    private static final Logger log = LoggerFactory.getLogger(ShowController.class);

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<ShowResponseDto>> searchShows(
            @RequestParam("search_query") String searchQuery) {
        log.info("petición recibida de busqueda para el search_query: {}", searchQuery);

        List<ShowResponseDto> response = showService.searchShows(searchQuery);
        return ResponseEntity.ok(response);
    }
}