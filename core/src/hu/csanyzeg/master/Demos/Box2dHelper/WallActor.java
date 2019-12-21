package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.ShapeType;

public class WallActor extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/wall.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }

    public WallActor(MyGame game, World world, float x, float y, float w, float h, float rotation) {
        super(game, boxTexture);
        setSize(w,h);
        setPosition(x,y);
        setRotation(rotation);
        setActorWorldHelper(new Box2DWorldHelper(world, this, ShapeType.Rectangle, new MyFixtureDef(), BodyDef.BodyType.StaticBody));
    }
}
