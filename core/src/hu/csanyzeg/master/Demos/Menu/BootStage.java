package hu.csanyzeg.master.Demos.Menu;

import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.Menu;

import hu.csanyzeg.master.Demos.Firework.FireworkActor;
import hu.csanyzeg.master.Demos.LoadingStage.DemoLoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

import static hu.csanyzeg.master.Demos.Menu.MenuStage.shutdownWallpaperTexture;

public class BootStage extends LoadingStage {
    public static String welcomeTexture = "demomenu/welcome.png";
    public static String welcomeSound = "demomenu/welcome.mp3";
    public static boolean firstBoot = true;

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(shutdownWallpaperTexture);
        assetList.addTexture(welcomeTexture);
        assetList.addSound(welcomeSound);
    }

    OneSpriteStaticActor welcome;

    public BootStage(final MyGame game) {
        super(new ResponseViewport(720), game);
        if(firstBoot) {
            addActor(new OneSpriteStaticActor(game, shutdownWallpaperTexture) {
                @Override
                public void init() {
                    super.init();
                    setSize(getViewport().getWorldWidth(), getViewport().getWorldHeight());
                }
            });

            welcome = new OneSpriteStaticActor(game, welcomeTexture) {
                @Override
                public void init() {
                    super.init();
                    setAlpha(0);
                    setPosition(getViewport().getWorldWidth() / 2 - getWidth() / 2, getViewport().getWorldHeight() / 2 - getHeight() / 2);
                }
            };

            addActor(welcome);

            game.getMyAssetManager().getSound(welcomeSound).play();

            addTimer(new TickTimer(1.75f, false, new TickTimerListener() {

                @Override
                public void onTick(Timer sender, float correction) {
                    getScreen().addStage(new MenuStage(game), 1, true);
                    getScreen().removeStage(BootStage.this);
                }

            }));
        }
        else
        {
            getScreen().addStage(new MenuStage(game), 1, true);
            getScreen().removeStage(BootStage.this);
        }
    }

    @Override
    public AssetList getAssetList() {
        return assetList;
    }

    float alpha = 0;

    @Override
    public void act(float delta) {
        super.act(delta);
        if(alpha < 0.98) alpha += 0.02;
        else alpha = 1;
        welcome.setAlpha(alpha);
    }
}
