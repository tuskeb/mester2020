package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Game.InitableInterface;

public abstract class MyLabel extends Label implements IZindex, InitableInterface {
    public MyLabel(CharSequence text, Skin skin) {
        super(text, skin);
        init();
    }

    public MyLabel(CharSequence text, Skin skin, String styleName) {
        super(text, skin, styleName);
        init();
    }

    public MyLabel(CharSequence text, Skin skin, String fontName, Color color) {
        super(text, skin, fontName, color);
        init();
    }

    public MyLabel(CharSequence text, Skin skin, String fontName, String colorName) {
        super(text, skin, fontName, colorName);
        init();
    }

    public MyLabel(CharSequence text, LabelStyle style) {
        super(text, style);
        init();
    }

    protected int zIndex = 0;

    @Override
    public int getZIndex() {
        return zIndex;
    }

    @Override
    public boolean setZIndex(int index) {
        this.zIndex = index;
        Group parent = this.getParent();
        if (parent == null) return false;
        Array<Actor> children = parent.getChildren();
        if (children.size == 1) return false;
        if (getStage() != null){
            if (getStage() instanceof MyStage){
                ((MyStage)getStage()).sortActorsByZindex();
                return true;
            }
        }

        return false;
    }
}
