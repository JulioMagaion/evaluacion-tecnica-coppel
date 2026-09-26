package com.tvmaze.api.midd.controllers;



import com.tvmaze.api.midd.dtos.CommentRequestDto;
import com.tvmaze.api.midd.services.CommentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {

    private final CommentService commentService;

    //Inyectamos por constructor
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Void> addComment(@Valid @RequestBody CommentRequestDto request) {
        commentService.saveComment(request);
        // Retornamos el status de la petición 201 Createdcite: 1]
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}