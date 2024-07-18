package com.example.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//CLASS ENVIRONMENT
public class Environment {
    static double max;//LE MAX DE LA DISTANCE POSSIBLE A PARCOURIR
    private static List<Animal> animals;
    private static List<Vegetable> vegetables;
    static int width;
    static int height;
    private Random random;

    public static double getMax() {
        return max;
    }

    public static List<Animal> getAnimals() {
        return animals;
    }

    public static List<Vegetable> getVegetables() {
        return vegetables;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public Random getRandom() {
        return random;
    }

    public Environment(List<Animal> animals, List<Vegetable> vegetables, int width, int height, Random random) {
        this.max = Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
        this.animals = animals;
        this.vegetables = vegetables;
        this.width = width;
        this.height = height;
        this.random = random;
    }
//LES METHODES UTILES POUR MODIFIER LES LIST D ANIMAUX OU VEGETAUX
    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void addVegetable(Vegetable vegetable) {
        vegetables.add(vegetable);
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void removeVegetable(Vegetable vegetable) {
        vegetables.remove(vegetable);
    }
}
