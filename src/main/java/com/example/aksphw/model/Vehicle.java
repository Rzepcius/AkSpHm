package com.example.aksphw.model;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

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
    private Color Color;

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

    public com.example.aksphw.model.Color getColor() {
        return Color;
    }

    public void setColor(com.example.aksphw.model.Color color) {
        Color = color;
    }
}
