package hu.csanyzeg.master.MyBaseClasses.Scene2D;


import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.Array;

public class MyGroup extends Group implements IZindex{

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
