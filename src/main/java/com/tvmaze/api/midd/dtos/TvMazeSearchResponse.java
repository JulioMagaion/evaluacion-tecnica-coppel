package com.tvmaze.api.midd.dtos;


// El API de TV maze devuelve un arreglo de este objeto
public record TvMazeSearchResponse(Double score, TvMazeShow show) {}

