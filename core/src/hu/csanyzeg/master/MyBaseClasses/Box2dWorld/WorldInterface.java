package hu.csanyzeg.master.MyBaseClasses.Box2dWorld;

import com.badlogic.gdx.physics.box2d.Body;

/**
 * Created by tuskeb on 2016. 10. 29..
 */

public interface WorldInterface {
    public void addToWorld();
    public void removeFromWorld();
    public void setInActive();
    public void setActive();
    public boolean isActive();
    public Body getBody();
    public void setSensor(boolean sensor);
}
