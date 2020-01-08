package hu.csanyzeg.master.Demos.SimpleClock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.TimeUtils;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class ClockHourPointer extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }


    public ClockHourPointer(MyGame game, SimpleWorld world, float x, float y, float w, float h, float rotation) {
        super(game, boxTexture);
        setSize(w, h);
        setRotation(rotation);
        setPosition(x,y);
        setOrigin(w/2f, w/2f);
        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Null, SimpleBodyType.Sensor));
        addTimer(new PermanentTimer(new PermanentTimerListener(){
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                super.onTick(sender, correction);
                ((SimpleWorldHelper)getActorWorldHelper()).body.rotateToFixSpeed(-(((TimeUtils.millis() / 1000 / 6 / 6) % 1200) + 100) * 0.3f, 10f, Direction.Shorter);
            }
        }));
        final SimpleWorldHelper helper = (SimpleWorldHelper) getActorWorldHelper();
        helper.getBodyColor().a = 0f;
        helper.body.colorToFixTime(Color.WHITE, 4f);
        helper.body.addCollisionCircleShape("mutatovege", 0,h-w/2f,w/2f, 0);
        helper.body.addCollisionCircleShape("mutatokozepe", 0,1.5f,w/2f, 0);

    }

}
