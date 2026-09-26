package com.tvmaze.api.midd.services;


import com.tvmaze.api.midd.dtos.CommentRequestDto;

public interface CommentService {
    void saveComment(CommentRequestDto request);
}