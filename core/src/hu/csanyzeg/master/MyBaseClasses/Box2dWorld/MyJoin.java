package hu.csanyzeg.master.MyBaseClasses.Box2dWorld;

import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;

public class MyJoin {
    public final MyActor actor;
    public final MyActor otherActor;
    public final Joint joint;

    public void remove(){

    }

    public MyJoin(MyActor actor, MyActor otherActor, Joint joint) {
        this.actor = actor;
        this.otherActor = otherActor;
        this.joint = joint;
    }



    public static void createWeldJoin(MyActor a, MyActor b){

    }

    public static void createRevoluteJoin(MyActor a, MyActor b){

    }


}
