package com.example.simulation;

import javafx.geometry.Pos;
//CLASS VEGETAL
public class Vegetable {
    private Position position;
    private int NutritionValue;

    public Position getPosition() {
        return position;
    }

    @Override
    public String toString() {
        return "Vegetable{" +
                "position=" + position +
                ", NutritionValue=" + NutritionValue +
                '}';
    }

    public int getNutritionValue() {
        return NutritionValue;
    }

    public Vegetable(Position position, int nutritionValue) {
        this.position = position;
        NutritionValue = nutritionValue;
    }
}
