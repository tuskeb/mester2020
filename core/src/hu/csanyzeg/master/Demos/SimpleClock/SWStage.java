package hu.csanyzeg.master.Demos.SimpleClock;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.Random;

import hu.csanyzeg.master.Demos.Box2dHelper.BoxActor;
import hu.csanyzeg.master.Demos.Box2dHelper.WallActor;
import hu.csanyzeg.master.Demos.Box2dJoin.ChainLinkActor;
import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.OriginRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
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
            final PointerActor sba;
            addActor(sba = new PointerActor(game, world,getWidth()/2 - (i % 5 == 0 ? 0.5f : 0.25f),getHeight()/2-0.05f,i % 5 == 0 ? 1f : 0.5f,0.1f,0));
            final SimpleBody body;
            body = ((SimpleWorldHelper)sba.getActorWorldHelper()).body;
            body.rotateToFixTime(i*6,4, Direction.Shorter);
            body.originToFixTime(body.getLeftBottomOriginX() - 4, body.getLeftBottomOriginY(), 1, OriginRule.FixOrigin);
            body.setSimpleBodyBehaviorListener(new SimpleBodyBehaviorListener(){
                @Override
                public void onStop(SimpleBody sender) {
                    super.onStop(sender);
                    ((SimpleWorldHelper)sba.getActorWorldHelper()).setBodyType(SimpleBodyType.Sensor);
                }
            });
        }


        addListener(new ClickListener(){
            public boolean circle = false;
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (circle) {
                    for (Actor a : getActors()) {
                        if (a instanceof PointerActor) {
                            SimpleBody body = getHelper(a).body;
                            body.originTo(1, -4, -4, OriginRule.Normal);
                            body.moveTo(1, 4,4);
                        }
                    }
                }else{
                    for (Actor a : getActors()) {
                        if (a instanceof PointerActor) {
                            SimpleBody body = getHelper(a).body;
                            body.originTo(1, 4, 4, OriginRule.Normal);
                            body.moveTo(1, -4, -4);
                        }
                    }
                }
                circle = !circle;

                return super.touchDown(event, x, y, pointer, button);
            }
        });



        addActor(new ClockSecPointer(game, world,getWidth()/2 - 0.05f,getHeight()/2-0.05f,0.1f,4f,0));
        addActor(new ClockMinutePointer(game, world,getWidth()/2 - 0.05f,getHeight()/2-0.05f,0.1f,3f,0));
        addActor(new ClockHourPointer(game, world,getWidth()/2 - 0.05f,getHeight()/2-0.05f,0.1f,2f,0));
    }
}
