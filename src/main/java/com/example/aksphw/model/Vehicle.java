package com.example.aksphw.model;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.*;

public class Vehicle {

    @Nonnull
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int id;
    @Nonnull
    @Size(min = 3)
    private String mark;
    @Nonnull
    @Size(min = 3)
    private String model;
    @NotNull
    private Color color;

    public Vehicle(int id, @Nonnull String mark, @Nonnull String model,Color color) {
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
