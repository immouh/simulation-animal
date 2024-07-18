package com.example.simulation;

import java.util.List;
//class abstrait pour animal
public abstract class Animal {
    protected Position position ;//POSIITION ANIMAL
    protected int energy;//ENERGY DE L ANIMAL
    protected int age;
    protected boolean isAlive;

//LES GETERS
    public Position getPosition() {
        return position;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAge() {
        return age;
    }

    public boolean isAlive() {
        return isAlive;
    }
//LES METHODES ABSTRAITS
    public abstract void eat();
    public abstract void move();

//methodes qui enleve le l animal de la liste
    public void die(){
        this.isAlive=false;
        List<Animal> animal =Environment.getAnimals();
            animal.remove(this);

    }
//REPRODUCTION METHODE QUI VERIFIE L ENERGIE DES 2 instance un bebe
    public Animal reproduction(Animal animal){

        if (this.getClass()==animal.getClass() && this.energy>50 && animal.getEnergy()>50){
            this.energy-=20;
            animal.energy-=20;
            Position position=new Position((this.position.getX() + animal.position.getX()) / 2, (this.position.getY() + animal.position.getY()) / 2);
        Animal baby= create(position);
        return baby;

        }
        return null;
    }
        protected abstract Animal create(Position pos);
    public void sleep() {
        this.energy +=2;
        this.age+=0.05;


    }
    @Override
    public String toString() {
        return "Animal{" +
                "position=" + position +
                ", energy=" + energy +
                ", age=" + age +
                ", isAlive=" + isAlive +
                '}';
    }
    public Animal(Position position, int energy, int age, boolean isAlive) {
        this.position = position;
        this.energy = energy;
        this.age = age;
        this.isAlive = isAlive;
    }
    public boolean IsAlive(){
        return this.isAlive= (energy>0);
    }

}
