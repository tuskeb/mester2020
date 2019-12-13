package hu.csanyzeg.master.MyBaseClasses.Scene2D;

//Fizikai vagy játékmodell illesztését teszi lehetővé az actorok számára
public abstract class WorldHelper<TBody, TActor> {
    public TActor actor;
    public TBody body;

    public WorldHelper(TActor actor, TBody body) {
        this.actor = actor;
        this.body = body;
    }

    public TActor getActor() {
        return actor;
    }

    public TBody getBody() {
        return body;
    }

    public abstract float getBodyX();
    public abstract float getBodyY();
    public abstract float getBodyRotation();
    public abstract float getBodyOriginX();
    public abstract float getBodyOriginY();

    public abstract float getActorX();
    public abstract float getActorY();
    public abstract float getActorRotation();
    public abstract float getActorOriginX();
    public abstract float getActorOriginY();

    public abstract WorldHelper setX();
    public abstract WorldHelper setY();
    public abstract WorldHelper setRotation();
    public abstract WorldHelper setOriginX();
    public abstract WorldHelper setOriginY();


    protected abstract void beforeAddToWorld();
    protected abstract void afterAddToWorld();
    protected abstract void beforeRemoveFromWorld();
    protected abstract void afterRemoveFromWorld();

    public abstract void addToWorld();
    public abstract void removeFromWorld();

/*

    public void addToWorld();
    public void removeFromWorld();
    public boolean remove();



    public void setActive(boolean active);
    public boolean isActive();
    public void setVisibile(boolean visible);
    public void isVisibile(boolean visible);

    public ShapeType getShapeType();
    public void setFriction(float value);
    public void setRestitution(float value);
    public void setDensity(float value);
    public Body getBody();
    public void setSensor(boolean sensor);



    public void act(float delta);
    public void positionChanged();
    public void rotationChanged();
    public void sizeChanged();


    public boolean isFlaggedForDelete();
    public void setPositionByLeftBottomCorner(float x, float y);
    public float getXByLeftBottomCorner();
    public float getYByLeftBottomCorner();



    // ------    World Event ------- //
*/
}
