package hu.csanyzeg.master.MyBaseClasses.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Game.InitableInterface;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.IZindex;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.IZindexCode;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public abstract class MyLabel extends Label implements IZindex, InitableInterface {

    protected MyGame game;

    public MyLabel(MyGame game, CharSequence text, Skin skin) {
        super(text, skin);
        this.game = game;
        init();
    }

    public MyLabel(MyGame game, CharSequence text, Skin skin, String styleName) {
        super(text, skin, styleName);
        this.game = game;
        init();
    }

    public MyLabel(MyGame game, CharSequence text, Skin skin, String fontName, Color color) {
        super(text, skin, fontName, color);
        this.game = game;
        init();
    }

    public MyLabel(MyGame game, CharSequence text, Skin skin, String fontName, String colorName) {
        super(text, skin, fontName, colorName);
        this.game = game;
        init();
    }

    public MyLabel(MyGame game, CharSequence text, LabelStyle style) {
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
