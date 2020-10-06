package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.math.RandomXS128;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabel;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction1;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

public class EnemyActor extends OneSpriteStaticActor {
    public static String asset =  "spaceinvaders/enemy1.png";
    public static RandomXS128 randomXS128 = new RandomXS128();
    private SimpleWorld world;

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(asset);
    }

    private String movingPattern = "RRRRRDLLLLLD";
    private int movingState = 0;

    public EnemyActor(MyGame game, SimpleWorld world, float x, float y) {
        super(game, asset);

        this.world = world;

        setPosition(x,y);

        setWidthWhithAspectRatio(12);

        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Sensor));

        addTimer(new TickTimer(1, true, new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);

                if (randomXS128.nextInt(50) == 0){
                    getStage().addActor(new EnemyBulletActor(game, world, getX() + 5, getY() - 3));
                }

                switch (movingPattern.charAt(movingState)){
                    case 'R':
                        ((SimpleWorldHelper)getActorWorldHelper()).body.moveToFixTime(getX() + 6, getY(), 0.7f, PositionRule.LeftBottom);
                        break;
                    case 'D':
                        ((SimpleWorldHelper)getActorWorldHelper()).body.moveToFixTime(getX(), getY() - 6, 0.7f, PositionRule.LeftBottom);
                        break;
                    case 'L':
                        ((SimpleWorldHelper)getActorWorldHelper()).body.moveToFixTime(getX() - 6, getY(), 0.7f, PositionRule.LeftBottom);
                        break;
                    case 'U':
                        ((SimpleWorldHelper)getActorWorldHelper()).body.moveToFixTime(getX() , getY() + 6, 0.7f, PositionRule.LeftBottom);
                        break;
                }
                movingState++;
                if (movingState == movingPattern.length()){
                    movingState = 0;
                }
            }
        }));
    }

    public void death(){
        getStage().addActor(new ExplosionActor(game, world, this));
        getActorWorldHelper().invoke(new Runnable() {
            @Override
            public void run() {
                world.setBodyType(((SimpleWorldHelper)getActorWorldHelper()).body, SimpleBodyType.Ghost);
            }
        });
        ((SimpleWorldHelper)getActorWorldHelper()).body.sizeToFixTime(1,1,0.5f,PositionRule.Center);
        ((SimpleWorldHelper)getActorWorldHelper()).body.setAngularVelocity(randomXS128.nextInt(2000)-1000);
        addTimer(new TickTimer(1, false, new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                ((SimpleWorldHelper)getActorWorldHelper()).remove();
            }
        }));
        SimpleLabel simpleLabel;
        simpleLabel = new SimpleLabel(game,world,"+100", "demoflappy/flappyfont.ttf", 1,1,1,1,10, new SimpleLabelAction1());
        simpleLabel.setPosition(getX(),getY());
        getStage().addActor(simpleLabel);
    }
}
