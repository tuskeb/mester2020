package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

public class StarShipActor extends OneSpriteStaticActor {
    public static String asset =  "spaceinvaders/starship.png";
    protected SimpleWorld world;
    protected boolean armor = false;

    public static AssetList assetList = new AssetList();

    static {
        assetList.addTexture(asset);
        assetList.addSound("spaceinvaders/shoot.mp3");
    }


    public StarShipActor(MyGame game, SimpleWorld world, float x, float y) {
        super(game, asset);

        setPosition(x, y);

        setWidthWhithAspectRatio(120);

        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Sensor));
        this.world = world;

        addTimer(new OneTickTimer(1, new OneTickTimerListener(){
            @Override
            public void onTick(OneTickTimer sender, float correction) {
                super.onTick(sender, correction);
                addListener(new ClickListener(){
                    @Override
                    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                        fire();
                        return super.touchDown(event, x, y, pointer, button);
                    }
                });
            }
        }));

        addTimer(new OneTickTimer(6,  new OneTickTimerListener(){
            @Override
            public void onTick(OneTickTimer sender, float correction) {
                super.onTick(sender, correction);
                setArmor(false);
            }
        }));

        armorTickTimer = new TickTimer(0.5f, true, new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                ((SimpleBody)getActorWorldHelper().getBody()).setColor(1,1,1,0);
                ((SimpleBody)getActorWorldHelper().getBody()).colorToFixTime(0.4f, 1,1,1,1);
                System.out.println("blink");
            }
        });
        addTimer(armorTickTimer);

        setArmor(true);
    }

    public void moveTo(float x){
        if (Math.abs(getX() - (x - getWidth() / 2)) < 10){
            fire();
        }else {
            ((SimpleWorldHelper) getActorWorldHelper()).body.moveToFixSpeed(x - getWidth() / 2, getY(), 1000, PositionRule.LeftBottom);
        }
    }

    public boolean isArmor() {
        return armor;
    }

    protected TickTimer armorTickTimer;

    public void setArmor(boolean armor) {

        if (armor){
            armorTickTimer.start();
        }else{
            armorTickTimer.stop();
            getActorWorldHelper().setBodyColor(Color.WHITE);
        }

        this.armor = armor;
    }

    public void fire(){
        getStage().addActor(new StarshipBulletActor(game, world, getX()+60, getY()));
        SpaceStage spaceStage = (SpaceStage)getStage();
        if (spaceStage.getPoint() >=  spaceStage.getLevel()) {
            game.getMyAssetManager().getSound("spaceinvaders/shoot.mp3").play();
            spaceStage.addPoint(-spaceStage.getLevel());
        }
    }

    public void death(){
        if (!armor) {
            getStage().addActor(new ExplosionActor(game, world, this));
            getActorWorldHelper().invoke(new Runnable() {
                @Override
                public void run() {
                    world.setBodyType(((SimpleWorldHelper) getActorWorldHelper()).body, SimpleBodyType.Ghost);
                }
            });
            ((SimpleWorldHelper) getActorWorldHelper()).body.sizeToFixTime(1, 1, 0.5f, PositionRule.Center);
            addTimer(new TickTimer(1, false, new TickTimerListener() {
                @Override
                public void onTick(Timer sender, float correction) {
                    super.onTick(sender, correction);
                    ((SimpleWorldHelper) getActorWorldHelper()).remove();
                    ((SpaceStage) getStage()).starshipDeath();
                }
            }));
            game.getMyAssetManager().getSound("spaceinvaders/enemydestroy.mp3").play();
        }
    }

}
