package com.example.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args){
        int width=100;
        int height=100;
        List<Animal> animals=new ArrayList<>();
        List<Vegetable> vegetables=new ArrayList<>();
        Random random =new Random();
        for (int i = 0; i <20 ; i++) {
            int x=random.nextInt(width);
            int y=random.nextInt(height);
            Vegetable veg=new Vegetable(new Position(x,y),10);
            vegetables.add(veg);
        }
        for (int i = 0; i < 10; i++) {
            int x=random.nextInt(width);
            int y=random.nextInt(height);
            Animal a=new Herbivore(new Position(x,y),50,0,true);
            animals.add(a);
        }
        for (int i = 0; i < 5; i++) {
            int x=random.nextInt(width);
            int y=random.nextInt(height);
            Animal a=new Carnivore(new Position(x,y),50,0,true);
            animals.add(a);
        }
        Environment environment=new Environment(animals,vegetables,width,height,random);
        simulate(environment,100);
    }

    public static void simulate(Environment environment, int steps) {
        for (int i = 0; i < steps; i++) {
            List<Animal> toRemove = new ArrayList<>();
            for (Animal animal : environment.getAnimals()) {
                animal.move();
                animal.eat();
                animal.sleep();
                if (!animal.isAlive()) {
                    toRemove.add(animal);
                }
            }

            environment.getAnimals().removeAll(toRemove);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

