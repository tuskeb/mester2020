package hu.csanyzeg.master.MyBaseClasses.SimpleWorld;

import com.badlogic.gdx.utils.Array;

//TODO: dinamic test típus implementálása


public class SimpleWorld {
    protected float elapsedTime=0;

    protected final Array<SimpleBody> bodies = new Array<>();

    private boolean locked = false;

    public boolean isLocked() {
        return locked;
    }

    protected SimpleWorldContactListener contactListener = new SimpleWorldContactListener();

    public SimpleWorldContactListener getContactListener() {
        return contactListener;
    }

    public void setContactListener(SimpleWorldContactListener contactListener) {
        this.contactListener = contactListener;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    //optimalizáció miatt
    protected final Array<SimpleBody> collisionBodies = new Array<SimpleBody>();
    protected final Array<SimpleBody> moveBodies = new Array<>();



    public void step(float deltaTime, int moveIterations, int positionCorrectionIterations){
        elapsedTime+=deltaTime;
        float stepTime = deltaTime / moveIterations;
        for(int i = 0; i<moveIterations; i ++){
            contactListener.beforeIteration(this, stepTime);
            locked = true;
            for(SimpleBody body : moveBodies){
                body.step(stepTime);
            }

            SimpleBody bodyA = null;
            SimpleBody bodyB = null;
            for(int x = 0; x < collisionBodies.size; x++)
            {
                for(int y = x+1; y < collisionBodies.size; y++)
                {
                    bodyA = collisionBodies.get(x);
                    bodyB = collisionBodies.get(y);
                    if (bodyA.overlaps(bodyB)){
                        if (!bodyA.connectedBodies.contains(bodyB,true)) {
                            bodyA.connectedBodies.add(bodyB);
                            bodyB.connectedBodies.add(bodyA);
                            bodyA.simpleBodyBehaviorListener.onContactAdded(bodyA, bodyB);
                            bodyB.simpleBodyBehaviorListener.onContactAdded(bodyB, bodyA);
                            contactListener.beginContact(this, new SimpleContact(bodyA, bodyB));
                        }
                    }else{
                        if (bodyA.connectedBodies.contains(bodyB,true)) {
                            bodyA.connectedBodies.removeValue(bodyB,true);
                            bodyB.connectedBodies.removeValue(bodyA, true);
                            bodyA.simpleBodyBehaviorListener.onContactRemoved(bodyA, bodyB);
                            bodyB.simpleBodyBehaviorListener.onContactRemoved(bodyB, bodyA);
                            contactListener.endContact(this, new SimpleContact(bodyA, bodyB));
                        }
                    }
                }
            }
            locked = false;
            contactListener.afterIteration(this, stepTime);
        }
    }

    protected void setBodyType(SimpleBody body, SimpleBodyType bodyType){
        if (locked){
            throw new UnsupportedOperationException("A test típusa nem változtatható meg a world.step futása közben.");
        }
        if (!bodies.contains(body, true)) return;
        body.bodyType = bodyType;
        switch (bodyType){
            case Dinamic:
            case Sensor:
                collisionBodies.add(body);
                moveBodies.add(body);
                break;
            case Ghost:
                moveBodies.add(body);
                collisionBodies.removeValue(body, true);
                break;
            case Static:
                collisionBodies.add(body);
                moveBodies.removeValue(body, true);
                break;
            case Passive:
                collisionBodies.removeValue(body, true);
                moveBodies.removeValue(body, true);
                break;
        }
    }

    public void addBody(SimpleBody body){
        bodies.add(body);
        setBodyType(body, body.bodyType);
    }

    public boolean removeBody(SimpleBody body) throws UnsupportedOperationException {
        if (locked){
            throw new UnsupportedOperationException("A test nem távolítható el a world.step futása közben.");
        }
        collisionBodies.removeValue(body,true);
        moveBodies.removeValue(body, true);
        return bodies.removeValue(body,true);
    }

    public void clearBodies() throws UnsupportedOperationException {
        if (locked){
            throw new UnsupportedOperationException("A test nem távolítható el a world.step futása közben.");
        }
        collisionBodies.clear();
        moveBodies.clear();
        bodies.clear();
    }
}
