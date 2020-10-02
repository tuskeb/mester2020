package hu.csanyzeg.master.Video;

import com.badlogic.gdx.Gdx;

import hu.csanyzeg.master.Demos.SpaceInvaders.SpaceScreen;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class VideoGame extends MyGame {
    @Override
    public void create() {
        super.create();
        Gdx.app.log("Create", "Message");
       // this.debug = true;
       // this.setScreen(new VideoScreen(this));
        this.setScreen(new SpaceScreen(this));
    }
}
