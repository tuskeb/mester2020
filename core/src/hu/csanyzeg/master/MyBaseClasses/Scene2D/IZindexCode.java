package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class IZindexCode {

    public static boolean setZIndex(int index, Actor actor) {
        Group parent = actor.getParent();
        if (parent == null) return false;
        Array<Actor> children = parent.getChildren();
        if (children.size == 1) return false;
        if (actor.getStage() != null){
            if (actor.getStage() instanceof MyStage){
                ((MyStage)actor.getStage()).sortActorsByZindex();
                return true;
            }
        }
        return false;
    }

    /*
    Insert code to class:


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


     */
}
