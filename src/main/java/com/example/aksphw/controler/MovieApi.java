package com.example.aksphw.controler;

import com.example.aksphw.model.Color;
import com.example.aksphw.model.Vehicle;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author andrzej.rzepecki
 **/


@RestController
@RequestMapping(value = "/vehicles",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class MovieApi {

    List<Vehicle> vehicleList = new ArrayList<>();

    public MovieApi() {
        this.vehicleList.add(new Vehicle(1,"Audi","A4", Color.BLACK));
        this.vehicleList.add(new Vehicle(1,"Opel","Vectra", Color.BLACK));
        this.vehicleList.add(new Vehicle(1,"Opel","Astra", Color.BLUE));
        this.vehicleList.add(new Vehicle(1,"Audi","A4", Color.BLACK));
        this.vehicleList.add(new Vehicle(1,"Audi","A4", Color.BLACK));
        this.vehicleList.add(new Vehicle(1,"Audi","A4", Color.BLACK));
        this.vehicleList.add(new Vehicle(1,"Audi","A4", Color.BLACK));
    }
}
