package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.InitableInterface;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.MyCircle;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.MyRectangle;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.MyShape;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;
import hu.csanyzeg.master.MyBaseClasses.WorldHelper.WorldHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyActor extends Actor implements InitableInterface, IZindex, WorldActor {

    WorldHelper<?, Actor> actorWorldHelper = null;

    public void setActorWorldHelper(WorldHelper<?, Actor> worldHelper){
        actorWorldHelper = worldHelper;
    }

    public final Array<Timer> timers = new Array<>();

    public void addTimer(Timer timer){
        timers.add(timer);
    }

    public void removeTimer(Timer timer){
        timers.removeValue(timer, true);
    }

    public Array<Timer> getTimers() {
        return timers;
    }

    @Override
    public WorldHelper<?, Actor> getActorWorldHelper() {
        return actorWorldHelper;
    }

    protected int zIndex = 0;

    @Override
    public boolean setZIndex(int index) {
        this.zIndex = index;
        Group parent = this.getParent();
        if (parent == null) return false;
        Array<Actor> children = parent.getChildren();
        if (children.size == 1) return false;
        if (getStage() != null){
            if (getStage() instanceof MyStage){
                ((MyStage)getStage()).sortActorsByZindex();
                return true;
            }
        }

        return false;
    }

    @Override
    public int getZIndex() {
        return zIndex;
    }

    protected float elapsedTime = 0;

    @Deprecated
    protected HashMap<String, MyShape> shapeMap;

    @Deprecated
    protected static float debugPointSize = 30f;

    @Deprecated
    public HashMap<String, MyShape> getCollisionShapeMap(){
        return shapeMap;
    }

    @Deprecated
    public void addBaseCollisionRectangleShape(){
        addCollisionShape("BaseRectangle",new MyRectangle(getWidth(),getHeight(),0,0,getOriginX(), getOriginY(), getRotation(), 0, true));
    }

    @Deprecated
    public void addBaseCollisionCircleShape() {
        addCollisionShape("BaseCircle", new MyCircle((float) Math.sqrt(getWidth() * getHeight()) / 2, 0, 0, getOriginX(), getOriginY(), getX(), getY(), true));
    }

    @Deprecated
    public void removeBaseCollisionRectangleShape(){
        removeCollisionShape("BaseRectangle");
    }

    @Deprecated
    public void removeBaseCollisionCircleShape(){
        removeCollisionShape("BaseCircle");
    }

    /**
     *
     * @param name
     * @param shape A pozíciója és a forgatása relatív az Actortól
     */
    @Deprecated
    public void addCollisionShape(String name, MyShape shape){
        if (shapeMap == null){
            shapeMap = new HashMap<String, MyShape>();
        }
        //shape.setOffset(shape.getX(), shape.getY());
        //shape.setBodyPosition(getX(),getY());
        shape.setUserData(this);
        shapeMap.put(name, shape);
    }

    @Deprecated
    public void removeCollisionShape(String name){
        if (shapeMap != null){
            shapeMap.remove(name);
        }
    }

    @Deprecated
    public MyShape getCollisionShape(String name){
        if (shapeMap != null){
            return shapeMap.get(name);
        }
        return null;
    }


    @Deprecated
    public static float getDebugPointSize() {
        return debugPointSize;
    }

    @Deprecated
    public static void setDebugPointSize(float debugPointSize) {
        MyActor.debugPointSize = debugPointSize;
    }

    @Deprecated
    public static void drawDebugLines(Vector2[] v, ShapeRenderer shapes){
        for (int i = 0; i < v.length - 1; i++) {
            shapes.line(v[i].x, v[i].y, v[i + 1].x, v[i + 1].y);
        }
        shapes.line(v[v.length - 1].x, v[v.length - 1].y, v[0].x, v[0].y);
    }

    @Deprecated
    @Override
    protected void drawDebugBounds(ShapeRenderer shapes) {
        super.drawDebugBounds(shapes);

        if (shapeMap != null) {
            switch ((((int) ((elapsedTime) * 4)) % 4)) {
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
            if (((((int) ((elapsedTime) * 4)) % 4)) < 3) {
                for (MyShape shape : shapeMap.values()) {
                    if (shape.active) {
                        drawDebugLines(shape.getCorners(), shapes);
                        //shapes.circle(shape.originX + shape.centerX + shape.offsetX, shape.originY + shape.centerY + shape.offsetY, getWidth() / debugPointSize, 7);
                        //shapes.circle(shape.realCenterX, shape.realCenterY, getWidth() / debugPointSize, 3);
                        shapes.circle(shape.getOriginX() + shape.getCenterX() + shape.getOffsetX(), shape.getOriginY() + shape.getCenterY() + shape.getOffsetY(), getWidth() / debugPointSize, 7);
                        shapes.circle(shape.getRealCenterX(), shape.getRealCenterY(), getWidth() / debugPointSize, 3);
                    }
                }
            }
        }


        shapes.setColor(Color.GREEN);
        shapes.circle(getOriginX() + getX(), getOriginY() + getY(), getWidth() / debugPointSize, 3);
    }

    protected final MyGame game;

    public MyActor(MyGame game) {
        super(); this.game = game;
    }

    @Override
    public void init() {
        setDebug(game.debug);
        setOrigintoCenter();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        elapsedTime += delta;

        for(Timer t : timers){
            t.act(delta);
        }

        if (actorWorldHelper != null){
            actorWorldHelper.act(delta);
            if(actorWorldHelper.getBody()!= null) {
                actorWorldHelper.beginUpdate();
                if (actorWorldHelper.positionChanged) {
                    setPosition(actorWorldHelper.getBodyX(), actorWorldHelper.getBodyY());
                }
                if (actorWorldHelper.originChanged) {
                    setOrigin(actorWorldHelper.getBodyOriginX(), actorWorldHelper.getBodyOriginY());
                }
                if (actorWorldHelper.sizeChanged) {
                    setSize(actorWorldHelper.getBodyWidth(), actorWorldHelper.getBodyHeight());
                }
                if (actorWorldHelper.rotationChanged) {
                    setRotation(actorWorldHelper.getBodyRotation());
                }
                if (actorWorldHelper.colorChanged){
                    setColor(actorWorldHelper.getBodyColor());
                }
                actorWorldHelper.endUpdate();
            }
        }
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
        if (actorWorldHelper != null && !actorWorldHelper.isModifyedByWorld()){
            actorWorldHelper.setBodySize(getWidth(), getHeight());
        }
    }

    public void setOrigintoCenter(){
        setOrigin(getWidth()/2, getHeight()/2);
    }

    @Override
    public void setSize(float width, float height) {
        float w = width / getWidth();
        float h = height / getHeight();
        if (shapeMap!=null) {
            for (MyShape shape : shapeMap.values()) {
                shape.setSize(shape.getWidth() * w, shape.getHeight() * h);
                shape.setOffsetX(shape.getOffsetX()*w);
                shape.setOffsetY(shape.getOffsetY()*h);
                shape.setOriginX(shape.getOriginX()*w);
                shape.setOriginY(shape.getOriginY()*h);
            }
        }
        setOrigin(getOriginX() * w, getOriginY() * h);
        super.setSize(width, height);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                shape.setPosition(getX(),getY());
            }
        }
        if (actorWorldHelper != null && !actorWorldHelper.isModifyedByWorld()){
            actorWorldHelper.setBodyPosition(getX(), getY());
        }

    }

    @Override
    protected void rotationChanged() {
        super.rotationChanged();
        if (shapeMap!=null) {
            for (MyShape shape:shapeMap.values()) {
                shape.setRotation(getRotation());
            }
        }
        if (actorWorldHelper != null && !actorWorldHelper.isModifyedByWorld()){
            actorWorldHelper.setBodyRotation(getRotation());
        }
    }

    @Deprecated
    public static boolean overlaps(MyActor actorA, MyActor actorB){
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
    public static ArrayList<String> getActorAOverlappedShapeKeys(MyActor actorA, MyActor actorB){
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
    public static ArrayList<String> getActorBOverlappedShapeKeys(MyActor actorA, MyActor actorB){
        return getActorAOverlappedShapeKeys(actorB,actorA);
    }

    @Deprecated
    public ArrayList<String> getMyOverlappedShapeKeys(MyActor anotherActor){
        return getActorAOverlappedShapeKeys(this, anotherActor);
    }

    @Deprecated
    public ArrayList<String> getOtherOverlappedShapeKeys(MyActor anotherActor){
        return getActorAOverlappedShapeKeys(anotherActor, this);
    }

    @Deprecated
    public static ArrayList<MyShape> getActorAOverlappedShapeValues(MyActor actorA, MyActor actorB){
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
    public static ArrayList<MyShape> getActorBOverlappedShapeValues(MyActor actorA, MyActor actorB){
        return getActorAOverlappedShapeValues(actorB,actorA);
    }

    @Deprecated
    public ArrayList<MyShape> getMyOverlappedShapeValues(MyActor anotherActor){
        return getActorAOverlappedShapeValues(this, anotherActor);
    }

    @Deprecated
    public ArrayList<MyShape> getOtherOverlappedShapeValues(MyActor anotherActor){
        return getActorAOverlappedShapeValues(anotherActor, this);
    }

    @Deprecated
    public static ArrayList<Map.Entry<String, MyShape>> getActorAOverlappedShapeEntries(MyActor actorA, MyActor actorB){
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
    public static ArrayList<Map.Entry<String, MyShape>> getActorBOverlappedShapeEntries(MyActor actorA, MyActor actorB){
        return getActorAOverlappedShapeEntries(actorB,actorA);
    }

    @Deprecated
    public ArrayList<Map.Entry<String, MyShape>> getMyOverlappedShapeEntries(MyActor anotherActor){
        return getActorAOverlappedShapeEntries(this, anotherActor);
    }

    @Deprecated
    public ArrayList<Map.Entry<String, MyShape>> getOtherOverlappedShapeEntries(MyActor anotherActor){
        return getActorAOverlappedShapeEntries(anotherActor, this);
    }

    @Deprecated
    public boolean overlaps(MyActor anotherActor){
        return overlaps(this, anotherActor);
    }


    public void resetElapsedTime()
    {
        elapsedTime = 0;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }



    public void fitToViewportRealWorldSizeWithBlackBars() {
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            float mulw = ev.getWorldWidth() / getWidth();
            float mulh = ev.getWorldHeight() / getHeight();
            if (mulw < mulh) {
                setSize(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSize(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }

    public void fitToViewportMinWorldSizeWithBlackBars(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            float mulw = ev.getMinWorldWidth() / getWidth();
            float mulh = ev.getMinWorldHeight() / getHeight();
            if (mulw < mulh) {
                setSize(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSize(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }


    public void fitToViewportMaxWorldSizeWithBlackBars(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            float mulw = ev.getMaxWorldWidth() / getWidth();
            float mulh = ev.getMaxWorldHeight() / getHeight();
            if (mulw < mulh) {
                setSize(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSize(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }

    public void stretchToViewportRealWorldSizeWithoutBlackBars(){
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            setSize(ev.getWorldWidth(), ev.getWorldHeight());
        }
    }

    public void stretchToViewportMinWorldSizeWithoutBlackBars(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            setSize(ev.getMinWorldWidth(), ev.getMinWorldHeight());
        }
    }


    public void stretchToViewportMaxWorldSizeWithoutBlackBars(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            setSize(ev.getMaxWorldWidth(), ev.getMaxWorldHeight());
        }
    }

    public void fitToViewportRealWorldSizeWithoutBlackBars(){
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            float mulw = ev.getWorldWidth() / getWidth();
            float mulh = ev.getWorldHeight() / getHeight();
            if (mulw > mulh) {
                setSize(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSize(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }

    public void fitToViewportMinWorldSizeWithoutBlackBars(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            float mulw = ev.getMinWorldWidth() / getWidth();
            float mulh = ev.getMinWorldHeight() / getHeight();
            if (mulw > mulh) {
                setSize(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSize(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }


    public void fitToViewportMaxWorldSizeWithoutBlackBars(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            float mulw = ev.getMaxWorldWidth() / getWidth();
            float mulh = ev.getMaxWorldHeight() / getHeight();
            if (mulw > mulh) {
                setSize(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSize(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }




    public void fitToViewportRealWorldSizeWithBlackBarsByOrigin() {
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            float mulw = ev.getWorldWidth() / getWidth();
            float mulh = ev.getWorldHeight() / getHeight();
            if (mulw < mulh) {
                setSizeByOrigin(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSizeByOrigin(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }

    public void fitToViewportMinWorldSizeWithBlackBarsByOrigin(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            float mulw = ev.getMinWorldWidth() / getWidth();
            float mulh = ev.getMinWorldHeight() / getHeight();
            if (mulw < mulh) {
                setSizeByOrigin(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSizeByOrigin(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }


    public void fitToViewportMaxWorldSizeWithBlackBarsByOrigin(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            float mulw = ev.getMaxWorldWidth() / getWidth();
            float mulh = ev.getMaxWorldHeight() / getHeight();
            if (mulw < mulh) {
                setSizeByOrigin(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSizeByOrigin(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }

    public void stretchToViewportRealWorldSizeWithoutBlackBarsByOrigin(){
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            setSizeByOrigin(ev.getWorldWidth(), ev.getWorldHeight());
        }
    }

    public void stretchToViewportMinWorldSizeWithoutBlackBarsByOrigin(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            setSizeByOrigin(ev.getMinWorldWidth(), ev.getMinWorldHeight());
        }
    }


    public void stretchToViewportMaxWorldSizeWithoutBlackBarsByOrigin(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            setSizeByOrigin(ev.getMaxWorldWidth(), ev.getMaxWorldHeight());
        }
    }

    public void fitToViewportRealWorldSizeWithoutBlackBarsByOrigin(){
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            float mulw = ev.getWorldWidth() / getWidth();
            float mulh = ev.getWorldHeight() / getHeight();
            if (mulw > mulh) {
                setSizeByOrigin(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSizeByOrigin(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }

    public void fitToViewportMinWorldSizeWithoutBlackBarsByOrigin(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            float mulw = ev.getMinWorldWidth() / getWidth();
            float mulh = ev.getMinWorldHeight() / getHeight();
            if (mulw > mulh) {
                setSizeByOrigin(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSizeByOrigin(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }


    public void fitToViewportMaxWorldSizeWithoutBlackBarsByOrigin(){
        Stage s;
        ExtendViewport ev;
        if ((s = getStage()) != null) {
            ev = (ExtendViewport)s.getViewport();
            float mulw = ev.getMaxWorldWidth() / getWidth();
            float mulh = ev.getMaxWorldHeight() / getHeight();
            if (mulw > mulh) {
                setSizeByOrigin(getWidth() * mulw, getHeight() * mulw);
            } else {
                setSizeByOrigin(getWidth() * mulh, getHeight() * mulh);
            }
        }
    }

    public void setPositionCenterOfActorToCenterOfViewport(){
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            setPosition(ev.getWorldWidth()/2-getWidth()/2, ev.getWorldHeight()/2-getHeight()/2);
        }
    }
    public void setPositionCenter(float y){
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            setPosition(ev.getWorldWidth()/2-getWidth()/2, y);
        }
    }

    public void setPositionMiddle(float x){
        Stage s;
        Viewport ev;
        if ((s = getStage()) != null) {
            ev = s.getViewport();
            setPosition(x, ev.getWorldHeight()/2-getHeight()/2);
        }
    }

    public void setPositionCenter(){
        setPositionCenter(getY());
    }

    public void setPositionMiddle() {
        setPositionMiddle(getX());
    }

    @Override
    public void setOriginX(float originX) {
        setOrigin(originX, getOriginY());
        originChanged();
    }

    @Override
    public void setOriginY(float originY) {
        setOrigin(getOriginX(),originY);
        originChanged();
    }

    @Override
    public void setOrigin(float originX, float originY) {
        if (shapeMap != null) {
            for (MyShape shape : getCollisionShapeMap().values()) {
                shape.setOrigin(originX, originY);
            }
        }
        super.setOrigin(originX, originY);
        originChanged();
    }

    @Override
    public void setOrigin(int alignment) {
        super.setOrigin(alignment);
        originChanged();
    }

    protected void originChanged(){
        if (actorWorldHelper != null && !actorWorldHelper.isModifyedByWorld()){
            actorWorldHelper.setBodyOrigin(getOriginX(), getOriginY());
        }
    }

    protected void colorChanged(){
        if (actorWorldHelper != null && !actorWorldHelper.isModifyedByWorld()){
            actorWorldHelper.setBodyColor(getColor());
        }
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        colorChanged();
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        super.setColor(r, g, b, a);
        colorChanged();
    }

    public boolean isInFrustum(){
        MyStage m = (MyStage)getStage();
        return m.isActorShowing(this);
    }
    public boolean isInFrustum(float zoom){
        MyStage m = (MyStage)getStage();
        return m.isActorShowing(this, zoom);
    }

    public void setWidthWhithAspectRatio(float width){
        setSize(width, getHeight()*(width/getWidth()));
    }

    public void seHeightWhithAspectRatio(float height){
        setSize(getWidth()*(height/getHeight()), height);
    }

    public void magnify(float mul){
        setSize(getWidth()*mul, getHeight()*mul);
    }

    public void setSizeByOrigin(float width, float height) {
        setPosition(getX() + (getWidth() - width) / (getWidth() / getOriginX()), getY() + (getHeight() - height) / (getHeight() / getOriginY()));
        setSize(width, height);
    }

    public void magnifyByOrigin(float mul){
        setSizeByOrigin(getWidth()*mul, getHeight()*mul);
    }

    public void setWidthWhithAspectRatioByOrigin(float width){
        setSizeByOrigin(width, getHeight()*(width/getWidth()));
    }

    public void seHeightWhithAspectRatioByOrigin(float height){
        setSizeByOrigin(getWidth()*(height/getHeight()), height);
    }

    public void setWidthByOrigin(float width) {
        setSizeByOrigin(width, getHeight());
    }

    public void setHeightByOrigin(float height) {
        setSizeByOrigin(getWidth(), getHeight());
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (getActorWorldHelper() != null){
            if (getStage() == null){
                getActorWorldHelper().removeFromWorld();
            }else{
                getActorWorldHelper().addToWorld();
            }
        }
    }

    @Override
    public boolean remove() {
        if(getActorWorldHelper()!=null){
            getActorWorldHelper().remove();
            return false;
        }
        return super.remove();
    }


    @Override
    public String toString() {
        return super.toString() + " {" +
                " X = " + getX() +
                " Y = " + getX() +
                ", width = " + getWidth() +
                ", height = " + getHeight() +
                ", rotation = " + getRotation() +
                ", originX = " + getOriginX() +
                ", originY = " + getOriginY();
    }

}
