package hu.csanyzeg.master.MyBaseClasses.SimpleWorld;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SimpleBody extends MyRectangle {
    protected final HashMap<String, MyShape> shapeMap = new HashMap<>();


    public SimpleBody(float width, float height, float originX, float originY, float rotation, float x, float y, SimpleBodyType bodyType) {
        super(width, height, 0,0, originX,originY, rotation, 0, x, y, true);
        this.bodyType = bodyType;
    }

    /**
    * A világba rakás után csak a világon keresztül lehet megváltoztatni
     */
    protected SimpleBodyType bodyType;

    public static final String BASERECTANGLE = "BaseRectangle";
    public static final String BASECIRCLE = "BaseCircle";

    protected final Array<SimpleBody> connectedBodies = new Array<>();

    protected boolean rotationChanged = false;
    protected boolean sizeChanged = false;
    protected boolean positionChanged = false;

    protected float elapsedTime = 0;

    /** világegység / mp **/
    protected Vector2 linearVelocity = new Vector2(0,0);
    /** Fok / mp **/
    protected float angularVelocity = 0f;
    /** világegység / mp **/
    protected Vector2 sizeVelocity = new Vector2(0,0);

    protected Vector2 targetPosition;
    protected float targetRotation;
    protected Vector2 targetSize;

    protected float linearTimer = 0;
    protected float angularTimer = 0;
    protected float sizeTimer = 0;


    protected static float debugPointSize = 30f;



    public HashMap<String, MyShape> getCollisionShapeMap(){
        return shapeMap;
    }

    public SimpleBodyType getBodyType() {
        return bodyType;
    }


    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        linearTimer = 0;
        this.linearVelocity.set(linearVelocity);
    }

    public void setLinearVelocity(float x, float y) {
        linearTimer = 0;
        this.linearVelocity.set(x,y);
    }


    public float getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(float angularVelocity) {
        angularTimer = 0;
        this.angularVelocity = angularVelocity;
    }

    public Vector2 getSizeVelocity() {
        return sizeVelocity;
    }

    public void setSizeVelocity(Vector2 sizeVelocity) {
        sizeTimer = 0;
        this.sizeVelocity.set(sizeVelocity);
    }

    public void setSizeVelocity(float w, float h) {
        sizeTimer = 0;
        this.sizeVelocity.set(w,h);
    }

    public void moveTo(float x, float y, float sec){

    }

    public void rotateTo(float rot, float sec){

    }

    public void sizeTo(float width, float height, float sec){

    }


    public void scaleTo(float scale, float sec){

    }






    public void step(float deltaTime){
        elapsedTime += deltaTime;
        if (angularVelocity >0) {
            setRotation(getRotation() + deltaTime * angularVelocity);
        }
        if (linearVelocity.len() > 0) {
            setPosition(getLeftBottomX() + linearVelocity.x * deltaTime, getLeftBottomY() + linearVelocity.y * deltaTime);
        }
        if (sizeVelocity.len() > 0) {
            setSize(getWidth() + sizeVelocity.x * deltaTime, getHeight() + sizeVelocity.y * deltaTime);
        }
    }



    public void addBaseCollisionRectangleShape(){
        System.out.println(getWidth());
            addCollisionShape(BASERECTANGLE,new MyRectangle(getWidth(),getHeight(),0,0, getLeftBottomOriginX(), getLeftBottomOriginY(), getRotation(), 0, getX(), getY(),true));
    }

    public void addBaseCollisionCircleShape() {
        addCollisionShape(BASECIRCLE, new MyCircle((float) Math.sqrt(getWidth() * getHeight()) / 2, 0, 0, getLeftBottomOriginX(), getLeftBottomOriginY(), getX(), getY(), true));
    }

    public void removeBaseCollisionRectangleShape(){
        removeCollisionShape(BASERECTANGLE);
    }

    public void removeBaseCollisionCircleShape(){
        removeCollisionShape(BASECIRCLE);
    }

    /**
     *
     * @param name
     * @param shape A pozíciója és a forgatása relatív az bodytól
     */
    public void addCollisionShape(String name, MyShape shape){
        shape.setUserData(this);
        shapeMap.put(name, shape);
    }

    public void addCollisionRectangleShape(String name, float offsetX, float offsetY, float w, float h, float offsetR){
        MyRectangle shape = new MyRectangle(w,h,offsetX,offsetY, getLeftBottomOriginX(), getLeftBottomOriginY(), getRotation(), offsetR, getX(), getY(),true);
        shape.setUserData(this);
        shapeMap.put(name, shape);
    }


    public void addCollisionCircleShape(String name, float offsetX, float offsetY, float radius){
         MyCircle shape = new MyCircle(radius,offsetX,offsetY, getLeftBottomOriginX(), getLeftBottomOriginY(), getX(), getY(),true);
        shape.setUserData(this);
        shapeMap.put(name, shape);
    }

    public void removeCollisionShape(String name){
            shapeMap.remove(name);
    }

    public MyShape getCollisionShape(String name){
            return shapeMap.get(name);
    }


    public static float getDebugPointSize() {
        return debugPointSize;
    }

    public static void setDebugPointSize(float debugPointSize) {
        SimpleBody.debugPointSize = debugPointSize;
    }

    public static void drawDebugLines(Vector2[] v, ShapeRenderer shapes){
        for (int i = 0; i < v.length - 1; i++) {
            shapes.line(v[i].x, v[i].y, v[i + 1].x, v[i + 1].y);
        }
        shapes.line(v[v.length - 1].x, v[v.length - 1].y, v[0].x, v[0].y);
    }


    public static boolean overlaps(SimpleBody bodyA, SimpleBody bodyB){
        for(MyShape shapeA : bodyA.getCollisionShapeMap().values()){
            for(MyShape shapeB : bodyB.getCollisionShapeMap().values()){
                if (shapeA.active && shapeB.active) {
                    if (shapeA.overlaps(shapeB)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static ArrayList<String> getbodyAOverlappedShapeKeys(SimpleBody bodyA, SimpleBody bodyB){
        ArrayList<String> strings = new ArrayList<String>();
        for(Map.Entry shapeA : bodyA.getCollisionShapeMap().entrySet()){
            for(Map.Entry shapeB : bodyB.getCollisionShapeMap().entrySet()){
                if (((MyShape) shapeA.getValue()).active && ((MyShape) shapeB.getValue()).active) {
                    if (((MyShape) shapeA.getValue()).overlaps((MyShape) shapeB.getValue())) {
                        strings.add((String) (shapeA.getKey()));
                    }
                }
            }
        }
        return strings;
    }

    public static ArrayList<String> getbodyBOverlappedShapeKeys(SimpleBody bodyA, SimpleBody bodyB){
        return getbodyAOverlappedShapeKeys(bodyB,bodyA);
    }

    public ArrayList<String> getMyOverlappedShapeKeys(SimpleBody anotherbody){
        return getbodyAOverlappedShapeKeys(this, anotherbody);
    }

    public ArrayList<String> getOtherOverlappedShapeKeys(SimpleBody anotherbody){
        return getbodyAOverlappedShapeKeys(anotherbody, this);
    }

    public static ArrayList<MyShape> getbodyAOverlappedShapeValues(SimpleBody bodyA, SimpleBody bodyB){
        ArrayList<MyShape> strings = new ArrayList<MyShape>();
        for(Map.Entry shapeA : bodyA.getCollisionShapeMap().entrySet()){
            for(Map.Entry shapeB : bodyB.getCollisionShapeMap().entrySet()){
                if (((MyShape) shapeA.getValue()).active && ((MyShape) shapeB.getValue()).active) {
                    if (((MyShape) shapeA.getValue()).overlaps((MyShape) shapeB.getValue())) {
                        strings.add((MyShape) (shapeA.getValue()));
                    }
                }
            }
        }
        return strings;
    }

    public static ArrayList<MyShape> getbodyBOverlappedShapeValues(SimpleBody bodyA, SimpleBody bodyB){
        return getbodyAOverlappedShapeValues(bodyB,bodyA);
    }

    public ArrayList<MyShape> getMyOverlappedShapeValues(SimpleBody anotherbody){
        return getbodyAOverlappedShapeValues(this, anotherbody);
    }

    public ArrayList<MyShape> getOtherOverlappedShapeValues(SimpleBody anotherbody){
        return getbodyAOverlappedShapeValues(anotherbody, this);
    }

    public static ArrayList<Map.Entry<String, MyShape>> getBodyAOverlappedShapeEntries(SimpleBody bodyA, SimpleBody bodyB){
        ArrayList<Map.Entry<String, MyShape>> strings = new ArrayList<Map.Entry<String, MyShape>>();
        for(Map.Entry shapeA : bodyA.getCollisionShapeMap().entrySet()){
            for(Map.Entry shapeB : bodyB.getCollisionShapeMap().entrySet()){
                if (((MyShape) shapeA.getValue()).active && ((MyShape) shapeB.getValue()).active) {
                    if (((MyShape) shapeA.getValue()).overlaps((MyShape) shapeB.getValue())) {
                        strings.add((Map.Entry<String, MyShape>) (shapeA));
                    }
                }
            }
        }
        return strings;
    }

    public static ArrayList<Map.Entry<String, MyShape>> getbodyBOverlappedShapeEntries(SimpleBody bodyA, SimpleBody bodyB){
        return getBodyAOverlappedShapeEntries(bodyB,bodyA);
    }

    public ArrayList<Map.Entry<String, MyShape>> getMyOverlappedShapeEntries(SimpleBody anotherbody){
        return getBodyAOverlappedShapeEntries(this, anotherbody);
    }

    public ArrayList<Map.Entry<String, MyShape>> getOtherOverlappedShapeEntries(SimpleBody anotherbody){
        return getBodyAOverlappedShapeEntries(anotherbody, this);
    }

    public boolean overlaps(SimpleBody anotherbody){
        return overlaps(this, anotherbody);
    }


    protected void rotationChanged() {
        if (shapeMap == null) return;
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                shape.setRotation(getRotation());
            }
        }
    }
    protected void sizeChanged(float newW, float newH) {
        if (shapeMap == null) return;
        float w = newW / getWidth();
        float h = newH / getHeight();
            for (MyShape shape : shapeMap.values()) {
                shape.setSize(shape.getWidth() * w, shape.getHeight() * h);
                shape.setOffsetX(shape.getOffsetX()*w);
                shape.setOffsetY(shape.getOffsetY()*h);
                shape.setOriginX(shape.getOriginX()*w);
                shape.setOriginY(shape.getOriginY()*h);
            }
    }

    protected void positionChanged() {
        if (shapeMap == null) return;
        for (MyShape shape : shapeMap.values()) {
            shape.setPosition(getLeftBottomX(), getLeftBottomY());
        }
    }


    @Override
    public void setPosition(float X, float Y) {
        super.setPosition(X, Y);
        positionChanged();
    }

    @Override
    public void setRotation(float degree) {
        super.setRotation(degree);
        rotationChanged();
    }

    @Override
    public void setSize(float width, float height) {
        sizeChanged(width, height);
        super.setSize(width, height);

    }
}
