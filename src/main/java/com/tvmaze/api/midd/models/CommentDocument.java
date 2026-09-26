package com.tvmaze.api.midd.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "comments")
public class CommentDocument {

    @Id
    private String id; // Mongo genera un String alfanumérico automáticamente
    private Long showId;
    private String comment;
    private Integer rating;

    public CommentDocument() {}

    public CommentDocument(Long showId, String comment, Integer rating) {
        this.showId = showId;
        this.comment = comment;
        this.rating = rating;
    }

    //Getters y Setters para todos los campos (id, showId, comment, rating)
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Long getShowId() { return showId; }
    public void setShowId(Long showId) { this.showId = showId; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
}