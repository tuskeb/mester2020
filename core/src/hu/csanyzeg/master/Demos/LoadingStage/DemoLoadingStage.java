package hu.csanyzeg.master.Demos.LoadingStage;

import com.badlogic.gdx.graphics.Color;
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
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public class DemoLoadingStage extends LoadingStage {
    public static String fontHash = "loadingscreen/normal.ttf";
    public static String bgHash = "loadingscreen/feher.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addFont(fontHash, fontHash, 20, Color.WHITE, AssetList.CHARS).protect = true;
        assetList.addTexture(bgHash).protect = true;
        AssetList.collectAssetDescriptor(ProgressActor.class, assetList);
        AssetList.collectAssetDescriptor(CimerActor.class, assetList);
        AssetList.collectAssetDescriptor(CircleActor.class, assetList);
    }


    private MyLabel filenameLabel;
    private ProgressActor progressActor;
    private CimerActor cimerActor;
    private OneSpriteStaticActor bgActor;

    public DemoLoadingStage(MyGame game) {
        super(new ExtendViewport(1024, 720), game);
        bgActor = new OneSpriteStaticActor(game, bgHash);
        addActor(bgActor);
        setCameraResetToLeftBottomOfScreen();
        bgActor.fitToViewportRealWorldSizeWithoutBlackBars();

        filenameLabel = new  MyLabel(game, "Loading...", new Label.LabelStyle(game.getMyAssetManager().getFont(fontHash), Color.valueOf("007515"))){
            @Override
            public void init() {

            }
        };

        ArrayList<CimerActor> cimerActors = new ArrayList<>();
        final float cx;
        final float cy;
        for(int i = 0; i<16; i++) {
            cimerActor = new CimerActor(game);
            cimerActor.setSize(256, 256);
            cimerActor.setActorWorldHelper(new SimpleWorldHelper(world, cimerActor, ShapeType.Null, SimpleBodyType.Ghost));
            addActor(cimerActor);
            cimerActor.setPositionCenterOfActorToCenterOfViewport();

            cimerActors.add(cimerActor);
        }
        cx = cimerActor.getX() - 128;
        cy = cimerActor.getY() + 96;


        float k = 0;
        for(CimerActor c : cimerActors){

            ((SimpleWorldHelper)c.getActorWorldHelper()).body.setSizeByCenter(200,200);
            ((SimpleWorldHelper)c.getActorWorldHelper()).body.setColor(new Color(1,1,1,k*k*k / 3f));
            ((SimpleWorldHelper)c.getActorWorldHelper()).body.sizeToFixTime(256, 256,6 - k*3, PositionRule.Center);
             k += 1f / cimerActors.size();
        }

        ((SimpleWorldHelper)cimerActors.get(cimerActors.size()-1).getActorWorldHelper()).body.setColor(new Color(1,1,1,1));

        addActor(filenameLabel);
        filenameLabel.setAlignment(Align.center, Align.center);
        filenameLabel.setSize(getWidth(),50);
        filenameLabel.setPosition(0,cimerActor.getY() - cimerActor.getHeight()/10 * 3.2f);


        progressActor = new ProgressActor(game);
        addActor(progressActor);
        progressActor.setSize(cimerActor.getWidth(),cimerActor.getHeight()/10);
        progressActor.setPosition(cimerActor.getX(),cimerActor.getY() - cimerActor.getHeight()/10f * 1.5f);




        addTimer(new IntervalTimer(0,5,new IntervalTimerListener(){
            @Override
            public void onStart(IntervalTimer sender) {
                super.onStart(sender);

                for (int i = 0; i < 20; i++) {
                    final SimpleWorldHelper finalSwh;
                    final MyActor m = new CircleActor(DemoLoadingStage.this.game);
                    m.setOrigin(256,5);
                    m.setPosition(cx,cy);
                    m.setRotation(180);

                    m.setActorWorldHelper(finalSwh = new SimpleWorldHelper(world, m, ShapeType.Null, SimpleBodyType.Ghost));
                    addActor(m);
                    final int finalI = i;
                    finalSwh.body.setColor(new Color(1,1,1,0));
                    finalSwh.body.rotateToFixTime(-355, 2 + 0.2f * finalI, Direction.ClockWise);
                    finalSwh.body.colorToFixTime(new Color(1,1,1,1), 1f + 0.2f * finalI);
                    m.addTimer(new IntervalTimer(1f + 0.18f * finalI, new IntervalTimerListener() {
                        @Override
                        public void onStop(IntervalTimer sender) {
                            super.onStop(sender);
                            finalSwh.body.colorToFixTime(new Color(1,1,1,0), 1);
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
        progressActor.reset();
    }

    @Override
    public AssetList getAssetList() {
        return assetList;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        filenameLabel.setText(getActualLoadingName());
        progressActor.setPercent(getPercent());
    }
}
