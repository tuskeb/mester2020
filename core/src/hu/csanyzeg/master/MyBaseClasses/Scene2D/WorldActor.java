package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import com.badlogic.gdx.scenes.scene2d.Actor;

public interface WorldActor {
    public void setActorWorldHelper(WorldHelper<?, Actor> worldHelper);
    public WorldHelper<?, Actor> getActorWorldHelper();

}
