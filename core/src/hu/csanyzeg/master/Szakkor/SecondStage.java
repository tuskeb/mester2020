package hu.csanyzeg.master.Szakkor;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class SecondStage extends SimpleWorldStage {
    public SecondStage(MyGame game) {
        super(new ExtendViewport(16,9), game);
        addActor(new SActor(this));
        addActor(new TActor(this));
    }


}
