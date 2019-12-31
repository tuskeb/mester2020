package hu.csanyzeg.master.Demos.SimpleWorld;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

import hu.csanyzeg.master.Demos.Box2dHelper.BoxActor;
import hu.csanyzeg.master.Demos.Box2dHelper.WallActor;
import hu.csanyzeg.master.Demos.Box2dJoin.ChainActorGroup;
import hu.csanyzeg.master.Demos.Box2dJoin.ChainLinkActor;
import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2dStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldDebugRenderer;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;

public class SWStage extends SimpleWorldStage {
    public static AssetList assetList = new AssetList();


    static {
        AssetList.collectAssetDescriptor(ChainLinkActor.class, assetList);
        AssetList.collectAssetDescriptor(WallActor.class, assetList);
        AssetList.collectAssetDescriptor(BoxActor.class, assetList);
        AssetList.collectAssetDescriptor(MenuButton.class, assetList);
    }


    Random random = new Random();



    public SWStage(MyGame game) {
        super(new ExtendViewport(16,9), game);
        setCameraResetToLeftBottomOfScreen();
        addActor(new SimpleBoxActor(game, world,5,3,1,3,0));
        addActor(new SimpleBoxActor(game, world,0,0,1,3,90));
        addActor(new SimpleBoxActor(game, world,0,7,1,3,180));
        addActor(new SimpleBoxActor(game, world,7,2,1,3,270));
        addBackButtonScreenBackByStackPopListener();
    }
}
