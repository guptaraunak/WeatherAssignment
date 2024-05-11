package com.WeatherDataProject.WeatherAssignment.repository;

import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface WeatherRepo extends MongoRepository<Weather, String> {

 //List<Weather> findAllByCity(String city);

 Weather findByCity(String city);

}
