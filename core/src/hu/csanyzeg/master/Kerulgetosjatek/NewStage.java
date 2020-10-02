package hu.csanyzeg.master.Kerulgetosjatek;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleOverlapsUtil;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

public class NewStage extends MyStage {
    PlayerActor playerActor;

    public NewStage(MyGame game) {
        super(new ResponseViewport(80), game);
        //addActor(new PlayerActor(game));
        addTimer(new TickTimer(1f, true, new TickTimerListener(){
            @Override
            public void onStart(Timer sender) {
                super.onStart(sender);
                addActor(new WallActor(game));
            }

            @Override
            public void onRepeat(TickTimer sender) {
                super.onRepeat(sender);
                addActor(new WallActor(game));
            }
        }));

        addActor(playerActor = new PlayerActor(game));


        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                playerActor.moveTo(x);
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        addTimer(new PermanentTimer(new PermanentTimerListener(){
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                super.onTick(sender, correction);
                for(Actor a : getActors()){
                    if (a instanceof WallActor){
                        if (SimpleOverlapsUtil.overlaps(a, playerActor)){
                            getActors().removeValue(playerActor, true);
                            addActor(new ExplosionActor(game, playerActor));
                            sender.stop();
                            break;
                        }
                    }
                }
            }
        }));

    }
}
