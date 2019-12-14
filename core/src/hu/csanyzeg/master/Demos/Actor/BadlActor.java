package hu.csanyzeg.master.Demos.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class BadlActor extends OneSpriteStaticActor {
    public static String textureHash = "badlogic.jpg";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(textureHash);
    }


    public BadlActor(MyGame game) {
        super(game, textureHash);
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                //event.getStage().addActor(new BadlActor());
            }
        });
        addBaseCollisionCircleShape();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        //setBodyPosition(getX()+1, getY());
    }
}
