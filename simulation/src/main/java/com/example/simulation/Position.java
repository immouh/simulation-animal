package com.example.simulation;

import java.util.Random;

public class Position {
    protected int x;
    protected int y;
    protected Random random=new Random();

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void moveRandomly(){
        int newX=this.getX()+(random.nextInt(3)-1);
        int newY=this.getY()+(random.nextInt(3)-1);
        if (newX>=0 && newX <Environment.getWidth()){
            this.setX(newX);

        }
        if (newY>=0 && newY <Environment.getHeight()){
            this.setY(newY);
        }
    }

    public double calculateDistance(Position d){
        double p=Math.sqrt((Math.pow((getX()-d.getX()),2)+Math.pow((getY()-d.getY()),2)));
        return p;
}}
