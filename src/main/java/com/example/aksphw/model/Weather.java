package com.example.aksphw.model;

import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "weathers")
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "City")
    private String cityName;
    @Column(name = "Country")
    private String countryName;
    private BigDecimal temperature;
    private BigDecimal humidity;
    private LocalTime time;
    private LocalDate date;

    public Weather() {
    }

    public Weather(String cityName, String countryName, BigDecimal temperature, BigDecimal humidity, LocalTime time, LocalDate date) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.temperature = temperature;
        this.humidity = humidity;
        this.time = time;
        this.date = date;
    }

    public String cityName() {
        return cityName;
    }

    public Weather setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String countryName() {
        return countryName;
    }

    public Weather setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    public BigDecimal temperature() {
        return temperature;
    }

    public Weather setTemperature(BigDecimal temperature) {
        this.temperature = temperature;
        return this;
    }

    public BigDecimal humidity() {
        return humidity;
    }

    public Weather setHumidity(BigDecimal humidity) {
        this.humidity = humidity;
        return this;
    }

    public LocalTime time() {
        return time;
    }

    public Weather setTime(LocalTime time) {
        this.time = time;
        return this;
    }

    public LocalDate date() {
        return date;
    }

    public Weather setDate(LocalDate date) {
        this.date = date;
        return this;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                ", temperature=" + temperature +
                ", humidity=" + humidity +
                ", time=" + time +
                ", date=" + date +
                '}';
    }
}
