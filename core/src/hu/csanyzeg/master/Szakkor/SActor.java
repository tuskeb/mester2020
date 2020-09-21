package hu.csanyzeg.master.Szakkor;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class SActor extends OneSpriteStaticActor {
    public SActor(SimpleWorldStage simpleWorldStage) {
        super(simpleWorldStage.game, "badlogic");
        setActorWorldHelper(new SimpleWorldHelper(simpleWorldStage.getWorld(), this, ShapeType.Rectangle, SimpleBodyType.Sensor));
        setSize(1,1);
        PermanentTimer t;
        addTimer(t = new PermanentTimer(new PermanentTimerListener(){
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                super.onTick(sender, correction);
                setX(getX()+correction);
            }
        }));
        getActorWorldHelper().addToWorld();

        ((SimpleBody)(getActorWorldHelper().getBody())).setSimpleBodyBehaviorListener(new SimpleBodyBehaviorListener(){
            @Override
            public void onContactAdded(SimpleBody sender, SimpleBody other) {
                super.onContactAdded(sender, other);
                t.stop();
                ((SimpleBody)(getActorWorldHelper().getBody())).setSimpleBodyBehaviorListener(null);
            }
        });
    }

}
