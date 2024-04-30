package com.WeatherDataProject.WeatherAssignment.service;

import com.WeatherDataProject.WeatherAssignment.dto.WeatherResponseDTO;
import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherService {
    WeatherResponseDTO addWeatherData(String city, Weather weather);

    WeatherResponseDTO getDataByCity(String city);

    List<WeatherResponseDTO> getAllData();

    WeatherResponseDTO updateDataByCity(String city, Weather weather);

    boolean deleteWeatherData(String city);
}
