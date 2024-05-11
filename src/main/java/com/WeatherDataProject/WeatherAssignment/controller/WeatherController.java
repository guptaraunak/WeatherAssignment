package com.WeatherDataProject.WeatherAssignment.controller;

import com.WeatherDataProject.WeatherAssignment.dto.WeatherDataResponseDTO;
import com.WeatherDataProject.WeatherAssignment.dto.WeatherResponseDTO;
import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import com.WeatherDataProject.WeatherAssignment.enity.WeatherData;
import com.WeatherDataProject.WeatherAssignment.service.WeatherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    WeatherService weatherService;

    @PostMapping
    public ResponseEntity<WeatherResponseDTO> saveWeather(@Valid @RequestBody Weather weather)
    {
        WeatherResponseDTO createdData=weatherService.saveWeather(weather);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdData);
    }

    @GetMapping("/getWeatherByCity/{city}")
    public ResponseEntity<WeatherResponseDTO> getWeatherByCity(@Valid @PathVariable("city") String city)
    {
        WeatherResponseDTO createdData=weatherService.getWeatherByCity(city);
        return ResponseEntity.ok(createdData);
    }

    @GetMapping("/getAllWeather")
    public  ResponseEntity<List<WeatherResponseDTO>> getAllWeatherEntityData()
    {
        List<WeatherResponseDTO> createdDataList=weatherService.getAllData();
        return ResponseEntity.ok(createdDataList);
    }

    @GetMapping("/getWeatherDataByCityAndDateRange/{city}/history")
    public ResponseEntity<List<WeatherDataResponseDTO>> getWeatherDataByDate(@Valid @PathVariable("city") String city, @RequestParam("start_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam("end_date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate)
    {
        List<WeatherDataResponseDTO> createdDataList=weatherService.findByDate(city, startDate, endDate);
        return ResponseEntity.ok(createdDataList);
    }

    @GetMapping("/getSortedWeatherDataByCity/{city}/")
    public  ResponseEntity<List<WeatherDataResponseDTO>> getSortedWeatherData(@Valid @PathVariable("city") String city, @RequestParam("sort_by") String sortBy, @RequestParam("order") String order)
    {
        List<WeatherDataResponseDTO> createdDataList=weatherService.getSortedWeatherData(city, sortBy, order);
        return ResponseEntity.ok(createdDataList);
    }

    @PutMapping("/updateWeatherDataByCityAndDate/{city}/")
    public ResponseEntity<WeatherDataResponseDTO> updateWeatherDataByCityAndDate(@Valid @PathVariable("city") String city, @RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date, @Valid @RequestBody WeatherData weatherData)
    {
        WeatherDataResponseDTO updatedData=weatherService.updateWeatherDataByCityAndDate(city, date, weatherData);
        return ResponseEntity.ok(updatedData);
    }

    @PutMapping("/addWeatherDataToWeatherListByCity/{city}")
    public ResponseEntity<String> addWeatherDataToWeatherListByCity(@Valid @PathVariable("city") String city, @Valid @RequestBody WeatherData weatherData)
    {
        weatherService.addWeatherDataToWeatherListByCity(city, weatherData);
        return ResponseEntity.ok("Weather Data Added Successfully");
    }

    @DeleteMapping("/deleteWeatherByCity/{city}")
    public ResponseEntity<String> deleteWeatherDataByCity(@Valid @PathVariable("city") String city)
    {
        weatherService.deleteWeather(city);

        String ans="Weather data of city "+city+" deleted successfully";

        return ResponseEntity.ok(ans);
    }

    @DeleteMapping("/deleteWeatherDataByCityAndDate/{city}/")
    public ResponseEntity<String> deleteWeatherDataByCityAndDate(@Valid @PathVariable("city") String city, @RequestParam("date") LocalDate date)
    {
        weatherService.deleteWeatherDataBYCityAndDate(city, date);

        String response="Weather data of city "+city+" with date "+date+" deleted successfully";
        return ResponseEntity.ok(response);
    }

}
