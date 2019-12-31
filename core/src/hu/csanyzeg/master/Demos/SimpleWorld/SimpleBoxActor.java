package hu.csanyzeg.master.Demos.SimpleWorld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyContactListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleContact;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class SimpleBoxActor extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }


    public SimpleBoxActor(MyGame game, SimpleWorld world, float x, float y, float w, float h, float rotation) {
        super(game, boxTexture);
        setSize(w, h);
        setRotation(rotation);
        setPosition(x,y);
        setOrigin(1f,1f);
        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Circle, SimpleBodyType.Dinamic));
        ((SimpleWorldHelper)getActorWorldHelper()).body.addCollisionRectangleShape("rect", 0.2f,0.2f,0.5f,0.5f,10f);
        ((SimpleWorldHelper)getActorWorldHelper()).body.addCollisionCircleShape("circ", 0.2f,0.2f,0.25f, 0f);
        //((SimpleWorldHelper)getActorWorldHelper()).body.setAngularVelocity(10f);
        //((SimpleWorldHelper)getActorWorldHelper()).body.setLinearVelocity(0.1f,0.1f);
        //((SimpleWorldHelper)getActorWorldHelper()).body.setSizeVelocity(0.1f,0.1f);
        //((SimpleWorldHelper)getActorWorldHelper()).body.setColorVelocity(-0.1f,-0.1f,-0.1f,-0.1f);



        ((SimpleWorldHelper)getActorWorldHelper()).addContactListener(new SimpleBodyContactListener() {
            @Override
            public void beginContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                setFlash();
                //remove();
            }

            @Override
            public void endContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                setFlash();
            }
        });

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);


                SimpleWorldHelper helper = (SimpleWorldHelper) getActorWorldHelper();
                //helper.body.moveTo(4,4,1f, PositionRule.Center);
                helper.body.rotateTo(310, 1, Direction.ClockWise);
                //helper.body.scaleTo(2, 1, PositionRule.Origin);
                //helper.body.setSizeByOrigin(2,2);
                helper.body.colorTo(Color.BLUE, 2f);
                //helper.body.setColor(Color.BLUE);
                //setSize(2f,2f);
                //setOrigin(0.2f,0.2f);
                //setOrigin((float)Math.random()*getWidth(),(float)Math.random()*getHeight());

            }
        });

        addTimer(new IntervalTimer(1f, new IntervalTimerListener() {
            @Override
            public void onTick(IntervalTimer sender, float correction) {
                setAlpha(sender.getElapsedTime());
            }

            @Override
            public void onStop(IntervalTimer sender) {
                setAlpha(1f);
                removeTimer(sender);
            }

            @Override
            public void onStart(IntervalTimer sender) {
                setAlpha(0);
            }
        } ));

        addTimer(new PermanentTimer(new PermanentTimerListener() {
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                SimpleWorldHelper helper = (SimpleWorldHelper) getActorWorldHelper();
                //helper.body.setOriginFixedPositionAbsolute((float)Math.random()*10f,(float)Math.random()*10f);
            }
        }));
    }

    public void contactWithBall(){
        if (!protect) {
            setSize(getWidth() * 0.9f, getHeight() * 0.9f);
            if (getWidth() < 0.05) {
                remove();
            }
            setFlash();
        }
    }

    private boolean protect = false;

    public void setFlash(){
        addTimer(new IntervalTimer(0.85f, new IntervalTimerListener() {
            @Override
            public void onTick(IntervalTimer sender, float correction) {
                setColor(1, sender.getElapsedTime()*2f, sender.getElapsedTime()*2f,1);
            }

            @Override
            public void onStop(IntervalTimer sender) {
                setColor(1,1,1,1);
                removeTimer(sender);
                protect = false;
            }

            @Override
            public void onStart(IntervalTimer sender) {
                protect = true;
            }
        }));
    }
}
