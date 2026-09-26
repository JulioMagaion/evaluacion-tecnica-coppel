package com.tvmaze.api.midd.dtos;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentRequestDto(
        @NotNull(message = "El show_id es obligatorio")
        Long show_id,

        @NotBlank(message = "El comentario no puede estar vacío")
        String comment,

        @NotNull(message = "El rating es obligatorio")
        @Min(value = 0, message = "El rating mínimo es 0")
        @Max(value = 5, message = "El rating máximo es 5")
        Integer rating
) {}