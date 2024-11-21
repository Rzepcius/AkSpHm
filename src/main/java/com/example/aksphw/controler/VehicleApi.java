package com.example.aksphw.controler;

import com.example.aksphw.model.Vehicle;
import com.example.aksphw.service.VehicleService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<List<Vehicle>> getVehicles(){
            vehicleService.getAllVehicles().stream().filter(vehicle -> !vehicle.hasLinks()).forEach(vehicle -> vehicle.add(linkTo(VehicleApi.class).
                        slash(vehicle.getId()).withSelfRel()));
            vehicleService.getAllVehicles().stream().filter(vehicle -> !vehicle.hasLinks()).forEach(vehicle -> vehicle.add(linkTo(VehicleApi.class).
                        withSelfRel()));
//        CollectionModel<Vehicle> vehicleCollectionModel = CollectionModel.
//                of(vehicleService.getAllVehicles(),linkTo(VehicleApi.class).withSelfRel());
        return new ResponseEntity<>(vehicleService.getAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Vehicle>> getVechicleById(@PathVariable long id){
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(id);
        List<Link> links = new ArrayList<>();
        Link link = linkTo(VehicleApi.class).slash(id).withSelfRel();
        if (!vehicleById.get().hasLink(link.getRel())) {
            links.add(link);
        }
        link = linkTo(VehicleApi.class).withRel("allVehicles");
        if (!vehicleById.get().hasLink(link.getRel())) {
            links.add(linkTo(VehicleApi.class).withRel("allVehicles"));
        }

        EntityModel<Vehicle> entityModel = EntityModel.of(vehicleById.get(),links);
        return new ResponseEntity<>(entityModel,HttpStatus.OK);
    }
    @GetMapping("/color/{color}")
    public ResponseEntity<CollectionModel<Vehicle>> getVechicleByColor(@PathVariable String color){
        List<Vehicle> vehicleByColor = vehicleService.getVehicleByColor(color);
        vehicleByColor.forEach(vehicleColor ->vehicleColor.
                add(linkTo(VehicleApi.class).slash("color").slash(color).withSelfRel()).
                add(linkTo(VehicleApi.class).slash(vehicleColor.getId()).withRel("carId")));
        CollectionModel<Vehicle> vehicleCollectionModel = CollectionModel.
                of(vehicleByColor,linkTo(VehicleApi.class).withSelfRel());
        if (vehicleByColor.isEmpty()&&vehicleByColor==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicleCollectionModel,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addVehicle(@RequestBody Vehicle vehicle){
        vehicleService.getAllVehicles().add(vehicle);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
