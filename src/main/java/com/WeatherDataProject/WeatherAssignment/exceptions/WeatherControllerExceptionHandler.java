package com.WeatherDataProject.WeatherAssignment.exceptions;


import com.WeatherDataProject.WeatherAssignment.controller.WeatherController;
import com.WeatherDataProject.WeatherAssignment.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = WeatherController.class)
public class WeatherControllerExceptionHandler {

    @ExceptionHandler(CityNotPresentException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCityNotPresentException(CityNotPresentException cnp)
    {
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(cnp.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDTO);
    }

    @ExceptionHandler(CityAlreadyExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleCityAlreadyExistException(CityAlreadyExistException ce)
    {
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(ce.getMessage(), HttpStatus.NOT_ACCEPTABLE.value());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exceptionResponseDTO);
    }

    @ExceptionHandler(NoWeatherDataExistException.class)
    public ResponseEntity<ExceptionResponseDTO> handleNoWeatherDataExistException(NoWeatherDataExistException nd)
    {
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(nd.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDTO);
    }

    @ExceptionHandler(InvalidInputException.class)
    public  ResponseEntity<ExceptionResponseDTO> handleInvalidInputException(InvalidInputException ie)
    {
        ExceptionResponseDTO exceptionResponseDTO=new ExceptionResponseDTO(ie.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponseDTO);
    }

}
