package hu.csanyzeg.master.Demos.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class ExplosionActor extends OneSpriteAnimatedActor {
    public static String textureHash = "explosion.atlas";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTextureAtlas(textureHash);
    }


    public ExplosionActor(MyGame game) {
        super(game, textureHash);
        setFps(30);
        addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                running = !running;
            }
        });
    }

}
