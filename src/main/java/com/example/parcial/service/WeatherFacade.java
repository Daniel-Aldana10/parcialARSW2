package com.example.parcial.service;

import com.example.parcial.dto.ResponseDto;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class WeatherFacade {
    private final StringRedisTemplate redisTemplate;
    private final WeatherService weatherService;
    public WeatherFacade(StringRedisTemplate redisTemplate, WeatherService weatherService) {
        this.redisTemplate = redisTemplate;
        this.weatherService = weatherService;
    }

    public ResponseDto generateResponse(String ciudad) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String value = ops.get(ciudad);
        if(value == null){
            ResponseDto response = weatherService.generateResponse(ciudad).block();
            String dia = String.valueOf(LocalDateTime.now());
            redisTemplate.opsForValue().set(ciudad + dia,  response.getName() + " " + response.getCountry() + " " + response.getLat() +" " + response.getLon() +" " + response.getTemp_c() + " " +response.getText());
        return response;

        }
        String[] datos = value.split(" ");
        ResponseDto datosOp = new ResponseDto();
        if(datos.length == 6) {
            datosOp.setName(datos[0]);
            datosOp.setCountry(datos[1]);
            datosOp.setLat(Double.valueOf(datos[2]));
            datosOp.setLon(Double.valueOf(datos[3]));
            datosOp.setTemp_c(Double.valueOf(datos[4]));
            datosOp.setText(datos[5]);
        }
        return datosOp;
    }
}
