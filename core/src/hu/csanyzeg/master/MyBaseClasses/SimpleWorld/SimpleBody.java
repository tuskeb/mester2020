package hu.csanyzeg.master.MyBaseClasses.SimpleWorld;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;


public class SimpleBody extends MyRectangle {
    protected final HashMap<String, MyShape> shapeMap = new HashMap<>();



    public SimpleBody(float width, float height, float originX, float originY, float rotation, float x, float y, SimpleBodyType bodyType, Color color) {
        super(width, height, 0,0, originX,originY, rotation, 0, x, y, true);
        this.bodyType = bodyType;
        this.color = color;
    }

    /**
    * A világba rakás után csak a világon keresztül lehet megváltoztatni
     */
    protected SimpleBodyType bodyType;
    protected SimpleBodyBehaviorListener simpleBodyBehaviorListener = new SimpleBodyBehaviorListener();

    public SimpleBodyBehaviorListener getSimpleBodyBehaviorListener() {
        return simpleBodyBehaviorListener;
    }

    public void setSimpleBodyBehaviorListener(SimpleBodyBehaviorListener simpleBodyBehaviorListener) {
        this.simpleBodyBehaviorListener = simpleBodyBehaviorListener;
    }

    public static final String BASERECTANGLE = "BaseRectangle";
    public static final String BASECIRCLE = "BaseCircle";

    protected final Array<SimpleBody> connectedBodies = new Array<>();

    protected float elapsedTime = 0;

    protected PositionRule sizingPositionRule = PositionRule.Origin;
    protected Color color = Color.WHITE;


    /** világegység / mp **/
    protected Vector2 linearVelocity = new Vector2(0,0);
    /** Fok / mp **/
    protected float angularVelocity = 0f;
    /** világegység / mp **/
    protected Vector2 sizeVelocity = new Vector2(0,0);
    /** színválzotás / mp **/
    protected float colorVelocityA = 0f;
    protected float colorVelocityR = 0f;
    protected float colorVelocityG = 0f;
    protected float colorVelocityB = 0f;


    protected Vector2 targetPosition = new Vector2();
    protected float targetRotation = 0;
    protected Vector2 targetSize = new Vector2();
    protected Color targetColor = Color.WHITE;



    protected float linearTimer = INVALIDTIMER;
    protected float angularTimer = INVALIDTIMER;
    protected float sizeTimer = INVALIDTIMER;
    protected float colorTimer = INVALIDTIMER;
    protected static final float INVALIDTIMER = Float.NEGATIVE_INFINITY;


    protected static float debugPointSize = 30f;
    protected boolean changedByWorld = false;


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
        linearTimer = INVALIDTIMER;
        this.linearVelocity.set(linearVelocity);
    }

    public void setLinearVelocity(float x, float y) {
        linearTimer = INVALIDTIMER;
        this.linearVelocity.set(x,y);
    }

    public float getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(float angularVelocity) {
        angularTimer = INVALIDTIMER;
        this.angularVelocity = angularVelocity;
    }

    public Vector2 getSizeVelocity() {
        return sizeVelocity;
    }

    public void setSizeVelocity(Vector2 sizeVelocity) {
        sizeTimer = INVALIDTIMER;
        this.sizeVelocity.set(sizeVelocity);
    }

    public void setSizeVelocity(float w, float h) {
        sizeTimer = INVALIDTIMER;
        this.sizeVelocity.set(w,h);
    }


    public void moveTo(float x, float y, float sec, PositionRule positionRule) {
        switch (positionRule) {
            case Center:
                targetPosition.set(x - getRealCenterX() + getLeftBottomX(), y - getRealCenterY() + getLeftBottomY());
                linearVelocity.set((x - getRealCenterX()) / sec, (y - getRealCenterY()) / sec);
                break;
            case LeftBottom:
                targetPosition.set(x, y);
                linearVelocity.set((x - getLeftBottomX()) / sec, (y - getLeftBottomY()) / sec);
                break;
            case Origin:
                targetPosition.set(x - getLeftBottomOriginX(), y - getLeftBottomOriginY());
                linearVelocity.set((x - getLeftBottomOriginX() - getLeftBottomX()) / sec, (y - getLeftBottomOriginY() - getLeftBottomY()) / sec);
                break;
        }
        linearTimer = sec;
    }


    public void rotateTo(float rot, float sec, Direction direction){
        float cw = ( rot < getRotation() ?  rot  - getRotation() : - 360 + rot - getRotation()) / sec;
        float ccw = (rot < getRotation() ? rot + 360 - getRotation() : rot - getRotation()) / sec;
        switch (direction){
            case ClockWise:
                angularVelocity = cw;
                break;
            case CounterClockWise:
                angularVelocity = ccw;
                break;
            case Shorter:
                angularVelocity = Math.abs(cw) < Math.abs(ccw) ? cw : ccw;
                break;
            case Longer:
                angularVelocity = Math.abs(cw) >= Math.abs(ccw) ? cw : ccw;
                break;
        }
        angularTimer = sec;
        targetRotation = rot;
    }

    public void sizeTo(float width, float height, float sec, PositionRule sizingPositionRule) {
        this.sizingPositionRule = sizingPositionRule;
        targetSize.set(width, height);
        sizeVelocity.set((width - this.width) / sec, (height - this.height) / sec);
        sizeTimer = sec;
    }

    public void scaleTo(float scale, float sec, PositionRule sizingPositionRule){
        sizeTo(width * scale, height * scale, sec, sizingPositionRule);
    }

    public void colorTo(Color color, float sec){
        targetColor = color;
        colorVelocityA = -(this.color.a-color.a)/sec;
        colorVelocityR = -(this.color.r-color.r)/sec;
        colorVelocityG = -(this.color.g-color.g)/sec;
        colorVelocityB = -(this.color.b-color.b)/sec;
    }






    public void step(float deltaTime){
        changedByWorld = true;
        elapsedTime += deltaTime;

        if (angularTimer >= 0f){
            angularTimer -= deltaTime;
            if (angularTimer <= 0f){
                angularTimer = INVALIDTIMER;
                angularVelocity = 0f;
                setRotation(targetRotation);
                simpleBodyBehaviorListener.onStop(this);
            }
        }
        if (angularVelocity != 0) {
            setRotation(getRotation() + deltaTime * angularVelocity);
        }



        if (linearTimer >= 0f){
            linearTimer -= deltaTime;
            if (linearTimer <= 0f){
                linearTimer = INVALIDTIMER;
                linearVelocity.set(0f,0f);
                setPosition(targetPosition.x, targetPosition.y);
                simpleBodyBehaviorListener.onStop(this);
            }
        }
        if (linearVelocity.len() != 0f) {
            setPosition(getLeftBottomX() + linearVelocity.x * deltaTime, getLeftBottomY() + linearVelocity.y * deltaTime);
        }


        if (sizeTimer >= 0f){
            sizeTimer -= deltaTime;
            if (sizeTimer <= 0f){
                sizeTimer = INVALIDTIMER;
                sizeVelocity.set(0f,0f);
                setSize(targetSize.x, targetSize.y);
                simpleBodyBehaviorListener.onStop(this);
            }
        }
        if (sizeVelocity.len() != 0f) {
            switch (sizingPositionRule) {
                case Center:
                    setSizeByCenter(getWidth() + sizeVelocity.x * deltaTime, getHeight() + sizeVelocity.y * deltaTime);
                break;
                case LeftBottom:
                    setSize(getWidth() + sizeVelocity.x * deltaTime, getHeight() + sizeVelocity.y * deltaTime);
                    break;
                case Origin:
                    setSizeByOrigin(getWidth() + sizeVelocity.x * deltaTime, getHeight() + sizeVelocity.y * deltaTime);
                    break;
            }
        }
        changedByWorld = false;


        if (colorTimer >= 0f){
            colorTimer -= deltaTime;
            if (colorTimer <= 0f){
                colorTimer = INVALIDTIMER;
                colorVelocityA=0;
                colorVelocityR=0;
                colorVelocityG=0;
                colorVelocityB=0;
                color = targetColor;
                simpleBodyBehaviorListener.onStop(this);
            }
        }
        if (colorVelocityA != 0f || colorVelocityB != 0f || colorVelocityG != 0f || colorVelocityR != 0f){
            color.add(colorVelocityR * deltaTime, colorVelocityG * deltaTime, colorVelocityB* deltaTime, colorVelocityA* deltaTime);
        }
    }

    public void setColorVelocity(float r, float g, float b, float a) {
        this.colorVelocityA = a;
        this.colorVelocityR = r;
        this.colorVelocityG = g;
        this.colorVelocityB = b;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void addBaseCollisionRectangleShape(){
        System.out.println(getWidth());
            addCollisionShape(BASERECTANGLE,new MyRectangle(width, height,offsetX,offsetY, originX, originY, rotation, offsetRotation, centerX, centerY,false));
            //addCollisionShape(BASERECTANGLE,new MyRectangle(getWidth(),getHeight(),0,0, getLeftBottomOriginX(), getLeftBottomOriginY(), getRotation(), 0, getX(), getY(),true));
    }

    public void addBaseCollisionCircleShape() {
        addCollisionShape(BASECIRCLE, new MyCircle(width, height,offsetX,offsetY, originX, originY, rotation, offsetRotation, centerX, centerY,false));
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
        MyRectangle shape = new MyRectangle(w,h,offsetX,offsetY, getLeftBottomOriginX(), getLeftBottomOriginY(), getRotation(), offsetR, getLeftBottomX(), getLeftBottomY(),true);
        shape.setUserData(this);
        shapeMap.put(name, shape);
    }


    public void addCollisionCircleShape(String name, float offsetX, float offsetY, float radius, float offsetR){
        MyCircle shape = new MyCircle(radius,offsetX,offsetY, getLeftBottomOriginX(), getLeftBottomOriginY(), getRotation(), offsetR, getLeftBottomX(), getLeftBottomY(),true);
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


    @Override
    protected void rotationChanged(float newR, float oldR) {
        if (shapeMap == null) return;
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                shape.setRotation(newR);
            }
        }
        if (!changedByWorld){
            angularTimer = INVALIDTIMER;
        }
    }

    @Override
    protected void sizeChanged(float newW, float newH, float oldW, float oldH) {
        if (shapeMap == null) return;
        float w = newW / oldW;
        float h = newH / oldH;
        for (MyShape shape : shapeMap.values()) {
            shape.setSize(shape.getWidth() * w, shape.getHeight() * h);
            shape.setOffset(shape.getOffsetX()*w, shape.getOffsetY()*h);
            shape.setOrigin(getLeftBottomOriginX(), getLeftBottomOriginY());
        }
        if (!changedByWorld){
            sizeTimer = INVALIDTIMER;
        }
    }

    @Override
    protected void originChanged(float newX, float newY, float oldX, float oldY){
        if (shapeMap == null) return;
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                shape.setOrigin (getLeftBottomOriginX(), getLeftBottomOriginY());
            }
        }
    }

    @Override
    protected void positionChanged(float newX, float newY, float oldX, float oldY) {
        if (shapeMap == null) return;
        for (MyShape shape : shapeMap.values()) {
            shape.setPosition(getLeftBottomX(), getLeftBottomY());
        }
        if (!changedByWorld){
            linearTimer = INVALIDTIMER;
        }
    }

    protected void colorChanged(Color newC, Color oldC) {
        if (!changedByWorld){
            colorTimer = INVALIDTIMER;
        }
    }
}
