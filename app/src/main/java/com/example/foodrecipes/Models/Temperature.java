package com.example.foodrecipes.Models;

public class Temperature {
    private double number;
    private String unit;

    public Temperature(double number, String unit) {
        this.number = number;
        this.unit = unit;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
