package com.WeatherDataProject.WeatherAssignment.enity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Weather {

    private String city;
    private Long temp;
    private String desc;

}
