package com.example.aksphw;

import com.example.aksphw.model.Country;
import com.example.aksphw.model.Weather;
import com.example.aksphw.repository.CountryRepository;
import com.example.aksphw.repository.WeatherRepository;
import org.aspectj.weaver.World;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class Start {

    private CountryRepository countryRepository;
    private WeatherRepository weatherRepository;

    public Start(CountryRepository countryRepository, WeatherRepository weatherRepository) {
        this.countryRepository = countryRepository;
        this.weatherRepository = weatherRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start() {
        Country country = new Country("Poland","Warsaw");
        Country country2 = new Country("Poland","Wroclaw");
        Country country3 = new Country("Poland","Lodz");
        Country country4 = new Country("USA","Warsaw");
        Country country5 = new Country("Poland","Warsaw");

        countryRepository.save(country);
        countryRepository.save(country2);
        countryRepository.save(country3);
        countryRepository.save(country4);
        countryRepository.save(country5);

        Weather weather = new Weather();
    }
}
