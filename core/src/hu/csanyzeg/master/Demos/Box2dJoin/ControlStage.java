package hu.csanyzeg.master.Demos.Box2dJoin;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class ControlStage extends MyStage {
    Box2dJoinStage box2dJoinStage;
    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(MenuButton.class, assetList);
    }

    public ControlStage(MyGame game, final Box2dJoinStage box2dJoinStage) {
        super(new ExtendViewport(720,720), game);
        this.box2dJoinStage = box2dJoinStage;
        setCameraResetToLeftBottomOfScreen();
        addBackButtonScreenBackByStackPopListener();
        addActor(new MenuButton(game, "Weld"){
            @Override
            public void init() {
                super.init();
                setPosition(0,500);
                setSize(100,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        box2dJoinStage.addWeld();
                    }
                });
            }
        });

        addActor(new MenuButton(game, "Revolute"){
            @Override
            public void init() {
                super.init();
                setPosition(0,400);
                setSize(100,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        box2dJoinStage.addRevolute();
                    }
                });
            }
        });
    }
}
