package com.mygdx.game.desktop;

public class VezerlesiSzerkezetek {


    public static void rekuzio(int hivas){
        //System.out.println(db);
        int helyidarab = hivas;
        System.out.println("Fuggveny eleje " + helyidarab);
        if (hivas < 10) {
            rekuzio(hivas + 1);
            rekuzio(hivas + 1);
        }
        System.out.println("Fuggveny vege " + helyidarab);
    }


    public static void main(String[] args) {

        rekuzio(0);

    }
}
