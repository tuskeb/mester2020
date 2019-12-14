package hu.csanyzeg.master.MyBaseClasses.Box2dWorld;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.Shape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.WorldHelper;

public class Box2DWorldHelper extends WorldHelper<Body, Actor> {

    ShapeType shapeType;
    MyFixtureDef fixtureDef;
    BodyDef.BodyType bodyType;
    World world;
    public float originX;
    public float originY;

    public Box2DWorldHelper(World world, Actor actor, ShapeType shapeType, MyFixtureDef fixtureDef, BodyDef.BodyType bodyType) {
        super(null, null);
        this.bodyType = bodyType;
        this.fixtureDef = fixtureDef;
        this.shapeType = shapeType;
        this.world = world;
        this.actor = actor;
    }


    //------------------  BODY SETTERS -----------------------------------------
    //------------------  BODY SETTERS -----------------------------------------
    //------------------  BODY SETTERS -----------------------------------------

    @Override
    public WorldHelper setBodyRotation(float rotation) {
        if (body == null){
            return this;
        }
        if (!modifyedByWorld) {
            body.setTransform(getActorX(), getActorY(), getActorRotation() * MathUtils.degreesToRadians);
        }
        return this;
    }

    @Override
    public WorldHelper setBodySize(float w, float h) {
        if (body == null){
            return this;
        }
        if (!modifyedByWorld) {
            removeFromWorld();
            addToWorld();
        }
        return this;
    }

    @Override
    public WorldHelper setBodyPosition(float x, float y) {
        if (body == null){
            return this;
        }
        if (!modifyedByWorld) {
            body.setTransform(getActorX(), getActorY(), getActorRotation() * MathUtils.degreesToRadians);
        }
        return this;
    }


    //------------------  BODY GETTERS -----------------------------------------
    //------------------  BODY GETTERS -----------------------------------------
    //------------------  BODY GETTERS -----------------------------------------


    @Override
    public float getBodyX() {
        if (body != null) {
            return body.getPosition().x - actor.getOriginX();
        }else
            return 0;

    }

    @Override
    public float getBodyY() {
        if (body != null) {
            return body.getPosition().y - actor.getOriginY();
        }else
            return 0;
    }

    @Override
    public float getBodyRotation() {
        return MathUtils.radDeg * body.getAngle();
    }

    @Override
    public float getBodyOriginX() {
        return originX;
    }

    @Override
    public float getBodyOriginY() {
        return originY;
    }

    @Override
    public WorldHelper setBodyOriginX() {
        return null;
    }

    @Override
    public WorldHelper setBodyOriginY() {
        return null;
    }

//------------------  ACTOR GETTERS -----------------------------------------
    //------------------  ACTOR GETTERS -----------------------------------------
    //------------------  ACTOR GETTERS -----------------------------------------
    //------------------  ACTOR GETTERS -----------------------------------------


    @Override
    public float getActorX() {
        return actor.getX();
    }

    @Override
    public float getActorY() {
        return actor.getY();
    }

    @Override
    public float getActorRotation() {
        return actor.getRotation();
    }

    @Override
    public float getActorOriginX() {
        return actor.getOriginX();
    }

    @Override
    public float getActorOriginY() {
        return actor.getOriginY();
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
        if (body!=null){
            return;
        }
        beforeAddToWorld();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(getActorX() + getActorOriginX(),getActorY() + getActorOriginY());
        bodyDef.angle = getActorRotation() * MathUtils.degRad;

        body = world.createBody(bodyDef);

        body.setUserData(this.actor);

        Shape shape;
        FixtureDef fixtureDef = new FixtureDef();
        this.fixtureDef.setFixtureValues(fixtureDef);

        switch (shapeType)
        {
            case Circle:
                shape = new CircleShape();
                ((CircleShape)shape).setRadius((getActorWidth()+getActorHeight())/4);
                //((CircleShape)shape).setBodyPosition(new Vector2((getActorWidth()+getActorHeight())/4, (getActorWidth()+getActorHeight())/4));
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                originX =(getActorWidth()+getActorHeight())/4;
                originY = (getActorWidth()+getActorHeight())/4;
                shape.dispose();
                break;
            case Rectangle:
                shape = new PolygonShape();
                //((PolygonShape)shape).setAsBox(getActorWidth()/2,getActorHeight()/2,new Vector2(getActorWidth()/2, getActorHeight()/2),0);
                ((PolygonShape)shape).setAsBox(getActorWidth()/2,getActorHeight()/2,new Vector2(0, 0),0);
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                originX = getActorWidth()/2;
                originY = getActorHeight()/2;
                //body.setTransform(originX, originY, getActorRotation()*MathUtils.degRad);
                shape.dispose();
                break;
                /*
            case Polygon:
                loader.attachFixture(body, bodyID, fixtureDef, getActorWidth()>getActorHeight()?getActorWidth():getActorHeight());
                Vector2 vector2 = loader.getOrigin(bodyID,getActorWidth()>getActorHeight()?getActorWidth():getActorHeight());
                setOrigin(vector2.x, vector2.y);
                break;

                 */
        }
        body.getMassData().center.set(getActorOriginX(),getActorOriginY());
        for(Fixture f : body.getFixtureList()) {
            f.setUserData(this.actor);
        }
        //body.setFixedRotation(false);
        System.out.println(body.isFixedRotation());
        afterAddToWorld();
    }

    protected boolean flaggedForDeleteFromWorld = false;


    @Override
    public void removeFromWorld() {
        if (body == null){
            return;
        }
        if (!body.getWorld().isLocked()) {
            beforeRemoveFromWorld();
            world.destroyBody(this.body);
            this.body = null;
            /*if (visibilityControl) {
                setVisible(false);
            }*/
            afterRemoveFromWorld();
        } else {
            flaggedForDeleteFromWorld = true;
        }
    }

    @Override
    public float getActorWidth() {
        return actor.getWidth();
    }

    @Override
    public float getActorHeight() {
        return actor.getHeight();
    }
}
