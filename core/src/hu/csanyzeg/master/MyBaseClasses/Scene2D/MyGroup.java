package hu.csanyzeg.master.MyBaseClasses.Scene2D;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class MyGroup extends Group implements IZindex{

    protected MyGame game;

    public MyGroup(MyGame game) {
        this.game = game;
    }

    protected int zIndex = 0;

    @Override
    public int getZIndex() {
        return zIndex;
    }

    @Override
    public boolean setZIndex(int index) {
        this.zIndex = index;
        return IZindexCode.setZIndex(index, this);
    }

}
