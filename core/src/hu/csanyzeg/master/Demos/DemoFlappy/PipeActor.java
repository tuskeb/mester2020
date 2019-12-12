package hu.csanyzeg.master.Demos.DemoFlappy;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class PipeActor extends OneSpriteStaticActor {
    public static final String pipeTexture = "demoflappy/pipe.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(pipeTexture);
    }


    public PipeActor(MyGame game) {
        super(game, pipeTexture);
        setTouchable(null);
        setSize(getWidth()/2.5f,getHeight()/2.5f);
        addBaseCollisionRectangleShape();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        setX(getX()-5);
        if(getX() < -getWidth()) setX(350);
    }
}
