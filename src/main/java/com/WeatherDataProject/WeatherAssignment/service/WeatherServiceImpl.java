package com.WeatherDataProject.WeatherAssignment.service;

import com.WeatherDataProject.WeatherAssignment.dto.WeatherDataResponseDTO;
import com.WeatherDataProject.WeatherAssignment.dto.WeatherResponseDTO;
import com.WeatherDataProject.WeatherAssignment.enity.Weather;
import com.WeatherDataProject.WeatherAssignment.enity.WeatherData;
import com.WeatherDataProject.WeatherAssignment.exceptions.*;
import com.WeatherDataProject.WeatherAssignment.mapper.WeatherEntityDTOMapper;
import com.WeatherDataProject.WeatherAssignment.repository.WeatherRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    WeatherRepo weatherRepo;

    @Override
    public List<WeatherResponseDTO> getAllData() {

        List<Weather> weatherList=weatherRepo.findAll();

        if(weatherList.isEmpty())
        {
            throw new NoWeatherDataExistException("No Weather data is present");
        }

        List<WeatherResponseDTO> list=new ArrayList<>();

        for (Weather weather : weatherList)
        {
            list.add(WeatherEntityDTOMapper.convertWeatherEnityToWeatherResponseDTO(weather));
        }

        return list;
    }

    @Override
    public WeatherDataResponseDTO updateWeatherDataByCityAndDate(String city, Date date, WeatherData weatherData) {

        if(city==null)
        {
            throw  new InvalidInputException("City not entered");
        }

        if(weatherData==null)
        {
            throw new InvalidInputException("Weather data not entered");
        }

        Weather weather=weatherRepo.findByCity(city);

        if(weather==null)
        {
            throw new CityNotPresentException("Weather data of city " +city+ " does not exist so updation cannot be processed");
        }

        List<WeatherData> weatherDataList=weather.getWeatherDataList();

        if(weatherDataList==null)
            throw new NoWeatherDataExistException("No Weather Data of this city exist");

        WeatherData weatherDataAfterChange=weatherDataList.stream().
                filter(wd->wd.getDate().equals(weatherData.getDate())).findFirst().
                orElseThrow(()->new NoWeatherDataExistException("No Weather Data of this date exist"));

        if(weatherData.getTemp()!=null)
            weatherDataAfterChange.setTemp(weatherData.getTemp());

        if(weatherData.getDesc()!=null)
            weatherDataAfterChange.setDesc((weatherData.getDesc()));

        weatherRepo.save(weather);

        return WeatherEntityDTOMapper.covertWeatherDatatoWeatherDataResponseDTO(weatherDataAfterChange);
    }

    @Override
    public void deleteWeather(String city) {

        if(city==null)
        {
            throw new InvalidInputException("City not entered");
        }

        Weather weather=weatherRepo.findByCity(city);

        if(weather==null)
        {
            throw new CityNotPresentException("Data of the city" +city+ "does not exist");
        }

        weatherRepo.delete(weather);
    }

    @Override
    public WeatherResponseDTO saveWeather(Weather weather) {

        if(weather.getCity()==null)
            throw new InvalidInputException("City cannot be null");

        Weather weather1=weatherRepo.findByCity(weather.getCity());

        if(weather1!=null)
            throw new CityAlreadyExistException("Data of this City Already exist");

        return WeatherEntityDTOMapper.convertWeatherEnityToWeatherResponseDTO(weatherRepo.save(weather));
    }

    @Override
    public WeatherResponseDTO getWeatherByCity(String city) {

        if(city==null)
            throw new InvalidInputException("City Not Present");

        Weather weather = weatherRepo.findByCity(city);

        if(weather==null)
            throw new CityNotPresentException("No Weather Data of this city is Present");

        return WeatherEntityDTOMapper.convertWeatherEnityToWeatherResponseDTO(weather);
    }

    @Override
    public List<WeatherDataResponseDTO> findByDate(String city, Date startDate, Date endDate) {

        if(city==null)
            throw new InvalidInputException("City cannot be null");

        Weather weather=weatherRepo.findByCity(city);

        if (weather==null)
            throw new CityNotPresentException("No Weather record of this city Exist");

        List<WeatherData> weatherDataList=weather.getWeatherDataList();

        if (weatherDataList==null)
            throw new NoWeatherDataExistException("No Weather data of this city exists");

        List<WeatherDataResponseDTO> weatherDataResponseDTOList=new ArrayList<>();

        for (WeatherData weatherData : weatherDataList)
        {
            if(weatherData.getDate().after(startDate) && weatherData.getDate().before(endDate))
                weatherDataResponseDTOList.add(WeatherEntityDTOMapper.covertWeatherDatatoWeatherDataResponseDTO(weatherData));
        }

        return weatherDataResponseDTOList;
    }

    @Override
    public List<WeatherDataResponseDTO> getSortedWeatherData(String city, String sortBy, String order) {

        if(city==null)
            throw new InvalidInputException("City Cannot be null");

        Weather weather=weatherRepo.findByCity(city);

        if(weather==null)
            throw new CityNotPresentException("This City does not exist in Weather database");

        List<WeatherData> weatherDataList=weather.getWeatherDataList();

        if(weatherDataList==null)
            throw new NoWeatherDataExistException("No Weather Data of this exist");

        if (sortBy != null) {

            if (sortBy.equals("temp"))
            {
                 weatherDataList.sort((a, b) -> Long.compare(a.getTemp(), b.getTemp()));
            }

            else
                throw new RuntimeException("Parameter does not match with allowed value");

        }

        if(order!=null)
        {
           if(order.equals("desc"))
               Collections.reverse(weatherDataList);
           else if (order.equals("asc")) {
               System.out.println("List already sorted");
           }
           else
               throw new RuntimeException("Parameter does not match with allowed value");
        }

        List<WeatherDataResponseDTO> weatherDataResponseDTOList=new ArrayList<>();

        for(WeatherData weatherData : weatherDataList)
            weatherDataResponseDTOList.add(WeatherEntityDTOMapper.covertWeatherDatatoWeatherDataResponseDTO(weatherData));

        return weatherDataResponseDTOList;
    }

    @Override
    public void addWeatherDataToWeatherListByCity(String city, WeatherData weatherData) {

        if(city==null)
            throw new InvalidInputException("City Cannot be null");

        if(weatherData==null)
            throw new InvalidInputException("Weather Data cannot be null");

        Weather weather=weatherRepo.findByCity(city);

        if(weather==null)
            throw new CityNotPresentException("City does not exist");

        List<WeatherData> weatherDataList=weather.getWeatherDataList();

        boolean sameDateWeatherData=weatherDataList.stream().anyMatch(wd->wd.getDate().equals(weatherData.getDate()));

        if(sameDateWeatherData)
            throw new WeatherDataDateAlreadyExistsException("Same Date Weather Data Already Exist");

        weatherDataList.add(weatherData);

        weather.setWeatherDataList(weatherDataList);

        weatherRepo.save(weather);
    }

    @Override
    public void deleteWeatherDataBYCityAndDate(String city, LocalDate date) {

        if(city==null)
            throw new InvalidInputException("City Cannot be null");

        if(date==null)
            throw new InvalidInputException("Date Cannot be null");

        Weather weather=weatherRepo.findByCity(city);

        if (weather==null)
            throw new CityNotPresentException("City does not exist");

        List<WeatherData> weatherDataList=weather.getWeatherDataList();

        if (weatherDataList==null)
            throw new NoWeatherDataExistException("No Weather Data of this City Exist");


//        WeatherData weatherData=weatherDataList.stream().filter(wd->wd.getDate().equals(date)).findAny().
//                orElseThrow(() -> new NoWeatherDataExistException("No Weather Data for this date exist"));

        WeatherData weatherData2=null;

        for(WeatherData weatherData1:weatherDataList)
        {
            Date date1 = weatherData1.getDate();

            LocalDate localDate = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if(localDate.equals(date))
            {
                weatherData2=weatherData1;
                break;
            }
        }

        if(weatherData2==null)
            throw  new RuntimeException("No Record of the given date exist");

        weatherDataList.remove(weatherData2);

        weather.setWeatherDataList(weatherDataList);

        weatherRepo.save(weather);
    }

}
