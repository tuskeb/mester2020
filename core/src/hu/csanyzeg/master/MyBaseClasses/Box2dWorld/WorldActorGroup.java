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
import com.badlogic.gdx.scenes.scene2d.Group;
import hu.csanyzeg.master.MyBaseClasses.Game.InitableInterface;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ShapeType;

/**
 * Created by tuskeb on 2016. 10. 29..
 */

public class WorldActorGroup extends Group implements WorldInterface, InitableInterface {
    public final World world;
    public final ShapeType shapeType;
    private Body body = null;
    private BodyDef bodyDef;
    private boolean flaggedForDeleteFromWorld = false;
    private boolean flaggedForDeleteFromStage = false;
    public final BodyDef.BodyType bodyType;
    private WorldBodyEditorLoader loader = null;
    public final String bodyID;
    private FixtureDef fixtureDef;


    private boolean visibilityControl = true;

    private boolean changeByWorld = false;

    public WorldActorGroup(World world, ShapeType shapeType, BodyDef.BodyType bodyType, FixtureDef fixtureDef) {
        this.world = world;
        this.shapeType = shapeType;
        this.bodyType = bodyType;
        this.fixtureDef = fixtureDef;

        bodyID = null;
        init();
    }

    public WorldActorGroup(World world, WorldBodyEditorLoader loader, String bodyID, BodyDef.BodyType bodyType, FixtureDef fixtureDef) {
        this.world = world;
        this.shapeType = ShapeType.Polygon;
        this.bodyType = bodyType;
        this.fixtureDef = fixtureDef;

        this.loader = loader;
        this.bodyID = bodyID;

        init();
    }

    public WorldActorGroup(World world, ShapeType shapeType, BodyDef.BodyType bodyType, float friction, float restitution, float density, boolean isSensor) {
        this.world = world;
        this.shapeType = shapeType;
        this.bodyType = bodyType;
        this.fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.isSensor = isSensor;
        bodyID = null;
        init();
    }

    public WorldActorGroup(World world, WorldBodyEditorLoader loader, String bodyID, BodyDef.BodyType bodyType, float friction, float restitution, float density, boolean isSensor) {
        this.world = world;
        this.shapeType = ShapeType.Polygon;
        this.bodyType = bodyType;
        this.fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixtureDef.isSensor = isSensor;

        this.loader = loader;
        this.bodyID = bodyID;

        init();
    }

    @Override
    public void init() {
        setSize(1,1);
    }


    @Override
    public void addToWorld() {

        beforeAddToWorld();
        bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(getX(),getY());
        bodyDef.angle = getRotation();

        body = this.world.createBody(bodyDef);
        body.setFixedRotation(false);
        body.setUserData(this);

        Shape shape;
        switch (shapeType)
        {
            case Circle:
                shape = new CircleShape();
                ((CircleShape)shape).setRadius((getWidth()+getHeight())/4);
                //((CircleShape)shape).setPosition(new Vector2((getWidth()+getHeight())/4, (getWidth()+getHeight())/4));
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                setOrigin((getWidth()+getHeight())/4, (getWidth()+getHeight())/4);
                shape.dispose();
                break;
            case Rectangle:
                shape = new PolygonShape();
                //((PolygonShape)shape).setAsBox(getWidth()/2,getHeight()/2,new Vector2(getWidth()/2, getHeight()/2),0);
                ((PolygonShape)shape).setAsBox(getWidth()/2,getHeight()/2,new Vector2(0, 0),0);
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);
                setOrigin(getWidth()/2,getHeight()/2);
                shape.dispose();
                break;
            case Polygon:
                loader.attachFixture(body, bodyID, fixtureDef, getWidth()>getHeight()?getWidth():getHeight());
                Vector2 vector2 = loader.getOrigin(bodyID,getWidth()>getHeight()?getWidth():getHeight());
                setOrigin(vector2.x, vector2.y);
                break;
        }
        body.getMassData().center.set(getWidth()/2,getHeight()/2);
        for(Fixture f : body.getFixtureList()) {
            f.setUserData(this);
        }
        if (visibilityControl) {
            setVisible(true);
        }
        afterAddToWorld();
    }

    protected void beforeAddToWorld()
    {

    }

    protected void afterAddToWorld()
    {

    }

    protected void beforeRemoveFromWorld()
    {

    }

    protected void afterRemoveFromWorld()
    {

    }

    @Override
    public boolean isActive() {
        return false;
    }

    public void removeFromStage(){
        if (getStage()==null) return;
        if (!flaggedForDeleteFromWorld) {
            getStage().getActors().removeValue(this, true);
        }else
        {
            flaggedForDeleteFromStage = true;
        }
    }

    @Override
    public void removeFromWorld() {
        //System.out.println(body.getWorld().isLocked());
        if (!body.getWorld().isLocked()) {
            beforeRemoveFromWorld();
            world.destroyBody(this.body);
            this.body = null;
            if (visibilityControl) {
                setVisible(false);
            }
            afterRemoveFromWorld();
        } else {
            flaggedForDeleteFromWorld = true;
        }
    }

    @Override
    public void setActive() {
        body.setActive(true);
    }

    @Override
    public void setInActive() {
        body.setActive(false);
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public Body getBody() {
        return body;
    }

    public boolean isVisibilityControl() {
        return visibilityControl;
    }

    public void setVisibilityControl(boolean visibilityControl) {
        this.visibilityControl = visibilityControl;
        setVisible(body != null);
    }

    @Override
    public boolean remove() {
        removeFromWorld();
        return super.remove();
    }

    public void setSensor(boolean sensor) {
        for (Fixture b:body.getFixtureList()) {
            b.setSensor(sensor);
        }
    }

    public void setFriction(float value) {
        for (Fixture b:body.getFixtureList()) {
            b.setFriction(value);
        }
    }

    public void setRestitution(float value) {
        for (Fixture b:body.getFixtureList()) {
            b.setRestitution(value);
        }
    }

    public void setDensity(float value) {
        for (Fixture b:body.getFixtureList()) {
            b.setDensity(value);
        }
    }


     @Override
    public void act(float delta) {
        super.act(delta);
        if (body!=null) {
            if (flaggedForDeleteFromWorld){
                removeFromWorld();
                flaggedForDeleteFromWorld = false;
                if (flaggedForDeleteFromStage) {
                    removeFromStage();
                    flaggedForDeleteFromStage = false;
                }
                return;
            }
            changeByWorld = true;
            setPosition(body.getPosition().x - getOriginX(), body.getPosition().y - getOriginY());
            setRotation(body.getAngle()* MathUtils.radiansToDegrees);
            changeByWorld = false;
        }
    }


    @Override
    protected void positionChanged() {
        super.positionChanged();
        if (body!=null && !changeByWorld) {
            body.setTransform(getX(), getY(), body.getAngle());
        }
    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        if (body!=null && !changeByWorld) {
            body.setTransform(getOriginX(), getOriginY(), getRotation()*MathUtils.degreesToRadians);
        }
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        if (body!=null && !changeByWorld) {
            removeFromWorld();
            addToWorld();
        }
    }


    public boolean isFlaggedForDelete() {
        return flaggedForDeleteFromWorld;
    }

    public void setPositionByLeftBottomCorner(float x, float y){
        setPosition(x+getOriginX(),y+getOriginY());
    }

    public float getXByLeftBottomCorner(){
        return getX()-getOriginX();
    }
    public float getYByLeftBottomCorner(){
        return getY()-getOriginY();
    }

}
