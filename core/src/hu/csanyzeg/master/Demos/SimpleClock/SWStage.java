package hu.csanyzeg.master.Demos.SimpleClock;

import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

import hu.csanyzeg.master.Demos.Box2dHelper.BoxActor;
import hu.csanyzeg.master.Demos.Box2dHelper.WallActor;
import hu.csanyzeg.master.Demos.Box2dJoin.ChainLinkActor;
import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
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
        addBackButtonScreenBackByStackPopListener();


        for(int i =0 ; i<60; i ++){
            PointerActor sba;
            addActor(sba = new PointerActor(game, world,getWidth()/2 - (i % 5 == 0 ? 0.5f : 0.25f),getHeight()/2-0.05f,i % 5 == 0 ? 1f : 0.5f,0.1f,0));
            ((SimpleWorldHelper)sba.getActorWorldHelper()).body.rotateToFixTime(i*6,3, Direction.Shorter);
        }
        addActor(new ClockSecPointer(game, world,getWidth()/2 - 0.05f,getHeight()/2-0.05f,0.1f,4f,0));
        addActor(new ClockMinutePointer(game, world,getWidth()/2 - 0.05f,getHeight()/2-0.05f,0.1f,3f,0));
        addActor(new ClockHourPointer(game, world,getWidth()/2 - 0.05f,getHeight()/2-0.05f,0.1f,2f,0));

    }
}
