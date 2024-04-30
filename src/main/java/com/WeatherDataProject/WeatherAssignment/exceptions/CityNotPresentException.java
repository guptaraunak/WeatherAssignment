package com.WeatherDataProject.WeatherAssignment.exceptions;

public class CityNotPresentException extends RuntimeException{

    public CityNotPresentException(String message) {
        super(message);
    }
}
