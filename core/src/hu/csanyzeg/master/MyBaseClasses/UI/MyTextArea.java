package hu.csanyzeg.master.MyBaseClasses.UI;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Game.InitableInterface;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.IZindex;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.IZindexCode;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public abstract class MyTextArea extends TextArea implements IZindex, InitableInterface {

    protected MyGame game;

    public MyTextArea(MyGame game, String text, Skin skin) {
        super(text, skin);
        this.game = game;
        init();
    }

    public MyTextArea(MyGame game, String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        this.game = game;
        init();
    }

    public MyTextArea(MyGame game, String text, TextFieldStyle style) {
        super(text, style);
        this.game = game;
        init();
    }


    public MyGame getGame() {
        return game;
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
