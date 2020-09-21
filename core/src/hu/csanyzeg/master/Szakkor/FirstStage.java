package hu.csanyzeg.master.Szakkor;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class FirstStage extends MyStage {
    BadlogiActor actor1;

    public FirstStage(MyGame game) {
        super(new ExtendViewport(16,9), game);
        actor1 = new BadlogiActor(game);
        actor1.setY(3);
        addActor(actor1);

        addActor(new BadlogiActor(game));
    }


}
