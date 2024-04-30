package com.WeatherDataProject.WeatherAssignment.exceptions;

public class CityAlreadyExistException extends RuntimeException{

    public CityAlreadyExistException(String message) {
        super(message);
    }
}
