package com.example.foodrecipes.Models;

public class Length {
    private int number;
    private String unit;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Length(int number, String unit) {
        this.number = number;
        this.unit = unit;
    }
}
