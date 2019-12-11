package hu.csanyzeg.master.Demos.DemoActor;

import com.badlogic.gdx.graphics.Color;

import hu.csanyzeg.master.Demos.DemoMenu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class ActorScreen extends MyScreen {
    public ActorScreen(MyGame game) {
        super(game);
        addStage(new ActorStage(game), 1, true);
    }

    @Override
    public AssetList getAssetList() {
        AssetList assetList = new AssetList();
        assetList.addTexture(BadlActor.textureHash);
        assetList.addTexture(CrossActor.textureHash);
        assetList.addTextureAtlas(ExplosionActor.textureHash);
        assetList.addTextureAtlas(StarActor.textureAtlasHash);
        assetList.addSound(StarActor.soundHash);

        assetList.addFont(MenuButton.fontHash, null, 20, Color.WHITE);
        assetList.addTexture(MenuButton.downHash, null);
        assetList.addTexture(MenuButton.upHash, null);
        assetList.addTexture(MenuButton.overHash, null);
        return assetList;
    }

    @Override
    public void init() {

    }
}
