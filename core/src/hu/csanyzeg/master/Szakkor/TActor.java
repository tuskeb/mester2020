package hu.csanyzeg.master.Szakkor;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class TActor extends OneSpriteStaticActor {
    public TActor(SimpleWorldStage simpleWorldStage) {
        super(simpleWorldStage.game, "badlogic");
        setActorWorldHelper(new SimpleWorldHelper(simpleWorldStage.getWorld(), this, ShapeType.Rectangle, SimpleBodyType.Sensor));
        setSize(1,1);
        setPosition(2,0);
    }

}
