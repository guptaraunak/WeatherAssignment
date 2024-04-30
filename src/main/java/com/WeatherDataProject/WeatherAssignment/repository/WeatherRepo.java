package com.WeatherDataProject.WeatherAssignment.repository;

import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WeatherRepo {

    private static final Map<String, Weather> data=new HashMap<>();

    public Weather save(String city, Weather weather)
    {
        if(data.containsKey(city))
        {
            System.out.println("City data already present");
            return null;
        }

        data.put(city, weather);
        System.out.println("Weather data added successfully");
        weather.setCity(city);
        return weather;
    }

    public Weather findByCity(String city)
    {
        if(!data.containsKey(city))
        {
            System.out.println("City data not preset");
            return null;
        }

        return data.get(city);

    }

    public List<Weather> findAll()
    {
        List<Weather> weatherList=new ArrayList<>();

        if(data.isEmpty())
        {
            System.out.println("No data present");
            return null;
        }

        for(String city : data.keySet())
        {
            weatherList.add(data.get(city));
        }

        return weatherList;
    }

    public Weather update(String city, Weather weather)
    {
        if(!data.containsKey(city))
        {
            System.out.println("City data not present for updation");
            return null;
        }

        weather.setCity(city);
        data.put(city, weather);
        return data.get(city);
    }


    public boolean delete(String city)
    {
        if(!data.containsKey(city))
        {
            System.out.println("data not present for deletion");
            return false;
        }

        data.remove(city);
        return true;
    }

}
