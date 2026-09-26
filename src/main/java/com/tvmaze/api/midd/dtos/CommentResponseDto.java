package com.tvmaze.api.midd.dtos;


public record CommentResponseDto(
        String comment,
        Integer rating
) {}