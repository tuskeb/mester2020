package com.mygdx.game.desktop;

public class Bike {

    public String color;
    public int distance = 0;
    public float pressure = 6.4f;

    public Bike(String color, int distance, float pressure) {
        this.color = color;
        this.distance = distance;
        this.pressure = pressure;
    }

    public Bike() {
        System.out.println("Vasaroltam egy uj kerekpart.");
    }

    public void ride(){
        distance++;
    }

    public void perec(){

    }

    public void kiir(){
        System.out.println("Ez egy " + color + " bicikli.");
    }
}
