package hu.csanyzeg.master.Demos.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.Demos.Box2dHelper.Box2dHelperScreen;
import hu.csanyzeg.master.Demos.Box2dHelper.BoxActor;
import hu.csanyzeg.master.Demos.Box2dJoin.Box2dJoinScreen;
import hu.csanyzeg.master.Demos.Firework.FireworkScreen;
import hu.csanyzeg.master.Demos.FlappyBird.FlappyScreen;
import hu.csanyzeg.master.Demos.LoadingStage.DemoPreLoadingStage;
import hu.csanyzeg.master.Demos.SimpleClock.SWScreen;
import hu.csanyzeg.master.Demos.SpaceInvaders.SpaceScreen;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

import static hu.csanyzeg.master.Demos.Box2dJoin.ChainLinkActor.linkTexture;
import static hu.csanyzeg.master.Demos.FlappyBird.BirdActor.birdTexture;
import static hu.csanyzeg.master.Demos.Menu.Menu.fireworkTexture;
import static hu.csanyzeg.master.Demos.Menu.Menu.invadersTexture;
import static hu.csanyzeg.master.Demos.Menu.Menu.simpleClockTexture;
import static hu.csanyzeg.master.Demos.Menu.Menu.trebuchet;

public class MenuItem extends MyGroup {
    private MenuItemType type;

    private OneSpriteActor icon;
    private MyLabel text;
    private ClickListener listener;

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(linkTexture);
        assetList.addTexture(BoxActor.boxTexture);
        assetList.addTextureAtlas(birdTexture);
        assetList.addTexture(invadersTexture);
        assetList.addTexture(fireworkTexture);
    }

    public MenuItem(MyGame game, MenuItemType type) {
        super(game);
        this.type = type;
        basicStuff();
        addActors();
    }

    private void basicStuff(){
        listener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                swichScreen();
            }
        };

        if(getHash() != birdTexture) icon = new OneSpriteStaticActor(game,getHash());
        else {
            icon = new OneSpriteAnimatedActor(game, getHash());
            ((OneSpriteAnimatedActor)icon).setFps(12);
        }

        icon.setX(7);
        icon.setSize(32*1.3f,(32/icon.getWidth()*icon.getHeight())*1.3f);
        icon.setY(0);
        setSize(icon.getWidth(),icon.getHeight());

        text = new MyLabel(game, getText(), getLabelStyle()) {
            @Override
            public void init() {
                setColor(Color.BLACK);
                setFontScale(1.5f);
                setPosition(55, icon.getY());
            }
        };

        icon.addListener(listener);
        text.addListener(listener);
    }

    private void swichScreen(){
        switch (type){
            case INVADERS:
                game.setScreenWithPreloadAssets(SpaceScreen.class, new DemoPreLoadingStage(game));
                break;
            case BOX2DJOIN:
                game.setScreenWithPreloadAssets(Box2dJoinScreen.class, new DemoPreLoadingStage(game));
                break;
            case FIREWORK:
                game.setScreenWithPreloadAssets(FireworkScreen.class, new DemoPreLoadingStage(game));
                break;
            case BOX2DHELPER:
                game.setScreenWithPreloadAssets(Box2dHelperScreen.class, new DemoPreLoadingStage(game));
                break;
            case FLAPPY:
                game.setScreenWithPreloadAssets(FlappyScreen.class, new DemoPreLoadingStage(game));
                break;
            case SIMPLEWORLD:
                game.setScreenWithPreloadAssets(SWScreen.class, new DemoPreLoadingStage(game));
                break;
        }
    }

    private void addActors(){
        addActor(text);
        addActor(icon);
    }

    private String getHash(){
        switch (type){
            case INVADERS:
                return invadersTexture;
            case BOX2DJOIN:
                return linkTexture;
            case FIREWORK:
                return fireworkTexture;
            case BOX2DHELPER:
                return "box2dhelper/box.png";
            case FLAPPY:
                return birdTexture;
            case SIMPLEWORLD:
                return simpleClockTexture;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private String getText(){
        switch (type){
            case INVADERS:
                return "Space Invaders";
            case BOX2DJOIN:
                return "B2D Join Demo";
            case FIREWORK:
                return "Firework Demo";
            case BOX2DHELPER:
                return "Box2D Helper";
            case FLAPPY:
                return "Flappy Demo";
            case SIMPLEWORLD:
                return "Simple Clock";
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
    }

    private Label.LabelStyle getLabelStyle()
    {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = game.getMyAssetManager().getFont(trebuchet);
        style.fontColor = Color.WHITE;
        return style;
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        icon.setY(y);
        text.setY(icon.getY()+icon.getHeight()*0.15f);
    }

    @Override
    public float getY() {
        return super.getY();
    }

}
