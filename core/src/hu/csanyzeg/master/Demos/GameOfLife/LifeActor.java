package hu.csanyzeg.master.Demos.GameOfLife;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

import hu.csanyzeg.master.Demos.SpaceInvaders.SmallLabelStyle;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos.DemoSimpleLabelListener1;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabel;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyContactListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleContact;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class LifeActor extends OneSpriteStaticActor {
    boolean isMale;
    public OneSpriteStaticActor bg;
    public OneSpriteStaticActor fg;
    public SimpleLabel label;
    public static RandomXS128 random = new RandomXS128();
    public float growSpeed;

    public LifeActor(MyGame game, SimpleWorld world) {
        super(game, "badlogic.jpg");
        float s;
        isMale = random.nextBoolean();
        /*
        addActor(bg = new OneSpriteStaticActor(game, "badlogic.jpg") {

        });
        addActor(fg = new OneSpriteStaticActor(game, "badlogic.jpg") {

        });
*/
        growSpeed = random.nextFloat() / 2 + 0.5f;
        setSize(s = random.nextInt(64) + 64, s);
        setOrigintoCenter();

        //addActor(label = new SimpleLabel(game, world, "ASD", new WorldLabelStyle("demoflappy/flappyfont.ttf",120)));

        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Circle, SimpleBodyType.Sensor));
        //setActorWorldHelper(new Box2DWorldHelper(world, ));
        /*((SimpleWorldHelper)getActorWorldHelper()).addContactListener(new SimpleBodyContactListener(){
            @Override
            public void beginContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                super.beginContact(contact, myHelper, otherHelper);
            }
        });*/
        SimpleBody sb = ((SimpleWorldHelper)getActorWorldHelper()).getBody();
        sb.setSimpleBodyBehaviorListener(new SimpleBodyBehaviorListener(){
            @Override
            public void onStop(SimpleBody sender) {
                super.onStop(sender);
                //System.out.println(LifeActor.this);
                sender.moveToFixSpeed(
                        random.nextInt((int)getStage().getViewport().getWorldWidth()),random.nextInt((int)getStage().getViewport().getWorldHeight()),
                        getSpeed(),
                        PositionRule.Center);
            }
        });

        ((SimpleWorldHelper)getActorWorldHelper()).addContactListener(new SimpleBodyContactListener(){
            @Override
            public void beginContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                super.beginContact(contact, myHelper, otherHelper);
                System.out.println(myHelper.actor);
                System.out.println(otherHelper.actor);
                myHelper.remove();
                otherHelper.remove();
            }
        });
/*
        addTimer(new PermanentTimer(new PermanentTimerListener(){
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                super.onTick(sender, correction);
                //setSize(getWidth() + correction * 10 * growSpeed, getHeight() + correction *10 * growSpeed);
                //setOrigintoCenter();
            }
        }));
*/
    }

    public float getSpeed(){
        return 256 - getWidth();
    }

    public LifeActor randomPos(){
        setPosition(random.nextInt((int)getStage().getViewport().getWorldWidth()),random.nextInt((int)getStage().getViewport().getWorldHeight()));
        return this;
    }

  /*  @Override
    protected void sizeChanged() {
        super.sizeChanged();
        for (Actor a: getChildren()) {
            a.setSize(getWidth(), getHeight());
        }
    }
*/
    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        SimpleBody sb = ((SimpleWorldHelper)getActorWorldHelper()).getBody();
        //sb.getSimpleBodyBehaviorListener().onStopMove(sb);
        /*sb.moveToFixTime(
                random.nextInt((int)getStage().getViewport().getWorldWidth()),random.nextInt((int)getStage().getViewport().getWorldHeight()),
                1,
                PositionRule.Center);

         */
        addTimer(new OneTickTimer(0.1f, new OneTickTimerListener(){
            @Override
            public void onTick(OneTickTimer sender, float correction) {
                super.onTick(sender, correction);
                sb.getSimpleBodyBehaviorListener().onStop(sb);
                //sb.sizeToFixSpeed(256,256,10,PositionRule.Center);
            }
        }));


    }

    public void moveto(float x, float y){
        SimpleBody sb = ((SimpleWorldHelper)getActorWorldHelper()).getBody();
        //sb.moveToFixTime(x, y,1,PositionRule.Center);
        sb.moveToFixTime(
                random.nextInt((int)getStage().getViewport().getWorldWidth()),
                random.nextInt((int)getStage().getViewport().getWorldHeight()),
                1,
                PositionRule.Center);
    }
}
