package com.WeatherDataProject.WeatherAssignment.service;

import com.WeatherDataProject.WeatherAssignment.dto.WeatherResponseDTO;
import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import com.WeatherDataProject.WeatherAssignment.exceptions.CityAlreadyExistException;
import com.WeatherDataProject.WeatherAssignment.exceptions.CityNotPresentException;
import com.WeatherDataProject.WeatherAssignment.exceptions.InvalidInputException;
import com.WeatherDataProject.WeatherAssignment.exceptions.NoWeatherDataExistException;
import com.WeatherDataProject.WeatherAssignment.mapper.WeatherEntityDTOMapper;
import com.WeatherDataProject.WeatherAssignment.repository.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepo weatherRepo;

    @Override
    public WeatherResponseDTO addWeatherData(String city, Weather weather) {

        if(city==null)
        {
            throw  new InvalidInputException("City not entered");
        }

        if(weather==null)
        {
            throw new InvalidInputException("Weather data not entered");
        }

        Weather weather1=weatherRepo.findByCity(city);

        if(weather1!=null)
        {
            throw new CityAlreadyExistException("Weather data of city " +city+ " already exists");
        }

        return WeatherEntityDTOMapper.convertWeatherEnityToWeatherResponseDTO(weatherRepo.save(city, weather));

    }

    @Override
    public WeatherResponseDTO getDataByCity(String city) {

        if(city==null)
        {
            throw  new InvalidInputException("City not entered");
        }

        Weather weather=weatherRepo.findByCity(city);

        if(weather==null)
        {
            throw new CityNotPresentException("Weather data of city" +city+ "does not exist");
        }

        return WeatherEntityDTOMapper.convertWeatherEnityToWeatherResponseDTO(weatherRepo.findByCity(city));
    }

    @Override
    public List<WeatherResponseDTO> getAllData() {

        List<Weather> weatherList=weatherRepo.findAll();

        if(weatherList==null)
        {
            throw new NoWeatherDataExistException("No Weather data is present");
        }

        List<WeatherResponseDTO> list=new ArrayList<>();

        for (Weather weather : weatherList)
        {
            list.add(WeatherEntityDTOMapper.convertWeatherEnityToWeatherResponseDTO(weatherRepo.findByCity(weather.getCity())));
        }

        return list;
    }

    @Override
    public WeatherResponseDTO updateDataByCity(String city, Weather weather) {

        if(city==null)
        {
            throw  new InvalidInputException("City not entered");
        }

        if(weather==null)
        {
            throw new InvalidInputException("Weather data not entered");
        }

        Weather weather1=weatherRepo.findByCity(city);

        if(weather1==null)
        {
            throw new CityNotPresentException("Weather data of city" +city+ "does not exist so updation cannot be processed");
        }

        return WeatherEntityDTOMapper.convertWeatherEnityToWeatherResponseDTO(weatherRepo.update(city, weather));

    }

    @Override
    public boolean deleteWeatherData(String city) {

        if(city==null)
        {
            throw new InvalidInputException("City not entered");
        }

        Weather weather=weatherRepo.findByCity(city);

        if(weather==null)
        {
            throw new CityNotPresentException("Data of the city" +city+ "does not exist");
        }

        weatherRepo.delete(city);
        return true;
    }
}
