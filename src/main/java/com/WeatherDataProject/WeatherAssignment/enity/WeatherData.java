package com.WeatherDataProject.WeatherAssignment.enity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    @NotNull(message = "Temperature Cannot be Null Please Enter temperature value")
    private Long temp;

    private String desc;

    @NotNull(message = "Date cannot be null Please enter date")
    private Date date;

}
