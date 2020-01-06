package hu.csanyzeg.master.Demos.SimpleClock;

import com.badlogic.gdx.graphics.Color;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;

public class PointerActor extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }


    public PointerActor(final MyGame game, final SimpleWorld world, final float x, final float y, final float w, float h, float rotation) {
        super(game, boxTexture);
        setSize(w, h);
        setRotation(rotation);
        setPosition(x,y);
        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Ghost));

        addTimer(new IntervalTimer(2f, new IntervalTimerListener() {
            @Override
            public void onTick(IntervalTimer sender, float correction) {
                setAlpha(sender.getElapsedTime());
                SimpleWorldHelper helper = (SimpleWorldHelper) getActorWorldHelper();
                helper.body.setOriginFixedPosition(getOriginX() + correction*2f,getOriginY());
                helper.body.setPosition(getX()-correction*2f, getY());
                System.out.println(PointerActor.this);
            }

            @Override
            public void onStop(IntervalTimer sender) {
                super.onStop(sender);
                SimpleWorldHelper helper = (SimpleWorldHelper) getActorWorldHelper();
                helper.body.setOrigin(4f + getWidth() / 2f,getOriginY());
                //helper.body.setPosition(x-helper.body.getOriginX() + w / 2f, getY());
                helper.setBodyType(SimpleBodyType.Sensor);

            }
        } ));

        final SimpleWorldHelper helper = (SimpleWorldHelper) getActorWorldHelper();
        helper.body.setSimpleBodyBehaviorListener(new SimpleBodyBehaviorListener(){
            @Override
            public void onContactAdded(SimpleBody sender, SimpleBody other) {
                super.onContactAdded(sender, other);
                if (((SimpleWorldHelper)other.getUserData()).actor instanceof ClockSecPointer){
                    for(int i = 0; i< 10; i++){
                        getStage().addActor(new PowderActor(game, world, helper.body.getRealCenterX() ,helper.body.getRealCenterY()));
                    }
                }
            }
        });

        helper.getBodyColor().a = 0f;
        helper.body.colorTo(Color.WHITE, 2f);

    }

}
