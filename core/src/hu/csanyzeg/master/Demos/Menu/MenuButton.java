package hu.csanyzeg.master.Demos.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;

public class MenuButton extends MyButton {
    public static String fontHash = "alegreyaregular.otf";
    public static String upHash = "blue.png";
    public static String downHash = "green.png";
    public static String overHash = "yellow.png";

    public static final AssetList assetList = new AssetList();

    static {
        assetList.addFont(MenuButton.fontHash, null, 20, Color.WHITE).protect = true;
        assetList.addTexture(MenuButton.downHash, null).protect = true;
        assetList.addTexture(MenuButton.upHash, null).protect = true;
        assetList.addTexture(MenuButton.overHash, null).protect = true;
    }

    @Override
    public void init() {

    }

    public MenuButton(MyGame game, String text) {
        super(game, text, new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(game.getMyAssetManager().getTexture(upHash))),
                new TextureRegionDrawable(new TextureRegion(game.getMyAssetManager().getTexture(downHash))),
                new TextureRegionDrawable(new TextureRegion(game.getMyAssetManager().getTexture(upHash))),
                game.getMyAssetManager().getFont(fontHash)));
    }
}
