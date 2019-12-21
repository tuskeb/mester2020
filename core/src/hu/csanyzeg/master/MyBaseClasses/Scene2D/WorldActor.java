package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.WorldHelper.WorldHelper;

public interface WorldActor {
    public void setActorWorldHelper(WorldHelper<?, Actor> worldHelper);
    public WorldHelper<?, Actor> getActorWorldHelper();

}
