package hu.csanyzeg.master.Demos.Box2dJoin;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WeldJoint;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class ChainLinkActor extends OneSpriteStaticActor {
    public static final String linkTexture = "box2dhelper/link.png";
    public Joint joint1 = null;
    public Joint joint2 = null;

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(linkTexture);
    }
    public ChainLinkActor(MyGame game, World world, WorldBodyEditorLoader loader, float x, float y, float scale, float rotation) {
        super(game, linkTexture);
        setSize(1.28f,0.57f);
        setPosition(x,y);
        setRotation(rotation);
        setActorWorldHelper(new Box2DWorldHelper(world, this, loader, "link.png", new MyFixtureDef(), BodyDef.BodyType.DynamicBody));
    }
}
