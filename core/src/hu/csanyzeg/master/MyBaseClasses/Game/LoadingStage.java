package hu.csanyzeg.master.MyBaseClasses.Game;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class LoadingStage extends MyStage {
    @Override
    public void init() {

    }

    public LoadingStage(MyGame game) {
        super(new FitViewport(1920,1080), game);
    }
}
