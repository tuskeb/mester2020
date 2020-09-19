package hu.csanyzeg.master.Demos.FlappyBird;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class BirdActor extends OneSpriteAnimatedActor {
    public static final String birdTexture = "demoflappy/bird.atlas";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTextureAtlas(birdTexture);
    }

    private boolean isAct;

    public BirdActor(MyGame game) {
        super(game, birdTexture);
        setAct(true);
        setSize(getWidth()/4,getHeight()/4);
        setTouchable(null);
        setFps(15);
        setSize(getWidth()*5,getHeight()*5);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isAct) {
            setY(getY() - 3);
            if (getRotation() > 0 && getRotation() < 90) setRotation(getRotation() - 5);
        }
        else setFps(0);
    }

    public void setAct(boolean b) {
        this.isAct = b;
    }
}
