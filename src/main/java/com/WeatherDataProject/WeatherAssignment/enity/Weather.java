package com.WeatherDataProject.WeatherAssignment.enity;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import java.util.ArrayList;
import java.util.List;

@EntityScan
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    @Id
    private String id;

    @NotNull(message = "City cannot be null Please enter city")
    private String city;

    @Valid
    private List<WeatherData> weatherDataList=new ArrayList<>();
}
