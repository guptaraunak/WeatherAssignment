package com.WeatherDataProject.WeatherAssignment.dto;

import lombok.Data;

import java.util.Date;

@Data
public class WeatherDataResponseDTO {

    private Long temp;
    private String desc;
    private Date date;

}
