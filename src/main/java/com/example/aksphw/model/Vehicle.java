package com.example.aksphw.model;

public class Vehicle {


    private int id;
    private String mark;
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
