package com.example.simulation;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
//CLASSE DE SIMULATION
public class SimulationGUI extends Application {
//LES CHAMPS
    private static List<AnimalCircle> animalsCircle = new ArrayList<>();
    private static List<Vegetable> vegetables = new ArrayList<>();
    private static final int width = 800;  // Largeur de la fenêtre
    private static final int height = 600; // Hauteur de la fenêtre
    private Group root;//GROUPE DE ENGLOBER LES ANIMAUX
    private Environment environment;

    @Override
    //OVERRIDE SUR METHODE ABSTRAITE DE APPLICATION POUR MODIFIER LE START
    public void start(Stage stage) {
        stage.setTitle("Simulation Environment");

        root = new Group();
        Scene scene = new Scene(root, width, height, Color.DARKGREY);

        initializeEnvironment();

        stage.setScene(scene);
        stage.show();
//METHODE POUR LANCER LA SIMULATION
        startSimulation();
    }
//INITIALISER L ENVIRONEMENT
    private void initializeEnvironment() {
        Random random = new Random();
        List<Animal> animals =new ArrayList<>();
        List<Vegetable> vegetables =new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Position herbivorePosition = new Position(random.nextInt(width), random.nextInt(height));
            Herbivore herbivore = new Herbivore(herbivorePosition, 20, 0, true);
            animals.add(herbivore);
        }

        for (int i = 0; i < 1; i++) {
            Position carnivorePosition = new Position(random.nextInt(width), random.nextInt(height));
            Carnivore carnivore = new Carnivore(carnivorePosition, 20, 0, true);
            animals.add(carnivore);
        }

        for (int i = 0; i < 5; i++) {
            Position vegetablePosition = new Position(random.nextInt(width), random.nextInt(height));
            Vegetable vegetable = new Vegetable(vegetablePosition, 10);
            vegetables.add(vegetable);
        }

        Environment environment = new Environment(animals, vegetables, width, height, random);

        for (Animal animal : animals) {
            Circle circle = new Circle(animal.getPosition().getX(), animal.getPosition().getY(), 10);
            if (animal instanceof Herbivore) {
                circle.setFill(Color.GREEN);
            } else if (animal instanceof Carnivore) {
                circle.setFill(Color.RED);
            }
            root.getChildren().add(circle);
            animalsCircle.add(new AnimalCircle(circle,animal));

        }
    }
//APRES LE MOUVEMEMNT BOUGER LE CERCLE DE L ANIMAL DANS LA SIMULATION
    private void updatePositions() {
        for (AnimalCircle anima : animalsCircle) {
            Circle circle = anima.getCircle();
            Animal animal = anima.getAnimal();
            if (animal.isAlive()) {
                circle.setCenterX(animal.getPosition().getX());
                circle.setCenterY(animal.getPosition().getY());
            } else {
                root.getChildren().remove(circle);
            }
        }
    }
//METHODE QUI RETURN L ANIMAL REPRESENTER PAR LE CIRCLE
    private Animal findAnimalByCircle(Circle circle) {
        for (AnimalCircle animal : animalsCircle) {
            if (animal.getAnimal().getPosition().getX() == circle.getCenterX() &&
                    animal.getAnimal().getPosition().getY() == circle.getCenterY()) {
                return animal.getAnimal();
            }
        }
        return null;
    }
    //LA SIMULATION le commencement

    private void startSimulation() {
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                List<Animal> toRemove = new ArrayList<>();
                for (Animal animal : environment.getAnimals()) {
                    if (animal.isAlive()) {
                        animal.move();
                        animal.eat();
                        animal.sleep();
                    } else {
                        toRemove.add(animal);
                    }
                }

                environment.getAnimals().removeAll(toRemove);
                updatePositions();
            }
        };
        timer.start();
    }

    public static void main(String[] args) {
        launch(args);



    }
}
