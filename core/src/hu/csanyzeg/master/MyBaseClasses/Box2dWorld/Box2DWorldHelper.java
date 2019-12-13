package hu.csanyzeg.master.MyBaseClasses.Box2dWorld;

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
        addToWorld();
    }

    @Override
    public float getBodyX() {
        if (body != null) {
            return body.getPosition().x;
        }else
            return 0;

    }

    @Override
    public float getBodyY() {
        if (body != null) {
            return body.getPosition().y;
        }else
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
        return actor.getX();
    }

    @Override
    public float getActorY() {
        return actor.getY();
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

        beforeAddToWorld();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(getActorX(),getActorY());
        bodyDef.angle = getActorOriginX();

        body = world.createBody(bodyDef);
        body.setFixedRotation(false);
        body.setUserData(this);

        Shape shape;
        FixtureDef fixtureDef = new FixtureDef();
        this.fixtureDef.setFixtureValues(fixtureDef);
        switch (shapeType)
        {
            case Circle:
                shape = new CircleShape();
                //((CircleShape)shape).setRadius((getActorWidth()+getActorHeight())/4);
                ((CircleShape)shape).setPosition(new Vector2((getActorWidth()+getActorHeight())/4, (getActorWidth()+getActorHeight())/4));
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                originX =(getActorWidth()+getActorHeight())/4;
                originY = (getActorWidth()+getActorHeight())/4;
                shape.dispose();
                break;
            case Rectangle:
                shape = new PolygonShape();
                ((PolygonShape)shape).setAsBox(getActorWidth()/2,getActorHeight()/2,new Vector2(getActorWidth()/2, getActorHeight()/2),0);
                //((PolygonShape)shape).setAsBox(getActorWidth()/2,getActorHeight()/2,new Vector2(0, 0),0);
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                originX = getActorWidth()/2;
                originY = getActorHeight()/2;
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
        body.getMassData().center.set(getActorWidth()/2,getActorHeight()/2);
        for(Fixture f : body.getFixtureList()) {
            f.setUserData(this);
        }
        afterAddToWorld();
    }

    @Override
    public void removeFromWorld() {

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
