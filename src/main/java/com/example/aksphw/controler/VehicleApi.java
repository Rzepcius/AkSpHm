package com.example.aksphw.controler;

import com.example.aksphw.model.Vehicle;
import com.example.aksphw.service.VehicleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * @author andrzej.rzepecki
 **/


@RestController
@RequestMapping(value = "/vehicles",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class VehicleApi {

    private final VehicleService vehicleService;

    public VehicleApi(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

        @GetMapping
    public ResponseEntity<CollectionModel<Vehicle>> getVehicles(){
            vehicleService.getAllVehicles().forEach(vehicle -> vehicle.add(linkTo(VehicleApi.class).
                        slash(vehicle.getId()).withSelfRel()));
        CollectionModel<Vehicle> vehicleCollectionModel = CollectionModel.
                of(vehicleService.getAllVehicles(),linkTo(VehicleApi.class).withSelfRel());
        return new ResponseEntity<>(vehicleCollectionModel, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollectionModel<Vehicle>> getVechicleById(@PathVariable long id){
        vehicleService.getAllVehicles().forEach(vehicle -> vehicle.add(linkTo(VehicleApi.class).
                slash(vehicle.getId()).withSelfRel()));
        CollectionModel<Vehicle> vehicleCollectionModel = CollectionModel.
                of(vehicleService.getAllVehicles(),linkTo(VehicleApi.class).withSelfRel());
        return new ResponseEntity<>(vehicleCollectionModel,HttpStatus.OK);
    }
    @GetMapping("/color/{color}")
    public ResponseEntity<List<Vehicle>> getVechicleById(@PathVariable String color){

        return new ResponseEntity<>(vehicleService.getVehicleByColor(color),HttpStatus.OK);
    }
}
