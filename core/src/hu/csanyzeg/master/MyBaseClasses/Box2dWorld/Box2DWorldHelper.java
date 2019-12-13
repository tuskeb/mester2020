package hu.csanyzeg.master.MyBaseClasses.Box2dWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.WorldHelper;

public class Box2DWorldHelper extends WorldHelper<Body, Actor> {
    public Box2DWorldHelper(World world) {
        super(null, null);
    }

    @Override
    public float getBodyX() {
        return 0;
    }

    @Override
    public float getBodyY() {
        return 0;
    }

    @Override
    public float getBodyRotation() {
        return 0;
    }

    @Override
    public float getBodyOriginX() {
        return 0;
    }

    @Override
    public float getBodyOriginY() {
        return 0;
    }

    @Override
    public float getActorX() {
        return 0;
    }

    @Override
    public float getActorY() {
        return 0;
    }

    @Override
    public float getActorRotation() {
        return 0;
    }

    @Override
    public float getActorOriginX() {
        return 0;
    }

    @Override
    public float getActorOriginY() {
        return 0;
    }

    @Override
    public WorldHelper setX() {
        return null;
    }

    @Override
    public WorldHelper setY() {
        return null;
    }

    @Override
    public WorldHelper setRotation() {
        return null;
    }

    @Override
    public WorldHelper setOriginX() {
        return null;
    }

    @Override
    public WorldHelper setOriginY() {
        return null;
    }

    @Override
    protected void beforeAddToWorld() {

    }

    @Override
    protected void afterAddToWorld() {

    }

    @Override
    protected void beforeRemoveFromWorld() {

    }

    @Override
    protected void afterRemoveFromWorld() {

    }

    @Override
    public void addToWorld() {

    }

    @Override
    public void removeFromWorld() {

    }
}
