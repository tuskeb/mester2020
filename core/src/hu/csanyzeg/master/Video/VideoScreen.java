package hu.csanyzeg.master.Video;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class VideoScreen extends MyScreen {

    public VideoScreen(MyGame game) {
        super(game);
        r = 0.2f;
        g = 0.2f;
        b = 0.0f;
    }

    @Override
    protected void afterAssetsLoaded() {
        addStage(new VideoStage(game), 0, true);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        AssetList.collectAssetDescriptor(VideoStage.class, assetList);
        return assetList;
    }

    @Override
    public void init() {

    }
}
