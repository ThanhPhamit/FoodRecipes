package com.example.foodrecipes.Models;

public class Amount {
    //    Amounts to grams
    public static enum AMOUNTS {
        OZ(28.35, "Oz"),
        CUP(125, "Cup"),
        TABLESPOON(7.5, "Tablespoon"),
        TEASPOON(2, "Teaspoon"),
        PINCH(1, "Pinch"),
        OUNCE(28.35, "Ounce"),
        POUND(453.59, "Pound"),
        GRAM(1, "Gram");

        public double toGrams;
        public String define;

        @Override
        public String toString() {
            return define;
        }

        AMOUNTS(double toGrams, String define) {
            this.toGrams = toGrams;
            this.define = define;
        }
    }

    private double sourceAmount;
    private AMOUNTS sourceUnit;
    private AMOUNTS targetUnit;

    public double getTargetAmout() {
        double resutl = sourceAmount;
//        Change value to grams
        resutl = resutl * sourceUnit.toGrams;
//        Change value to target Unit
        resutl = resutl / targetUnit.toGrams;
        return resutl;
    }

    public Amount(double sourceAmount, AMOUNTS sourceUnit, AMOUNTS targetUnit) {
        this.sourceAmount = sourceAmount;
        this.sourceUnit = sourceUnit;
        this.targetUnit = targetUnit;
    }

    public Amount() {
    }

    public double getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(double sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public AMOUNTS getSourceUnit() {
        return sourceUnit;
    }

    public void setSourceUnit(AMOUNTS sourceUnit) {
        this.sourceUnit = sourceUnit;
    }

    public AMOUNTS getTargetUnit() {
        return targetUnit;
    }

    public void setTargetUnit(AMOUNTS targetUnit) {
        this.targetUnit = targetUnit;
    }
}
