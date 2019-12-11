package hu.csanyzeg.master.Demos.DemoLoadingStage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyLabel;

public class DemoLoadingStage extends LoadingStage {
    public static String fontHash30 = "loadingscreen/consola.ttf30";
    public static String fontHash100 = "loadingscreen/consola.ttf100";
    private MyLabel filenameLabel;
    private MyLabel percentLabel;

    public DemoLoadingStage(MyGame game) {
        super(new ExtendViewport(1280, 640), game);
        filenameLabel = new  MyLabel("Loading...", new Label.LabelStyle(assetManager.getFont(fontHash30), Color.GREEN)){
            @Override
            public void init() {

            }
        };
        percentLabel = new  MyLabel("0%", new Label.LabelStyle(assetManager.getFont(fontHash100), Color.GREEN)){
            @Override
            public void init() {

            }
        };
        addActor(filenameLabel);
        filenameLabel.setAlignment(Align.center, Align.center);
        filenameLabel.setSize(1024,50);
        filenameLabel.setPosition(128,230);

        addActor(percentLabel);
        percentLabel.setAlignment(Align.center, Align.center);
        percentLabel.setSize(1024,150);
        percentLabel.setPosition(128,300);
    }

    @Override
    public void init() {
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        assetList.addFont("loadingscreen/normal.ttf", fontHash30, 30, Color.WHITE, AssetList.CHARS);
        assetList.addFont("loadingscreen/italic.ttf", fontHash100, 200, Color.WHITE, AssetList.NUMBERS + AssetList.SIGNS);

        return assetList;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        filenameLabel.setText(getActualLoadingName());
        percentLabel.setText(getPercent() + "%");
    }
}
