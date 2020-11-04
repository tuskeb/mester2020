package hu.csanyzeg.master.TimerTest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class TimerTest extends MyGame {

    @Override
    public void create() {
        super.create();
        setScreen(new TimerScreen(this));
    }

}
