package hu.csanyzeg.master.Demos.Box2dJoin;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.DestructionListener;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

import hu.csanyzeg.master.Demos.Box2dHelper.BoxActor;
import hu.csanyzeg.master.Demos.Box2dHelper.WallActor;
import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2dStage;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyJoint;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class Box2dJoinStage extends Box2dStage {
    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(ChainLinkActor.class, assetList);
        AssetList.collectAssetDescriptor(WallActor.class, assetList);
        AssetList.collectAssetDescriptor(BoxActor.class, assetList);
        AssetList.collectAssetDescriptor(MenuButton.class, assetList);
    }


    java.util.Random random = new Random();

    public void addBox() {
        addActor(new BoxActor(game, world, random.nextFloat() * 14 + 2, 10, random.nextFloat() + 0.5f, random.nextFloat() * 360));
    }

    public void addWeld(){
        addActor(new ChainActorGroup(game, world, loader,1,10, JointDef.JointType.WeldJoint));
    }

    public void addRevolute(){
        addActor(new ChainActorGroup(game, world, loader,1,10, JointDef.JointType.RevoluteJoint));
    }


    public void addDistance(){
        addActor(new ChainActorGroup(game, world, loader,1,10, JointDef.JointType.DistanceJoint));
    }


    public void addMouse(){
        addActor(new ChainActorGroup(game, world, loader,1,10, JointDef.JointType.MouseJoint));
    }

    public void addRope(){
        addActor(new ChainActorGroup(game, world, loader,1,10, JointDef.JointType.RopeJoint));
    }


    public void addPrismatic(){
        addActor(new ChainActorGroup(game, world, loader,1,10, JointDef.JointType.PrismaticJoint));
    }


    public Box2dJoinStage(MyGame game) {
        super(new ExtendViewport(16,9), game);
        setLoader("box2dhelper/teszt.json");
        setCameraResetToLeftBottomOfScreen();
        addActor(new WallActor(game, world,0,0,getWidth(),1,0));
    }
}
