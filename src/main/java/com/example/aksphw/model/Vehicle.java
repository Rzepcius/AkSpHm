package com.example.aksphw.model;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

public class Vehicle extends RepresentationModel<Vehicle> {

    @Nonnull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private Integer id;
    @Nonnull
    @Size(min = 3)
    private String mark;
    @Nonnull
    @Size(min = 3)
    private String model;
    @NotNull
    private String color;

    public Vehicle(int id, @Nonnull String mark, @Nonnull String model,String color) {
        this.id = id;
        this.mark = mark;
        this.model = model;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Vehicle vehicle = (Vehicle) o;
        return id == vehicle.id && Objects.equals(mark, vehicle.mark) && Objects.equals(model, vehicle.model) && Objects.equals(color, vehicle.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, mark, model, color);
    }
}
