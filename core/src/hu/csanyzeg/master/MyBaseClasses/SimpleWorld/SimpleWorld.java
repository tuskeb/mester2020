package hu.csanyzeg.master.MyBaseClasses.SimpleWorld;

import com.badlogic.gdx.utils.Array;

//TODO: méret és origin átvétele a világból az actorba, dinamic test típus implementálása, remove tesztelése, fejlesztése, méretezésnél az origin nem jó helyre kerül, gomb a menübe a demóhoz


public class SimpleWorld {
    protected float elapsedTime=0;

    protected final Array<SimpleBody> bodies = new Array<>();


    protected SimpleWorldContactListener contactListener = new SimpleWorldContactListener() {
        @Override
        public void beginContact(SimpleContact contact) {
        }

        @Override
        public void endContact(SimpleContact contact) {
        }
    };

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
    protected final Array<SimpleBody> collisionBodiesA = new Array<SimpleBody>();
    protected final Array<SimpleBody> collisionBodiesB = new Array<SimpleBody>();
    protected final Array<SimpleBody> moveBodies = new Array<>();



    public void step(float deltaTime, int moveIterations, int positionCorrectionIterations){
        elapsedTime+=deltaTime;
        float stepTime = deltaTime / moveIterations;
        for(int i = 0; i<moveIterations; i ++){
            for(SimpleBody body : moveBodies){
                body.step(stepTime);
            }

            SimpleBody bodyA = null;
            SimpleBody bodyB = null;
            for(int x = 0; x < collisionBodiesA.size; x++)
            {
                for(int y = x+1; y < collisionBodiesA.size; y++)
                {
                    bodyA = collisionBodiesA.get(x);
                    bodyB = collisionBodiesA.get(y);
                    if (bodyA.overlaps(bodyB)){
                        if (!bodyA.connectedBodies.contains(bodyB,true)) {
                            bodyA.connectedBodies.add(bodyB);
                            bodyB.connectedBodies.add(bodyA);
                            contactListener.beginContact(new SimpleContact(bodyA, bodyB));
                        }
                    }else{
                        if (bodyA.connectedBodies.contains(bodyB,true)) {
                            bodyA.connectedBodies.removeValue(bodyB,true);
                            bodyB.connectedBodies.removeValue(bodyA, true);
                            contactListener.endContact(new SimpleContact(bodyA, bodyB));
                        }
                    }
                }
            }
        }
    }

    protected void setBodyType(SimpleBody body, SimpleBodyType bodyType){
        if (!bodies.contains(body, true)) return;
        body.bodyType = bodyType;
        switch (bodyType){
            case Dinamic:
            case Sensor:
                collisionBodiesA.add(body);
                collisionBodiesB.add(body);
                moveBodies.add(body);
                break;
            case Ghost:
                moveBodies.add(body);
                break;
            case Static:
                collisionBodiesA.add(body);
                collisionBodiesB.add(body);

                break;
        }
    }

    public void addBody(SimpleBody body){
        bodies.add(body);
        setBodyType(body, body.bodyType);
    }

    public boolean removeBody(SimpleBody body){
        collisionBodiesA.removeValue(body,true);
        collisionBodiesB.removeValue(body,true);
        moveBodies.removeValue(body, true);
        return bodies.removeValue(body,true);
    }

    public void clearBodies(){
        collisionBodiesA.clear();
        collisionBodiesB.clear();
        moveBodies.clear();
        bodies.clear();
    }
}
