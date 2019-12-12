package hu.csanyzeg.master.Demos.DemoLoadingStage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.Demos.DemoMenu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.CimerActor;
import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.MyAssetManager;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public class DemoLoadingStage extends LoadingStage {
    public static String fontHash = "loadingscreen/normal.ttf";
    public static String bgHash = "loadingscreen/feher.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addFont(fontHash, fontHash, 20, Color.WHITE, AssetList.CHARS).protect = true;
        assetList.addTexture(bgHash).protect = true;
        MyAssetManager.collectAssetDescriptor(ProgressActor.class, assetList);
        MyAssetManager.collectAssetDescriptor(CimerActor.class, assetList);
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

        filenameLabel = new  MyLabel("Loading...", new Label.LabelStyle(game.getMyAssetManager().getFont(fontHash), Color.valueOf("007515"))){
            @Override
            public void init() {

            }
        };
        cimerActor = new CimerActor(game);
        addActor(cimerActor);
        cimerActor.setSize(256,256);
        cimerActor.setPositionCenterOfActorToCenterOfViewport();


        addActor(filenameLabel);
        filenameLabel.setAlignment(Align.center, Align.center);
        filenameLabel.setSize(1024,50);
        filenameLabel.setPosition(0,cimerActor.getY() - cimerActor.getHeight()/10 * 3.2f);


        progressActor = new ProgressActor(game);
        addActor(progressActor);
        progressActor.setSize(cimerActor.getWidth(),cimerActor.getHeight()/10);
        progressActor.setPosition(cimerActor.getX(),cimerActor.getY() - cimerActor.getHeight()/10f * 1.5f);

    }

    @Override
    public void show() {
        super.show();
        cimerActor.setElapsedTime(0);
        progressActor.reset();
    }

    @Override
    public void init() {
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
