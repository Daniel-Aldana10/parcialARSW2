package com.example.parcial.config;


import com.example.parcial.service.WeatherService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public  class Config{



    @Value("${api_key}")
    private String apiKey;


    @Bean
    public WeatherService weatherService(WebClient.Builder builder) {
        return new WeatherService(builder.build(), apiKey);
    }
}