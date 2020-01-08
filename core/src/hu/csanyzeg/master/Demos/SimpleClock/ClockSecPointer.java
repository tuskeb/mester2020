package hu.csanyzeg.master.Demos.SimpleClock;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyContactListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleContact;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class ClockSecPointer extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }


    public ClockSecPointer(final MyGame game, final SimpleWorld world, float x, float y, float w, float h, float rotation) {
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
                ((SimpleWorldHelper)getActorWorldHelper()).body.rotateToFixSpeed(-((TimeUtils.millis() / 1000) % 60) * 6f, 60f, Direction.Shorter);
                //((SimpleWorldHelper)getActorWorldHelper()).body.rotateToFixSpeed(-120078, 60f, Direction.Shorter);
                //((SimpleWorldHelper)getActorWorldHelper()).body.rotateToFixSpeed(0, 60f, Direction.Shorter);
            }
        }));
        final SimpleWorldHelper helper = (SimpleWorldHelper) getActorWorldHelper();
        helper.getBodyColor().a = 0f;
        helper.body.colorToFixTime(Color.WHITE, 2f);
        helper.body.addCollisionCircleShape("mutatovege", 0,h-w/2f,w/2f, 0);
        helper.body.addCollisionCircleShape("mutatokozepe", 0,1.5f,w/2f, 0);
        helper.addContactListener(new SimpleBodyContactListener(){
            @Override
            public void beginContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                super.beginContact(contact, myHelper, otherHelper);
                if (otherHelper.actor instanceof ClockMinutePointer || otherHelper.actor instanceof ClockHourPointer){
                    for(int i = 0; i< 50; i++){
                        Vector2 v2 = new Vector2((float)Math.random()*getWidth(), (float)Math.random()*getHeight());
                        v2.rotate(getRotation());
                        v2.add(getX(), getY());
                        getStage().addActor(new PowderActor(game, world, v2.x ,v2.y));
                    }
                }

                if (otherHelper.actor instanceof PointerActor){
                    for(int i = 0; i< 10; i++){
                        getStage().addActor(new PowderActor(game, world, otherHelper.body.getRealCenterX(), otherHelper.body.getRealCenterY()));
                    }
                }
            }
        });
    }

}
