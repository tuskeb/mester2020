package hu.csanyzeg.master.Demos.DemoLoadingStage;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class DemoLoadingStage extends LoadingStage {
    public DemoLoadingStage(MyGame game) {
        super(new ExtendViewport(1280, 720), game);
    }

    @Override
    public void init() {

    }
}
