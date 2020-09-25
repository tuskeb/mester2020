package hu.csanyzeg.master.NewGame;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class SnakeStage extends MyStage {
    public SnakeStage(MyGame game) {
        super(new ExtendViewport(160, 90), game);
        addActor(new SnakeHead(game));
    }
}
