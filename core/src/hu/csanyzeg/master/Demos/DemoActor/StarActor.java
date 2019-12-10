package hu.csanyzeg.master.Demos.DemoActor;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class StarActor extends OneSpriteAnimatedActor {
    Sound sound;

    public StarActor(MyGame game) {
        super(game, "STAR_TEXTUREATLAS");
        sound = game.getMyAssetManager().getSound("STAR_SOUND");
        setFps(8);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                PlaySound();
            }
        });
        //setOrigin(128,128);
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
