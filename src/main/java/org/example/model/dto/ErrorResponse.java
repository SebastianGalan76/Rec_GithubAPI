package org.example.model.dto;

public record ErrorResponse(
        int status,
        String message
) { }
