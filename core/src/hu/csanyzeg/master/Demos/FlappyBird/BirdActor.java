package hu.csanyzeg.master.Demos.FlappyBird;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class BirdActor extends OneSpriteStaticActor {
    public static final String birdTexture = "demoflappy/bird.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(birdTexture);
    }

    private boolean isAct;

    public BirdActor(MyGame game) {
        super(game, birdTexture);
        setAct(true);
        setSize(getWidth()/4,getHeight()/4);
        setTouchable(null);
        addBaseCollisionCircleShape();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isAct) {
            setY(getY() - 3);
            if (getRotation() > 0 && getRotation() < 90) setRotation(getRotation() - 5);
        }
    }

    public void setAct(boolean b) {
        this.isAct = b;
    }
}
