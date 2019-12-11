package hu.csanyzeg.master.Demos.DemoSzakkor;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class BlStage extends MyStage {

    public BlStage(MyGame game) {
        super(new FitViewport(640, 480), game);
        addActor(new BlActor(game));
        addBackButtonScreenBackByStackPopListener();
    }

    @Override
    public void init() {

    }
}
