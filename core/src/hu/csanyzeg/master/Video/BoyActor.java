package hu.csanyzeg.master.Video;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class BoyActor extends OneSpriteAnimatedActor {
    public static String asset =  "Video/boy.atlas";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTextureAtlas(asset);
    }

    private PermanentTimer run;

    public BoyActor(MyGame game) {
        super(game, asset);
        setSize(1.66f,1.42f);
        setFps(7);
        run = new PermanentTimer(new PermanentTimerListener(){
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                super.onTick(sender, correction);
                setX(getX() + 1 * correction);
            }

            @Override
            public void onStop(PermanentTimer sender) {
                super.onStop(sender);
                stop();
                setFrame(1);
            }

            @Override
            public void onStart(PermanentTimer sender) {
                super.onStart(sender);
                start();
            }
        });
        addTimer(run);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (isRunning()){
                    run.stop();
                }else{
                    run.start();
                }
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //setX(getX() + 1 * delta);
    }

    public void stopWalking(){
        run.stop();
    }
}
