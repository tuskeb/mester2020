package hu.csanyzeg.master.MyBaseClasses.WorldHelper;

//Fizikai vagy játékmodell illesztését teszi lehetővé az actorok számára
public abstract class WorldHelper<TBody, TActor> {
    public TActor actor;
    public TBody body;

    public boolean modifyedByWorld = false;

    public void beginUpdate(){
        modifyedByWorld = true;
    }

    public void endUpdate(){
        modifyedByWorld = false;
    }

    public boolean isModifyedByWorld() {
        return modifyedByWorld;
    }

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

    public abstract void remove();

    public abstract float getBodyX();
    public abstract float getBodyY();
    public abstract float getBodyRotation();
    public abstract float getBodyOriginX();
    public abstract float getBodyOriginY();
    public abstract float getBodyWidth();
    public abstract float getBodyHeight();

    public abstract float getActorX();
    public abstract float getActorY();
    public abstract float getActorWidth();
    public abstract float getActorHeight();

    public abstract float getActorRotation();
    public abstract float getActorOriginX();
    public abstract float getActorOriginY();

    public abstract WorldHelper setBodyRotation(float rotation);
    public abstract WorldHelper setBodyOrigin(float x, float y);
    public abstract WorldHelper setBodySize(float w, float h);
    public abstract WorldHelper setBodyPosition(float x, float y);


    protected abstract void beforeAddToWorld();
    protected abstract void afterAddToWorld();
    protected abstract void beforeRemoveFromWorld();
    protected abstract void afterRemoveFromWorld();

    public abstract void addToWorld();
    public abstract void removeFromWorld();
    public abstract void refreshBodyOnWorld();

    public abstract void act(float delta);




}
