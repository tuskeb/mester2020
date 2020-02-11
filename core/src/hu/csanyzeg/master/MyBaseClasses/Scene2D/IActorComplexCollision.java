package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.MyCircle;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.MyRectangle;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.MyShape;

@Deprecated
public interface IActorComplexCollision {

    @Deprecated
    public HashMap<String, MyShape> getCollisionShapeMap();

    @Deprecated
    public static float debugPointSize = 30f;


    @Deprecated
    public default void addBaseCollisionRectangleShape(){
        Actor actor = (Actor)this;
        addCollisionShape("BaseRectangle",new MyRectangle(actor.getWidth(),actor.getHeight(),0,0,actor.getOriginX(), actor.getOriginY(), actor.getRotation(), 0, true));
    }

    @Deprecated
    public default void addBaseCollisionCircleShape() {
        Actor actor = (Actor)this;
        addCollisionShape("BaseCircle", new MyCircle((float) Math.sqrt(actor.getWidth() * actor.getHeight()) / 2, 0, 0, actor.getOriginX(), actor.getOriginY(), actor.getX(), actor.getY(), true));
    }

    @Deprecated
    public default void removeBaseCollisionRectangleShape(){
        removeCollisionShape("BaseRectangle");
    }

    @Deprecated
    public default void removeBaseCollisionCircleShape(){
        removeCollisionShape("BaseCircle");
    }

    /**
     *
     * @param name
     * @param shape A pozíciója és a forgatása relatív az Actortól
     */
    @Deprecated
    public default void addCollisionShape(String name, MyShape shape){
        shape.setUserData(this);
        getCollisionShapeMap().put(name, shape);
    }

    @Deprecated
    public default void removeCollisionShape(String name){
        getCollisionShapeMap().remove(name);
    }

    @Deprecated
    public default MyShape getCollisionShape(String name){
        return getCollisionShapeMap().get(name);
    }


    @Deprecated
    public static float getDebugPointSize() {
        return debugPointSize;
    }

    @Deprecated
    public static void drawDebugLines(Vector2[] v, ShapeRenderer shapes){
        for (int i = 0; i < v.length - 1; i++) {
            shapes.line(v[i].x, v[i].y, v[i + 1].x, v[i + 1].y);
        }
        shapes.line(v[v.length - 1].x, v[v.length - 1].y, v[0].x, v[0].y);
    }

    @Deprecated
    public static boolean overlaps(IActorComplexCollision actorA, IActorComplexCollision actorB){
        if (actorA.getCollisionShapeMap() == null) return false;
        if (actorB.getCollisionShapeMap() == null) return false;
        for(MyShape shapeA : actorA.getCollisionShapeMap().values()){
            for(MyShape shapeB : actorB.getCollisionShapeMap().values()){
                if (shapeA.active && shapeB.active) {
                    if (shapeA.overlaps(shapeB)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Deprecated
    public static ArrayList<String> getActorAOverlappedShapeKeys(IActorComplexCollision actorA, IActorComplexCollision actorB){
        ArrayList<String> strings = new ArrayList<String>();
        for(Map.Entry shapeA : actorA.getCollisionShapeMap().entrySet()){
            for(Map.Entry shapeB : actorB.getCollisionShapeMap().entrySet()){
                if (((MyShape) shapeA.getValue()).active && ((MyShape) shapeB.getValue()).active) {
                    if (((MyShape) shapeA.getValue()).overlaps((MyShape) shapeB.getValue())) {
                        strings.add((String) (shapeA.getKey()));
                    }
                }
            }
        }
        return strings;
    }

    @Deprecated
    public static ArrayList<String> getActorBOverlappedShapeKeys(IActorComplexCollision actorA, IActorComplexCollision actorB){
        return getActorAOverlappedShapeKeys(actorB,actorA);
    }

    @Deprecated
    public default ArrayList<String> getMyOverlappedShapeKeys(IActorComplexCollision anotherActor){
        return getActorAOverlappedShapeKeys(this, anotherActor);
    }

    @Deprecated
    public default ArrayList<String> getOtherOverlappedShapeKeys(MyActor anotherActor){
        return getActorAOverlappedShapeKeys(anotherActor, this);
    }

    @Deprecated
    public static ArrayList<MyShape> getActorAOverlappedShapeValues(IActorComplexCollision actorA, IActorComplexCollision actorB){
        ArrayList<MyShape> strings = new ArrayList<MyShape>();
        for(Map.Entry shapeA : actorA.getCollisionShapeMap().entrySet()){
            for(Map.Entry shapeB : actorB.getCollisionShapeMap().entrySet()){
                if (((MyShape) shapeA.getValue()).active && ((MyShape) shapeB.getValue()).active) {
                    if (((MyShape) shapeA.getValue()).overlaps((MyShape) shapeB.getValue())) {
                        strings.add((MyShape) (shapeA.getValue()));
                    }
                }
            }
        }
        return strings;
    }

    @Deprecated
    public static ArrayList<MyShape> getActorBOverlappedShapeValues(IActorComplexCollision actorA, IActorComplexCollision actorB){
        return getActorAOverlappedShapeValues(actorB,actorA);
    }

    @Deprecated
    public default ArrayList<MyShape> getMyOverlappedShapeValues(IActorComplexCollision anotherActor){
        return getActorAOverlappedShapeValues(this, anotherActor);
    }

    @Deprecated
    public default ArrayList<MyShape> getOtherOverlappedShapeValues(IActorComplexCollision anotherActor){
        return getActorAOverlappedShapeValues(anotherActor, this);
    }

    @Deprecated
    public static ArrayList<Map.Entry<String, MyShape>> getActorAOverlappedShapeEntries(IActorComplexCollision actorA, IActorComplexCollision actorB){
        ArrayList<Map.Entry<String, MyShape>> strings = new ArrayList<Map.Entry<String, MyShape>>();
        for(Map.Entry shapeA : actorA.getCollisionShapeMap().entrySet()){
            for(Map.Entry shapeB : actorB.getCollisionShapeMap().entrySet()){
                if (((MyShape) shapeA.getValue()).active && ((MyShape) shapeB.getValue()).active) {
                    if (((MyShape) shapeA.getValue()).overlaps((MyShape) shapeB.getValue())) {
                        strings.add((Map.Entry<String, MyShape>) (shapeA));
                    }
                }
            }
        }
        return strings;
    }

    @Deprecated
    public static ArrayList<Map.Entry<String, MyShape>> getActorBOverlappedShapeEntries(IActorComplexCollision actorA, IActorComplexCollision actorB){
        return getActorAOverlappedShapeEntries(actorB,actorA);
    }

    @Deprecated
    public default ArrayList<Map.Entry<String, MyShape>> getMyOverlappedShapeEntries(IActorComplexCollision anotherActor){
        return getActorAOverlappedShapeEntries(this, anotherActor);
    }

    @Deprecated
    public default ArrayList<Map.Entry<String, MyShape>> getOtherOverlappedShapeEntries(IActorComplexCollision anotherActor){
        return getActorAOverlappedShapeEntries(anotherActor, this);
    }

    @Deprecated
    public default boolean overlaps(IActorComplexCollision anotherActor){
        return overlaps(this, anotherActor);
    }


    @Deprecated
    public default void drawComplexCollisionDebugBounds(ShapeRenderer shapes) {
        if (getCollisionShapeMap() != null) {
            Actor actor = (Actor) this;
            if (getCollisionShapeMap() != null) {
                IElapsedTime iElapsedTime = (IElapsedTime) this;

                switch ((((int) ((iElapsedTime.getElapsedTime()) * 4)) % 4)) {
                    case 0:
                        shapes.setColor(Color.WHITE);
                        break;
                    case 1:
                        shapes.setColor(Color.GRAY);
                        break;
                    case 2:
                        shapes.setColor(Color.DARK_GRAY);
                        break;
                }

                if (((((int) ((iElapsedTime.getElapsedTime()) * 4)) % 4)) < 3) {
                    for (MyShape shape : getCollisionShapeMap().values()) {
                        if (shape.active) {
                            drawDebugLines(shape.getCorners(), shapes);
                            //shapes.circle(shape.originX + shape.centerX + shape.offsetX, shape.originY + shape.centerY + shape.offsetY, getWidth() / debugPointSize, 7);
                            //shapes.circle(shape.realCenterX, shape.realCenterY, getWidth() / debugPointSize, 3);
                            shapes.circle(shape.getOriginX() + shape.getCenterX() + shape.getOffsetX(), shape.getOriginY() + shape.getCenterY() + shape.getOffsetY(), actor.getWidth() / debugPointSize, 7);
                            shapes.circle(shape.getRealCenterX(), shape.getRealCenterY(), actor.getWidth() / debugPointSize, 3);
                        }
                    }
                }
            }


            shapes.setColor(Color.GREEN);
            shapes.circle(actor.getOriginX() + actor.getX(), actor.getOriginY() + actor.getY(), actor.getWidth() / debugPointSize, 3);
        }
    }

    @Deprecated
    public default void setsizeComplexCollision(float oldWidth, float oldHeight, float newWidth, float newHeight){
        if (getCollisionShapeMap() != null) {
            float w = newWidth / oldWidth;
            float h = newHeight / oldHeight;
            for (MyShape shape : getCollisionShapeMap().values()) {
                shape.setSize(shape.getWidth() * w, shape.getHeight() * h);
                shape.setOffsetX(shape.getOffsetX() * w);
                shape.setOffsetY(shape.getOffsetY() * h);
                shape.setOriginX(shape.getOriginX() * w);
                shape.setOriginY(shape.getOriginY() * h);
            }
        }
    }

    @Deprecated
    public default void positionchangedComplexCollision(){
        if (getCollisionShapeMap() != null) {
            Actor actor = (Actor) this;
            for (MyShape shape : getCollisionShapeMap().values()) {
                shape.setPosition(actor.getX(), actor.getY());
            }
        }
    }

    @Deprecated
    public default void rotationchangedComplexCollision(){
        if (getCollisionShapeMap() != null) {
            Actor actor = (Actor) this;
            for (MyShape shape : getCollisionShapeMap().values()) {
                shape.setRotation(actor.getRotation());
            }
        }
    }


    @Deprecated
    public default void originchangedComplexCollision(){
        if (getCollisionShapeMap() != null) {
            Actor actor = (Actor) this;
            for (MyShape shape : getCollisionShapeMap().values()) {
                shape.setOrigin(actor.getOriginX(), actor.getOriginY());
            }
        }
    }
}
