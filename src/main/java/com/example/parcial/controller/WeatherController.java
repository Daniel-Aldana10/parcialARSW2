package com.example.parcial.controller;

import com.example.parcial.dto.ResponseDto;
import com.example.parcial.service.WeatherFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class WeatherController {
    @Value("${api_key}")
    private String apikey;
    private final WeatherFacade weatherFacade;

    public WeatherController(WeatherFacade weather) {
        this.weatherFacade = weather;
    }
    @GetMapping("/generate")
    public ResponseDto generate(@RequestParam String ciudad){
        return weatherFacade.generateResponse(ciudad);
    }

}
