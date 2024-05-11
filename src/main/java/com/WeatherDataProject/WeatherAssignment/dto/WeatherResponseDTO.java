package com.WeatherDataProject.WeatherAssignment.dto;

import com.WeatherDataProject.WeatherAssignment.enity.WeatherData;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class WeatherResponseDTO {

    private String city;
    private List<WeatherDataResponseDTO> weatherDataResponseDTOList=new ArrayList<>();

}

