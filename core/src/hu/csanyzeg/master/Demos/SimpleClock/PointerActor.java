package hu.csanyzeg.master.Demos.SimpleClock;

import com.badlogic.gdx.graphics.Color;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;

public class PointerActor extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }


    public PointerActor(final MyGame game, final SimpleWorld world, final float x, final float y, final float w, float h, float rotation) {
        super(game, boxTexture);
        setSize(w, h);
        setRotation(rotation);
        setPosition(x,y);
        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Ghost));

        final SimpleWorldHelper helper = (SimpleWorldHelper) getActorWorldHelper();


        helper.getBodyColor().a = 0f;
        helper.body.colorToFixTime(Color.WHITE, 2f);

    }

}
