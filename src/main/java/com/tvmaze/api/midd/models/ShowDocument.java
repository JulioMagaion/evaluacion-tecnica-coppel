package com.tvmaze.api.midd.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "shows_cache")
public class ShowDocument {

    @Id
    private Long id;

    //guardar el JSON completo que nos devuelve TV-Maze
    private Map<String, Object> data;

    public ShowDocument() {
    }

    public ShowDocument(Long id, Map<String, Object> data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
