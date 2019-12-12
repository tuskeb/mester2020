package hu.csanyzeg.master.Demos.DemoActor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.Demos.DemoMenu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.MyAssetManager;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class ActorStage extends MyStage {

    public static AssetList assetList = new AssetList();
    static {
        MyAssetManager.collectAssetDescriptor(MenuButton.class, assetList);
        MyAssetManager.collectAssetDescriptor(StarActor.class, assetList);
        MyAssetManager.collectAssetDescriptor(BadlActor.class, assetList);
        MyAssetManager.collectAssetDescriptor(CrossActor.class, assetList);
        MyAssetManager.collectAssetDescriptor(ExplosionActor.class, assetList);
    }



    public ActorStage(final MyGame game) {
        super(new ExtendViewport(720,720), game);
        addBackButtonScreenBackByStackPopListener();

        addActor(new MenuButton(game, "Exit"){
            @Override
            public void init() {
                super.init();
                setPosition(0,0);
                setSize(200,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreenBackByStackPop();
                    }
                });
            }
        });
    }

    @Override
    public void init() {

    }

}
