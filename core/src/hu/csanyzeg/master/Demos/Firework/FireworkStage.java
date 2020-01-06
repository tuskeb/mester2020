package hu.csanyzeg.master.Demos.Firework;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2dStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

public class FireworkStage extends Box2dStage {
    public static final String fireworkSound = "demofirework/sound.wav";

    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(FireworkActor.class, assetList);
        assetList.addSound(fireworkSound);
    }


    ArrayList<makeFirework> fireworks = new ArrayList<>();
    TickTimer myTimer;
    OneSpriteStaticActor button;
    boolean stop;

    public FireworkStage(final MyGame game) {
        super(new ResponseViewport(9), game);
        addBackButtonScreenBackByStackPopListener();
        addBaseFirework();
        setButton();
        setTimer();
    }

    private void setTimer() {
        stop = false;

        myTimer = new TickTimer(1f, true, new TickTimerListener() {

            @Override
            public void onTick(Timer sender, float correction) {
                if (!stop) fireworks.add(new makeFirework((int) (Math.random() * 128) + 24));
            }
        });
        addTimer(myTimer);
    }

    private void setButton()
    {
        button = new OneSpriteStaticActor(game, FireworkActor.fireworkTexture);
        button.setSize(6,3);
        button.setPosition(-3,-1.5f);
        button.setColor(Color.GREEN);

        button.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(stop == true)
                {
                    stop = false;
                    button.setColor(Color.GREEN);
                }
                else
                {
                    stop = true;
                    button.setColor(Color.RED);
                }
            }
        });

        addActor(button);
    }

    private void addBaseFirework()
    {
        fireworks.add(new makeFirework((int) (Math.random() * 128) + 24));
    }

    private class makeFirework
    {
        ArrayList<FireworkActor> fireworkActors = new ArrayList<>();
        int particles;

        private makeFirework(int particles)
        {
            fireworkActors.add(new FireworkActor(game,world,true));
            fireworkActors.get(0).setForce(new Vector2(0,10));
            fireworkActors.get(0).setPosition((float) (Math.random() * getViewport().getWorldWidth()),-1);
            addActor(fireworkActors.get(0));
            this.particles = particles;
        }

        private void act()
        {
            if(fireworkActors.get(0).getY() >= getViewport().getWorldHeight()*0.8) {
                for (int i = 0; i < particles; i++) {
                    fireworkActors.add(new FireworkActor(game, world, false));
                    fireworkActors.get(i + 1).setPosition((float) (fireworkActors.get(0).getX() + Math.random()*1.5), (float) (getViewport().getWorldHeight() * 0.8f + Math.random()*1.5));
                    fireworkActors.get(i + 1).setRotation(360.0f / particles * i);
                    fireworkActors.get(i + 1).setForce(new Vector2((float) Math.sin(fireworkActors.get(i + 1).getRotation()) * 0.5f, (float) Math.cos(fireworkActors.get(i + 1).getRotation()) * 0.5f));
                    addActor(fireworkActors.get(i + 1));
                }
                game.getMyAssetManager().getSound(fireworkSound).play();
                fireworkActors.get(0).setForce(new Vector2(0, 0));
                fireworkActors.get(0).setPosition(0, -5);
                fireworkActors.get(0).remove();
            }
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        for (makeFirework currentFirework : fireworks) currentFirework.act();
        //countParticles();
    }

    private void countParticles()
    {
        int particles = 0;

        for (Actor actor : getActors())
        {
            if(actor instanceof FireworkActor) particles++;
        }

        System.out.println("Tűzijáték lövedékek: " + particles);
    }
}
