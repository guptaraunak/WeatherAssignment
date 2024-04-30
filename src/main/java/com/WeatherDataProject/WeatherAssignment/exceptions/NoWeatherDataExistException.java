package com.WeatherDataProject.WeatherAssignment.exceptions;

public class NoWeatherDataExistException extends RuntimeException{
    public NoWeatherDataExistException(String message) {
        super(message);
    }
}
