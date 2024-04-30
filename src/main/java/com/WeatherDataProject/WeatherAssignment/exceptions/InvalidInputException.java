package com.WeatherDataProject.WeatherAssignment.exceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message) {
        super(message);
    }
}
