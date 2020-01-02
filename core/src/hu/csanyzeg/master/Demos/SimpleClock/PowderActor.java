package hu.csanyzeg.master.Demos.SimpleClock;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;

public class PowderActor extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }


    public PowderActor(MyGame game, SimpleWorld world, float x, float y) {
        super(game, boxTexture);
        float s =(float)Math.random() * 0.2f + 0.1f;
        setSize(s, s);
        setRotation((float)Math.random()*360f - 180f);
        setPosition(x - s/2f,y - s/2f);
        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Circle, SimpleBodyType.Ghost));
        ((SimpleWorldHelper)getActorWorldHelper()).body.setAngularVelocity((float)Math.random()*360f - 180f);
        ((SimpleWorldHelper)getActorWorldHelper()).body.setLinearVelocity((float)Math.random() - 0.5f, (float)Math.random()-0.5f);
        ((SimpleWorldHelper)getActorWorldHelper()).body.setColorVelocity(0,0,0,-0.5f);
        addTimer(new IntervalTimer(2f, new IntervalTimerListener() {
            @Override
            public void onStop(IntervalTimer sender) {
                super.onStop(sender);
                remove();
            }
        } ));
    }
}
