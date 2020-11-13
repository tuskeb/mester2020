package com.mygdx.game.desktop;

public class Oop {
    public static void main(String[] args) {
        System.out.println("A program elindult");
        Bike dolgozos = new Bike();
        Bike hetvegi = new Bike();
        Bike tura = new Bike("szurke", 9000, 7.2f);

        MotorBike ujsport = new MotorBike(50);

        dolgozos.color = "Kék";
        dolgozos.distance = 670;

        hetvegi.color = "Piros";
        hetvegi.distance = 57820;

        System.out.println(ujsport.distance);
        ujsport.ride();
        ujsport.color = "kek";
        System.out.println(ujsport.distance);

        for(int i = 0; i < 5; i++) {
            System.out.println(hetvegi.distance);
            hetvegi.ride();
        }


        System.out.println("Meg itt is vagyok");
        System.out.println(dolgozos.distance);


        ujsport.kiir();
        hetvegi.kiir();
        tura.kiir();
    }
}
