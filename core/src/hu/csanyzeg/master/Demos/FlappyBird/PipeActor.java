package hu.csanyzeg.master.Demos.FlappyBird;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class PipeActor extends OneSpriteStaticActor {
    public static final String pipeTexture = "demoflappy/pipe.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(pipeTexture);
    }

    private boolean isAct;

    public PipeActor(MyGame game) {
        super(game, pipeTexture);
        setTouchable(null);
        setAct(true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(isAct) {
            setX(getX() - 5);
            if (getX() < -getWidth() - 5) setX(350);
        }
    }

    public void setAct(boolean b) {
        this.isAct = b;
    }
}
