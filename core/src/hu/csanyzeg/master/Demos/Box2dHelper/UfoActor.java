package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class UfoActor extends OneSpriteStaticActor {
    public static final String ufoTexture = "box2dhelper/ufo.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(ufoTexture);
    }


    public UfoActor(MyGame game, World world, WorldBodyEditorLoader loader, float x, float y, float w, float h) {
        super(game, ufoTexture);
        setSize(w,h);
        setPosition(x,y);
        setActorWorldHelper(new Box2DWorldHelper(world, this, loader, "ufo.png", new MyFixtureDef(), BodyDef.BodyType.DynamicBody));

    }
}
