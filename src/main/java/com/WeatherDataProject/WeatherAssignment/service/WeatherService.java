package com.WeatherDataProject.WeatherAssignment.service;

import com.WeatherDataProject.WeatherAssignment.dto.WeatherDataResponseDTO;
import com.WeatherDataProject.WeatherAssignment.dto.WeatherResponseDTO;
import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import com.WeatherDataProject.WeatherAssignment.enity.WeatherData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface WeatherService {

    List<WeatherResponseDTO> getAllData();

    WeatherDataResponseDTO updateWeatherDataByCityAndDate(String city, Date date, WeatherData weatherData);

    void deleteWeather(String city);

    WeatherResponseDTO saveWeather(Weather weather);

    WeatherResponseDTO getWeatherByCity(String city);

    List<WeatherDataResponseDTO> findByDate(String city, Date startDate, Date endDate);

    List<WeatherDataResponseDTO> getSortedWeatherData(String city, String sortBy, String order);

    void addWeatherDataToWeatherListByCity(String city, WeatherData weatherData);

    void deleteWeatherDataBYCityAndDate(String city, LocalDate date);
}
