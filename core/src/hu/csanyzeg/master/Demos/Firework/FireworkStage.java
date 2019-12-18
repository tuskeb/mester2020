package hu.csanyzeg.master.Demos.Firework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

import hu.csanyzeg.master.Demos.Box2dHelper.UfoActor;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2dStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
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

    ArrayList<FireworkActor> fireworkActors = new ArrayList<>();

    public FireworkStage(final MyGame game) {
        super(new ExtendViewport(16,9), game);
        fireworkActors.add(new FireworkActor(game,world,true));
        fireworkActors.get(0).setForce(new Vector2(0,10));
        fireworkActors.get(0).setPosition(getViewport().getWorldWidth()/2,-1);
        addActor(fireworkActors.get(0));
        addBackButtonScreenBackByStackPopListener();

        addTimer(new TickTimer(2f, true, new TickTimerListener() {
            @Override
            public void onRepeat(TickTimer sender) {

            }

            @Override
            public void onTick(Timer sender, float correction) {
                fireworkActors = new ArrayList<>();
                fireworkActors.add(new FireworkActor(game,world,true));
                fireworkActors.get(0).setForce(new Vector2(0,6));
                fireworkActors.get(0).setPosition((float) (Math.random() * getViewport().getWorldWidth()/2) + getViewport().getWorldWidth()*0.25f,-2.5f);
                addActor(fireworkActors.get(0));
            }

            @Override
            public void onStop(Timer sender) {

            }

            @Override
            public void onStart(Timer sender) {

            }}));
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(fireworkActors.get(0).getY() >= getViewport().getWorldHeight()*0.8) {
            for (int i = 0; i < 128; i++) {
                fireworkActors.add(new FireworkActor(game, world, false));
                fireworkActors.get(i + 1).setPosition((float) (fireworkActors.get(0).getX() + Math.random()), (float) (getViewport().getWorldHeight() * 0.8f + Math.random()));
                fireworkActors.get(i + 1).setRotation(360 / 128.0f * i);
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
