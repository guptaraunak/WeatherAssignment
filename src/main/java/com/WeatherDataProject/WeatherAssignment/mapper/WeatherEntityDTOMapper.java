package com.WeatherDataProject.WeatherAssignment.mapper;

import com.WeatherDataProject.WeatherAssignment.dto.WeatherDataResponseDTO;
import com.WeatherDataProject.WeatherAssignment.dto.WeatherResponseDTO;
import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import com.WeatherDataProject.WeatherAssignment.enity.WeatherData;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class WeatherEntityDTOMapper {

    public static WeatherResponseDTO convertWeatherEnityToWeatherResponseDTO(Weather weather) {
        WeatherResponseDTO weatherResponseDTO = new WeatherResponseDTO();

        weatherResponseDTO.setCity(weather.getCity());

        List<WeatherDataResponseDTO> weatherDataResponseDTOList1 = new ArrayList<>();

        for (WeatherData weatherData : weather.getWeatherDataList()) {
            weatherDataResponseDTOList1.add(covertWeatherDatatoWeatherDataResponseDTO(weatherData));
        }

        weatherResponseDTO.setWeatherDataResponseDTOList(weatherDataResponseDTOList1);

        return weatherResponseDTO;
    }

    public static WeatherDataResponseDTO covertWeatherDatatoWeatherDataResponseDTO(WeatherData weatherData) {
        WeatherDataResponseDTO weatherDataResponseDTO = new WeatherDataResponseDTO();

        weatherDataResponseDTO.setTemp(weatherData.getTemp());
        weatherDataResponseDTO.setDesc(weatherData.getDesc());
        weatherDataResponseDTO.setDate(weatherData.getDate());

        return weatherDataResponseDTO;

    }

}

