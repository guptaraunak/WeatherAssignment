package com.WeatherDataProject.WeatherAssignment.controller;

import com.WeatherDataProject.WeatherAssignment.dto.WeatherResponseDTO;
import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import com.WeatherDataProject.WeatherAssignment.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @PostMapping("/{city}")
    public ResponseEntity addWeather(@PathVariable("city") String city, @RequestBody Weather weather)
    {
        WeatherResponseDTO createdData=weatherService.addWeatherData(city, weather);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdData);
    }

    @GetMapping("/{city}")
    public ResponseEntity getWeatherByCity(@PathVariable("city") String city)
    {
        WeatherResponseDTO createdData=weatherService.getDataByCity(city);
        return ResponseEntity.ok(createdData);
    }

    @GetMapping
    public  ResponseEntity getAllWeatherData()
    {
        List<WeatherResponseDTO> createdDataList=weatherService.getAllData();
        return ResponseEntity.ok(createdDataList);
    }

    @PutMapping("/{city}")
    public ResponseEntity updateWeatherDataByCity(@PathVariable("city") String city, @RequestBody Weather weather)
    {
        WeatherResponseDTO updatedData=weatherService.updateDataByCity(city, weather);
        return ResponseEntity.ok(updatedData);
    }

    @DeleteMapping("/{city}")
    public ResponseEntity deleteWeatherDataByCity(@PathVariable("city") String city)
    {
        weatherService.deleteWeatherData(city);

        String ans="Weather data of city "+city+" deleted successfully";

        return ResponseEntity.ok(ans);
    }

}
