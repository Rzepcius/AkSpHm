package com.example.aksphw.model;


import jakarta.persistence.*;

@Entity(name = "Countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "Country")
    private String countryName;
    @Column(name = "City")
    private String cityName;

    public Country() {
    }

    public Country(String countryName, String cityName) {
        this.countryName = countryName;
        this.cityName = cityName;
    }

    public String cityName() {
        return cityName;
    }

    public Country setCityName(String cityName) {
        this.cityName = cityName;
        return this;
    }

    public String countryName() {
        return countryName;
    }

    public Country setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", cityName='" + cityName + '\'' +
                ", countryName='" + countryName + '\'' +
                '}';
    }
}
