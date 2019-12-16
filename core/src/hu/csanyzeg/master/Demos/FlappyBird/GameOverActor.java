package hu.csanyzeg.master.Demos.FlappyBird;

import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class GameOverActor extends OneSpriteStaticActor {
    public static final String gameOverTexure = "demoflappy/gameover.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(gameOverTexure);
    }

    private Viewport viewport;

    public GameOverActor(MyGame game, Viewport viewport) {
        super(game, gameOverTexure);
        this.viewport = viewport;
        setPosition(viewport.getWorldWidth()/2-getWidth()/2,-getHeight());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(getY() < viewport.getWorldHeight()*0.7f) setY(getY()+20);
    }
}
