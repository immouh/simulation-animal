package com.example.simulation;

import javafx.scene.shape.Circle;

public class AnimalCircle {
        private Circle circle;
        private Animal animal;

        public AnimalCircle(Circle circle, Animal animal) {
            this.circle = circle;
            this.animal = animal;
        }

        public Circle getCircle() {
            return circle;
        }

        public Animal getAnimal() {
            return animal;
        }
}
