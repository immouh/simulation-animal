package com.example.simulation;

import java.util.List;
//CLASS FILS DU ANIMAL HERBIVORE

public class Herbivore extends Animal {
    public Herbivore(Position position, int energy, int age, boolean isAlive) {
        super(position, energy, age, isAlive);
    }
//HERBIVORE EAT
    //ELLE RECUPERER LES VEGETAUX DE L ENVIRONEMENT ET TROUVE LE VEGETAL LE PLUS PROCHE
    @Override
    public void eat() {
        List<Vegetable> vegetables = Environment.getVegetables();
        Vegetable closestOne = findClosestVegetable(vegetables);
        if (closestOne != null && position.equals(closestOne.getPosition())) {
            eat(closestOne);
            vegetables.remove(closestOne);
        }
    }
//ELLE MANGE
    public void eat(Vegetable vegetable) {
        this.energy += vegetable.getNutritionValue();
    }
//DEPLACEMENT RANDOM
    @Override
    public void move() {
        if (energy > 50) {
            position.moveRandomly();
        } else {
            moveToClosestVegetable();
            eat();
        }
    }
//POUR LA METHODE DU BEBE
    @Override
    protected Animal create(Position pos) {
        return new Herbivore(pos, 50, 0, true);
    }
//POUR TROUVER LE VEGETAL LE PLUS PROCHE
    private Vegetable findClosestVegetable(List<Vegetable> vegetables) {
        Vegetable closestOne = null;
        double min = Double.POSITIVE_INFINITY;
        for (Vegetable veg : vegetables) {
            double distance = this.position.calculateDistance(veg.getPosition());
            if (distance < min) {
                min = distance;
                closestOne = veg;
            }
        }
        return closestOne;
    }
//DEPLACEMENT VERS LE VEGETAL LE PLUS PROCHE
    private void moveToClosestVegetable() {
        List<Vegetable> vegetables = Environment.getVegetables();
        Vegetable closestOne = null;
        double min = Double.POSITIVE_INFINITY;
        for (Vegetable veg : vegetables) {
            double distance = this.position.calculateDistance(veg.getPosition());
            if (distance < min) {
                min = distance;
                closestOne = veg;
            }
        }
        if (closestOne != null) {
            Position vegPosition = closestOne.getPosition();
            int deltaX = vegPosition.getX() - this.position.getX();
            int deltaY = vegPosition.getY() - this.position.getY();
            if (deltaX != 0) {
                this.position.setX(this.position.getX() + Integer.signum(deltaX));
            }
            if (deltaY != 0) {
                this.position.setY(this.position.getY() + Integer.signum(deltaY));
            }
        }
    }
}
