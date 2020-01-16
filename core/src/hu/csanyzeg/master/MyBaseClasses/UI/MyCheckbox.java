package hu.csanyzeg.master.MyBaseClasses.UI;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import hu.csanyzeg.master.MyBaseClasses.Game.InitableInterface;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.IZindex;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.IZindexCode;

public abstract class MyCheckbox extends CheckBox implements IZindex, InitableInterface {

    protected MyGame game;

    public MyCheckbox(MyGame game, String text, Skin skin) {
        super(text, skin);
        this.game = game;
    }

    public MyCheckbox(MyGame game, String text, Skin skin, String styleName) {
        super(text, skin, styleName);
        this.game = game;
    }

    public MyCheckbox(MyGame game, String text, CheckBoxStyle style) {
        super(text, style);
        this.game = game;
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
