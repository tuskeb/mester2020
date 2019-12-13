package hu.csanyzeg.master.Demos.Actor;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class ActorStage extends MyStage {

    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(MenuButton.class, assetList);
        AssetList.collectAssetDescriptor(StarActor.class, assetList);
        AssetList.collectAssetDescriptor(BadlActor.class, assetList);
        AssetList.collectAssetDescriptor(CrossActor.class, assetList);
        AssetList.collectAssetDescriptor(ExplosionActor.class, assetList);
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
