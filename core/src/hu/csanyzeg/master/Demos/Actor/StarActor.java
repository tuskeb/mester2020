package hu.csanyzeg.master.Demos.Actor;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class StarActor extends OneSpriteAnimatedActor {
    Sound sound;
    /*------------------AssetList------------*/


    public static final AssetList assetList = new AssetList();

    public static final String soundHash = "star.wav";
    public static final String textureAtlasHash = "star.atlas";

    static {
        assetList.addTextureAtlas(textureAtlasHash);
        assetList.addSound(soundHash);
    }

    /*------------------AssetList------------*/






    public StarActor(MyGame game) {
        super(game, textureAtlasHash);
        sound = game.getMyAssetManager().getSound(soundHash);
        setFps(8);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                PlaySound();
            }
        });
    }

    public void PlaySound()
    {
        sound.play();
        getStage().getActors().removeValue(this, true);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        rotateBy(0.1f);
    }
}
