package com.api.midd.tvmaze.dtos;


import java.util.List;


public record ShowResponseDto(
        Long id,
        String name,
        String channel,
        String summary,
        List<String> genres
) {}
