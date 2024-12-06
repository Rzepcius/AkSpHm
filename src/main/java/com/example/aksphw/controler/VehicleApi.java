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
    public static final String ALL_VEHICLES = "allVehicles";
    public static final String COLOR_URL = "color";
    private final VehicleService vehicleService;

    public VehicleApi(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Vehicle>> getVehicles() {
        List<Vehicle> allVehicles = vehicleService.getAllVehicles();
        if (allVehicles == null || allVehicles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        linkCollection(allVehicles);

        CollectionModel<Vehicle> vehicleCollectionModel = CollectionModel.of(allVehicles, linkTo(VehicleApi.class).withSelfRel());
        return new ResponseEntity<>(vehicleCollectionModel, HttpStatus.OK);
    }

    private static void linkCollection(List<Vehicle> allVehicles) {
        allVehicles.stream().filter(vehicle -> !vehicle.hasLinks()).
                forEach(vehicle -> vehicle.
                        add(linkTo(VehicleApi.class).slash(vehicle.getId()).withRel(VEHICLE_ID)).
                        add(linkTo(VehicleApi.class).slash(vehicle.getColor()).withRel(VEHICLE_COLOR)).
                        add(linkTo(VehicleApi.class).withRel(ALL_VEHICLES)));
    }

    @GetMapping("/{vehicleId}")
    public ResponseEntity<EntityModel<Vehicle>> getVechicleById(@PathVariable long vehicleId) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(vehicleId);
        List<Link> links = new ArrayList<>();
        if (vehicleById.isPresent()) {
            linkSingleEntry(vehicleById.get(), links);
            EntityModel<Vehicle> entityModel = EntityModel.of(vehicleById.get(), links);
            return new ResponseEntity<>(entityModel, HttpStatus.OK);
        }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<CollectionModel<Vehicle>> getVehicleByColor(@PathVariable String color) {
        List<Vehicle> vehicleByColor = vehicleService.getVehicleByColor(color);
        linkCollection(vehicleByColor);
        CollectionModel<Vehicle> vehicleCollectionModel = CollectionModel.
                of(vehicleByColor, linkTo(VehicleApi.class).withSelfRel());
        if (vehicleByColor == null || vehicleByColor.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(vehicleCollectionModel, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<EntityModel<Vehicle>> addVehicle(@RequestBody Vehicle newVehicle) {
        Optional<Vehicle> foundVehicle = vehicleService.getAllVehicles().stream().
                filter(vehicle -> vehicle.equals(newVehicle)).findFirst();
        if (vehicleService.getAllVehicles().stream().anyMatch(vehicle -> vehicle.getId() == newVehicle.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        if (foundVehicle.isEmpty()) {
            List<Link> links = new ArrayList<>();
            linkSingleEntry(newVehicle, links);
            vehicleService.getAllVehicles().add(newVehicle);
            EntityModel<Vehicle> entityModel = EntityModel.of(newVehicle, links);
            return new ResponseEntity<>(entityModel, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/mod/{id}")
    public ResponseEntity<Vehicle> modVehicle(@PathVariable int id, @RequestBody Vehicle modVehicle) {
        List<Link> links = new ArrayList<>();
        Optional<Vehicle> foundVehicle = vehicleService.getAllVehicles().stream().
                filter(vehicle -> vehicle.getId() == id).findFirst();
        boolean usedId = vehicleService.getAllVehicles().stream().anyMatch(vehicle -> vehicle.getId() == modVehicle.getId());
        if (foundVehicle.isPresent()&&!usedId) {
            vehicleService.getAllVehicles().remove(foundVehicle.get());
            linkSingleEntry(modVehicle,links);
            vehicleService.getAllVehicles().add(modVehicle);
            return new ResponseEntity<>(modVehicle, HttpStatus.OK);
        } else if (usedId){
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/mod/fields/{vehicleId}")
    public ResponseEntity<Vehicle> modField(@PathVariable int vehicleId,
                                            @RequestParam(required = false) Integer id,
                                            @RequestParam(required = false) String mark,
                                            @RequestParam(required = false) String model,
                                            @RequestParam(required = false) String color) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(vehicleId);
        if (vehicleById.isPresent()) {
            if (id != null) {
                if (vehicleService.getAllVehicles().stream().anyMatch(vehicle -> vehicle.getId() == id)) {
                    return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
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
            return new ResponseEntity<>(vehicleById.get(), HttpStatus.ACCEPTED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<HttpStatus> modField(@PathVariable int vehicleId) {
        Optional<Vehicle> vehicleById = vehicleService.getVehicleById(vehicleId);
        if (vehicleById.isPresent()) {
            if (vehicleService.getAllVehicles().remove(vehicleById.get())) {
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    private void linkSingleEntry(Vehicle vehicleById, List<Link> links) {
        if (!vehicleById.hasLinks()) {
            links.add(linkTo(VehicleApi.class).slash(vehicleById.getId()).withRel(VEHICLE_ID));
            links.add(linkTo(VehicleApi.class).slash(COLOR_URL).slash(vehicleById.getColor()).withRel(VEHICLE_COLOR));
            links.add(linkTo(VehicleApi.class).withRel(ALL_VEHICLES));
        }
    }
}
