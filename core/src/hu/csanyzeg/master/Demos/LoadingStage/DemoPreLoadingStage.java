package hu.csanyzeg.master.Demos.LoadingStage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.util.ArrayList;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.OriginRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public class DemoPreLoadingStage extends LoadingStage {
    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(WhiteCircleActor.class, assetList);
    }


    private MyLabel filenameLabel;
    private ProgressActor progressActor;
    private CimerActor cimerActor;
    private OneSpriteStaticActor bgActor;
    protected boolean first = true;


    public DemoPreLoadingStage(MyGame game) {
        super(new ExtendViewport(1024, 720, new OrthographicCamera()), game);
        final float cx = getViewport().getWorldWidth() / 2f - 386;
        final float cy = getViewport().getWorldHeight() / 2f;
        setCameraResetToLeftBottomOfScreen();


        addTimer(new IntervalTimer(0,1,new IntervalTimerListener(){
            @Override
            public void onStart(IntervalTimer sender) {
                super.onStart(sender);

                for (int i = 0; i < 20; i++) {
                    final SimpleWorldHelper finalSwh;
                    final MyActor m = new WhiteCircleActor(DemoPreLoadingStage.this.game);
                    m.setRotation(0);
                    m.setActorWorldHelper(finalSwh = new SimpleWorldHelper(world, m, ShapeType.Null, SimpleBodyType.Ghost));
                    addActor(m);
                    m.setPositionCenterOfActorToCenterOfViewport();
                    final int finalI = i;
                    finalSwh.body.setColor(new Color(1,1,1,0.3f));
                    if (first) {
                        finalSwh.body.originToFixTime(finalSwh.body.getLeftBottomOriginX() - 256, finalSwh.body.getLeftBottomOriginY(), 1f, OriginRule.FixOrigin);
                    }else{
                        finalSwh.body.setOriginFixedOrigin(finalSwh.body.getLeftBottomOriginX() - 256, finalSwh.body.getLeftBottomOriginY());
                    }
                    finalSwh.body.rotateToFixTime(-355, 0.7f + 0.1f * finalI, Direction.ClockWise);
                    finalSwh.body.colorToFixTime(new Color(1,1,1,1), 0.5f + 0.2f * finalI);
                    m.addTimer(new IntervalTimer(0.5f + 0.08f * finalI, new IntervalTimerListener() {
                        @Override
                        public void onStop(IntervalTimer sender) {
                            super.onStop(sender);
                            finalSwh.body.colorToFixTime(new Color(1,1,1,0), 0.2f);
                        }
                    }));

                    finalSwh.body.setSimpleBodyBehaviorListener(new SimpleBodyBehaviorListener(){
                        @Override
                        public void onStop(SimpleBody sender) {
                            super.onStop(sender);
                            m.remove();
                        }
                    });
                }
                first = false;
            }

            @Override
            public void onStop(IntervalTimer sender) {
                super.onStop(sender);
                sender.start();
            }
        }));


    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public AssetList getAssetList() {
        return assetList;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
}
