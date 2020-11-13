package com.mygdx.game.desktop;

public class MotorBike extends Bike {
    public int ccm;

    public MotorBike(int ccm) {
        this.ccm = ccm;
    }

    @Override
    public void kiir() {
        //super.kiir();
        System.out.println("De ez egy motorbicikli am!");
    }
}
