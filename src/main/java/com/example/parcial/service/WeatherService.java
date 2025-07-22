package com.example.parcial.service;

import com.example.parcial.dto.ResponseDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class WeatherService {
    private final WebClient webClient;
    private final String apiKey;

    public WeatherService(WebClient webClient, String apiKey) {
        this.webClient = webClient;
        this.apiKey = apiKey;
    }

    public Mono<ResponseDto> generateResponse(String empresa) {
        try {
            return webClient.get()
                    .uri("https://api.weatherapi.com/v1/current.json?key="+  apiKey  + "&q=" + empresa)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(response -> {
                        Map<String, Object> choices = (Map<String, Object>) response.get("location");
                        String name= (String) choices.get("name");
                        String country = (String) choices.get("country");
                        Double lat = (Double) choices.get("lat");
                        Double lon = (Double) choices.get("lon");
                        Map<String, Object> current = (Map<String, Object>) response.get("current");
                        Double temp_c = (Double) current.get("temp_c");
                        Map<String, Object> condition = (Map<String, Object>) current.get("condition");
                        String hola = (String) condition.get("text");
                        ResponseDto dto = new ResponseDto();
                        dto.setName(name);
                        dto.setCountry(country);
                        dto.setLat(lat);
                        dto.setLon(lon);
                        dto.setTemp_c(temp_c);
                        dto.setText(hola);


                return dto;
            });

        } catch (Exception e) {
            throw new RuntimeException("Error llamando a la api " + e.getMessage());
        }
    }
}
