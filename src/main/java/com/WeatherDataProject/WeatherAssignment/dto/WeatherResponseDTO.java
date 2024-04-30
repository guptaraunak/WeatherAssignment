package com.WeatherDataProject.WeatherAssignment.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class WeatherResponseDTO {

    private String city;
    private Long temp;
    private String desc;
}

