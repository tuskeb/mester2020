package hu.csanyzeg.master.Demos.FlappyBird;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class CityActor extends MyGroup {
    public static final String hatterTextureEjjel = "demoflappy/background-night.png";
    public static final String hatterTextureNappal = "demoflappy/background-day.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(hatterTextureEjjel);
        assetList.addTexture(hatterTextureNappal);
    }

    private OneSpriteStaticActor ejjel;
    private OneSpriteStaticActor nappal;

    public CityActor(MyGame game, Viewport viewport) {
        super(game);
        alpha = 0.9f;
        alphaIncrement = false;
        ejjel = new OneSpriteStaticActor(game,hatterTextureEjjel);
        nappal = new OneSpriteStaticActor(game,hatterTextureNappal);
        ejjel.setSize(viewport.getWorldWidth(),viewport.getWorldHeight());
        nappal.setSize(viewport.getWorldWidth(),viewport.getWorldHeight());
        addActor(ejjel);
        addActor(nappal);
    }

    private float alpha;
    private boolean alphaIncrement;

    @Override
    public void act(float delta) {
        super.act(delta);
        nappal.setAlpha(alpha);
        if(!alphaIncrement) alpha -= 0.00075;
        else alpha += 0.00075;
        if(alpha > 0.99 || alpha < 0.01) alphaIncrement = !alphaIncrement;
    }
}
