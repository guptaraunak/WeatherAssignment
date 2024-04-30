package com.WeatherDataProject.WeatherAssignment.dto;

import lombok.Data;

@Data
public class ExceptionResponseDTO {

private String message;
private int code;

    public ExceptionResponseDTO(String message, int code) {
        this.message = message;
        this.code = code;
    }
}
