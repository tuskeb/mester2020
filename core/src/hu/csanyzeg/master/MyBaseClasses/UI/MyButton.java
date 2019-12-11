package hu.csanyzeg.master.MyBaseClasses.UI;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Game.InitableInterface;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.IZindex;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public abstract class MyButton extends TextButton implements InitableInterface, IZindex {

    public MyButton(MyGame game, String text, TextButtonStyle style) {
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
