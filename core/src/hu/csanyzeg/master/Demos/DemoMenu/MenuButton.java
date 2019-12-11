package hu.csanyzeg.master.Demos.DemoMenu;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.UI.MyButton;

public class MenuButton extends MyButton {
    public static String fontHash = "alegreyaregular.otf";
    public static String upHash = "blue.png";
    public static String downHash = "green.png";
    public static String overHash = "yellow.png";

    @Override
    public void init() {

    }

    public MenuButton(MyGame game, String text) {
        super(game, text, new TextButtonStyle(new TextureRegionDrawable(new TextureRegion(game.getMyAssetManager().getTexture(upHash))),
                new TextureRegionDrawable(new TextureRegion(game.getMyAssetManager().getTexture(downHash))),
                new TextureRegionDrawable(new TextureRegion(game.getMyAssetManager().getTexture(overHash))),
                game.getMyAssetManager().getFont(fontHash)));
    }
}
