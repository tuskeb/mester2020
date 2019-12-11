package hu.csanyzeg.master.Demos.DemoFlappy;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class BirdActor extends OneSpriteStaticActor {
    public static final String birdTexture = "demoflappy/bird.png";

    public BirdActor(MyGame game) {
        super(game, birdTexture);
        setSize(getWidth()/5,getHeight()/5);
        setTouchable(null);
        addBaseCollisionCircleShape();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setY(getY()-3);
        if(getRotation() > 0) setRotation(getRotation()-7);
    }
}
