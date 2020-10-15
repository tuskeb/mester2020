package com.mygdx.game.desktop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.SimpleTimeZone;

public class Tombok {



    public static ArrayList<Integer> f(){
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        arrayList.add(8);
        arrayList.add(15);
        arrayList.add(99);
        arrayList.add(9);
        arrayList.add(3);

        arrayList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer egyik, Integer masik) {
                return masik - egyik;
            }
        });
        return arrayList;
    }


    public static void main(String[] args) {
        ArrayList<Integer> fuggvenyboljott;
        fuggvenyboljott = f();

        System.out.println(fuggvenyboljott.indexOf(3));

        for(int i = 0; i<fuggvenyboljott.size(); i++){
            System.out.println(fuggvenyboljott.get(i));
        }

    }
}

