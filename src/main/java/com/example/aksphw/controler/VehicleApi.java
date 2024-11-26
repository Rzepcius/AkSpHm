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
    public static final String VEHICLE_ID = "vehicleId";
    public static final String VEHICLE_COLOR = "vehicleColor";
    private final VehicleService vehicleService;

    private Link link;

    public VehicleApi(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Vehicle>> getVehicles() {
        List<Vehicle> allVehicles = vehicleService.getAllVehicles();
        if (allVehicles == null || allVehicles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        allVehicles.stream().filter(vehicle -> !vehicle.hasLinks()).
                forEach(vehicle -> vehicle.
                        add(linkTo(VehicleApi.class).slash(vehicle.getId()).withRel(VEHICLE_ID)).
                        add(linkTo(VehicleApi.class).slash(vehicle.getColor()).withRel(VEHICLE_COLOR)));

        CollectionModel<Vehicle> vehicleCollectionModel = CollectionModel.of(allVehicles, linkTo(VehicleApi.class).withSelfRel());
        return new ResponseEntity<>(vehicleCollectionModel, HttpStatus.OK);
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<EntityModel<Vehicle>> getVechicleById(@PathVariable long vehicleId) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(vehicleId);
        List<Link> links = new ArrayList<>();
        if (vehicleById.isPresent()) {
            linkSingleEntry(vehicleById, links);
        }
        link = null;
        EntityModel<Vehicle> entityModel = EntityModel.of(vehicleById.get(), links);
        return new ResponseEntity<>(entityModel, HttpStatus.OK);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<CollectionModel<Vehicle>> getVehicleByColor(@PathVariable String color) {
        List<Vehicle> vehicleByColor = vehicleService.getVehicleByColor(color);
        vehicleByColor.stream().
                filter(vehicle -> !vehicle.hasLink(linkTo(VehicleApi.class).slash(vehicle.getId()).withRel(VEHICLE_ID).getRel())).
                forEach(vehicle -> vehicle.
                        add(linkTo(VehicleApi.class).slash(vehicle.getId()).withRel(VEHICLE_ID)));
        vehicleByColor.stream().
                filter(vehicle -> !vehicle.hasLink(linkTo(VehicleApi.class).slash("color").slash(vehicle.getColor()).withRel(VEHICLE_COLOR).getRel())).
                forEach(vehicle -> vehicle.
                        add(linkTo(VehicleApi.class).slash("color").slash(vehicle.getColor()).withRel(VEHICLE_COLOR)));
        CollectionModel<Vehicle> vehicleCollectionModel = CollectionModel.
                of(vehicleByColor, linkTo(VehicleApi.class).withSelfRel());
        if (vehicleByColor == null || vehicleByColor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicleCollectionModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addVehicle(@RequestBody Vehicle newVehicle) {
        Optional<Vehicle> foundVehicle = vehicleService.getAllVehicles().stream().
                filter(vehicle -> vehicle.equals(newVehicle)).findFirst();
        if (vehicleService.getAllVehicles().stream().anyMatch(vehicle -> vehicle.getId() == newVehicle.getId())) {
            return new ResponseEntity<>("Id already Used", HttpStatus.NOT_ACCEPTABLE);
        }
        if (foundVehicle.isEmpty()) {
            vehicleService.getAllVehicles().add(newVehicle);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Duplicate Entry", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PutMapping("/mod/{id}")
    public ResponseEntity<String> modVehicle(@PathVariable int id,@RequestBody Vehicle modVehicle) {
        Optional<Vehicle> foundVehicle = vehicleService.getAllVehicles().stream().
                filter(vehicle -> vehicle.getId() == modVehicle.getId()).findFirst();
        if (!foundVehicle.isEmpty()) {
            vehicleService.getAllVehicles().remove(foundVehicle.get());
            return this.addVehicle(modVehicle);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/mod/fields/{vehicleId}")
    public ResponseEntity<String> modField(@PathVariable int vehicleId,
                                           @RequestParam(required = false) Integer id,
                                           @RequestParam(required = false) String mark,
                                           @RequestParam(required = false) String model,
                                           @RequestParam(required = false) String color) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(vehicleId);
        if (vehicleById.isPresent()) {
            if (id != null) {
                if (vehicleService.getAllVehicles().stream().anyMatch(vehicle -> vehicle.getId() == id)) {
                    return new ResponseEntity<>("Id already Used", HttpStatus.NOT_ACCEPTABLE);
                } else {
                    vehicleById.get().setId(id);
                }
            }
            if (mark != null && !mark.isEmpty()) {
                vehicleById.get().setMark(mark);
            }
            if (model != null && !model.isEmpty()) {
                vehicleById.get().setModel(model);
            }
            if (color != null && !color.isEmpty()) {
                vehicleById.get().setColor(color);
            }
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<String> modField(@PathVariable int vehicleId){
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(vehicleId);
        if (vehicleById.isPresent()){
            if (vehicleService.getAllVehicles().remove(vehicleById.get())) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    private void linkSingleEntry(Optional<Vehicle> vehicleById, List<Link> links) {
        link = linkTo(VehicleApi.class).slash(vehicleById.get().getId()).withRel(VEHICLE_ID);
        if (!vehicleById.get().hasLink(link.getRel())) {
            links.add(link);
        }
        link = linkTo(VehicleApi.class).slash("color").slash(vehicleById.get().getColor()).withRel(VEHICLE_COLOR);
        if (!vehicleById.get().hasLink(link.getRel())) {
            links.add(link);
        }
    }
}
