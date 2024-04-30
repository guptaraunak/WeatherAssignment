package com.WeatherDataProject.WeatherAssignment.mapper;

import com.WeatherDataProject.WeatherAssignment.dto.WeatherResponseDTO;
import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import org.springframework.beans.factory.annotation.Autowired;

public class WeatherEntityDTOMapper {

    public  static WeatherResponseDTO convertWeatherEnityToWeatherResponseDTO(Weather weather)
    {
        WeatherResponseDTO weatherResponseDTO=new WeatherResponseDTO();

        weatherResponseDTO.setCity(weather.getCity());
        weatherResponseDTO.setTemp(weather.getTemp());
        weatherResponseDTO.setDesc(weather.getDesc());

        return weatherResponseDTO;
    }
}
