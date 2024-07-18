package com.example.simulation;

import java.util.List;

public class Carnivore extends Animal{
    public Carnivore(Position position, int energy, int age, boolean isAlive) {
        super(position, energy, age, isAlive);
    }

    @Override
    public void eat() {
        List<Animal> animals=Environment.getAnimals();
        Animal closestOne=findClosestAnimal(animals);
        if (position.equals(closestOne.getPosition())) {
            eat(closestOne);
            animals.remove(closestOne);
        }
    }
    public void eat( Animal prey){
        this.energy+=prey.energy;
        prey.die();
    }

    private Animal findClosestAnimal(List<Animal> animals) {
        Animal closestOne = null;
        double min = Double.POSITIVE_INFINITY;
        for (Animal other : animals) {
            if (other instanceof Herbivore) {
                double distance = this.position.calculateDistance(other.getPosition());
                if (distance < min) {
                    min = distance;
                    closestOne = other;
                }
            }
        }
        return closestOne;
    }
    private void moveToClosestAnimal() {
        List<Animal> animals = Environment.getAnimals();
        Animal closestOne = findClosestAnimal(animals);
        if (closestOne != null) {
            Position animalPosition = closestOne.getPosition();
            int deltaX = animalPosition.getX() - this.position.getX();
            int deltaY = animalPosition.getY() - this.position.getY();
            Position newPosition = new Position((this.position.getX() + deltaX), this.position.getY() + deltaY);
            boolean isOccupied = false;
            for (Animal other : animals) {
                if (other.getPosition().equals(newPosition)) {
                    isOccupied = true;
                    break;
                }
            }
            if (!isOccupied) {
                if (deltaX != 0) {
                    this.position.setX(this.position.getX() + Integer.signum(deltaX));
                }
                if (deltaY != 0) {
                    this.position.setY(this.position.getY() + Integer.signum(deltaY));
                }
            }
        }
    }
    @Override
    public void move() {
        if (energy > 50) {
            position.moveRandomly();
        } else {
            moveToClosestAnimal();
            eat();
        }
    }
    @Override
    protected Animal create(Position pos) {
        return null;
    }
}
