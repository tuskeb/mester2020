package hu.csanyzeg.master.MyBaseClasses.Assets;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public abstract class LoadingStage extends MyStage {

    public LoadingStage(Viewport viewport, MyGame game) {
        super(viewport, game);
    }

}
