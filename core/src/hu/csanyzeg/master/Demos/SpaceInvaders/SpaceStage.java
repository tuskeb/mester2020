package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanyzeg.master.Demos.FlappyBird.FlappyScreen;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabel;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction1;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction2;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelStyle;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

import static hu.csanyzeg.master.Demos.FlappyBird.FlappyStage.flappyFont;

public class SpaceStage extends SimpleWorldStage {
    Music music;
    int level = 1;

    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(EnemyActor.class, assetList);
        AssetList.collectAssetDescriptor(EnemyBulletActor.class, assetList);
        AssetList.collectAssetDescriptor(StarshipBulletActor.class, assetList);
        AssetList.collectAssetDescriptor(ExplosionActor.class, assetList);
        assetList.addMusic("spaceinvaders/tetris.mp3");
    }


    private void newLevel(){
        SimpleLabel simpleLabel;
        simpleLabel = new SimpleLabel(game, world, "Level "  + level, new LevelLabelStyle());
        addActor(simpleLabel);
        simpleLabel.setPositionCenter(600);

        addTimer(new TickTimer(3, false, new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                for (int k = 0; k < 1 + level; k++) {
                    for (int i = 0; i < 8; i++) {
                        addActor(new EnemyActor(game, world, i * 160, 800 - k * 100 ));
                    }
                }
                removeTimer(sender);

                addTimer(new TickTimer(1, true, new TickTimerListener(){
                    @Override
                    public void onTick(Timer sender, float correction) {
                        super.onTick(sender, correction);
                        int count = 0;
                        for(Actor actor : getActors()){
                            if (actor instanceof EnemyActor){
                                count++;
                            }
                        }
                        if (count == 0) {
                            removeTimer(sender);
                            music.stop();
                            SimpleLabel s;
                            addActor(s = new SimpleLabel(game, world, "Complete", new LevelLabelStyle()));
                            s.setPositionCenter(600);
                            game.getMyAssetManager().getSound("spaceinvaders/levelcomplete.mp3").play();
                            addTimer(new TickTimer(5, true, new TickTimerListener(){
                                @Override
                                public void onTick(Timer sender, float correction) {
                                    super.onTick(sender, correction);
                                    removeTimer(sender);
                                    music.setPosition(0);
                                    music.play();
                                    level++;
                                    newLevel();
                                }
                            }));

                        }
                    }
                }));
            }
        }));

    }


    public SpaceStage(MyGame game) {
        super(new ExtendViewport(1600, 960), game);
        StarShipActor starShipActor = new StarShipActor(game, world, 800,1);
        addActor(starShipActor);
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                starShipActor.moveTo(x);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        SimpleLabel simpleLabel;
        simpleLabel = new SimpleLabel(game, world, "Space Invaders", new SmallLabelStyle());
        addActor(simpleLabel);
        simpleLabel.setPositionCenter(300);

        addTimer(new TickTimer(2, false, new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                newLevel();
                removeTimer(sender);
            }
        }));

        music = game.getMyAssetManager().getMusic("spaceinvaders/tetris.mp3");
        music.setLooping(true);
        music.play();



    }

    @Override
    public void dispose() {
        super.dispose();
        music.stop();
    }
}
