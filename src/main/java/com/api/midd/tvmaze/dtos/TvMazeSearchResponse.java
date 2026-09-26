package com.api.midd.tvmaze.dtos;


// El API de TV maze devuelve un arreglo de este objeto
public record TvMazeSearchResponse(Double score, TvMazeShow show) {}

