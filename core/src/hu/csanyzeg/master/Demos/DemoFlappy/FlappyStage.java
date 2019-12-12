package hu.csanyzeg.master.Demos.DemoFlappy;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import hu.csanyzeg.master.Demos.DemoActor.StarActor;
import hu.csanyzeg.master.Demos.DemoMenu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.MyAssetManager;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

import static hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor.overlaps;

public class FlappyStage extends MyStage {


    public static AssetList assetList = new AssetList();
    static {
        MyAssetManager.collectAssetDescriptor(CityActor.class, assetList);
        MyAssetManager.collectAssetDescriptor(MenuButton.class, assetList);
        MyAssetManager.collectAssetDescriptor(BirdActor.class, assetList);
        MyAssetManager.collectAssetDescriptor(PipeActor.class, assetList);
    }

    private BirdActor birdActor;
    private CityActor cityActor;
    private CityActor cityActor2;
    private PipeActor felcso;
    private PipeActor lecso;
    private MenuButton menuButton;

    public FlappyStage(final MyGame game) {
        super(new FitViewport(320,490), game);
        birdActor = new BirdActor(game);
        cityActor = new CityActor(game);
        cityActor2 = new CityActor(game);
        felcso = new PipeActor(game);
        lecso = new PipeActor(game);
        felcso = new PipeActor(game);
        lecso = new PipeActor(game);
        menuButton = new MenuButton(game,"Vissza a menübe");
        menuButton.setPosition(getViewport().getWorldWidth()/2-menuButton.getWidth()/2,getViewport().getWorldHeight()/2-menuButton.getHeight()/2);
        felcso.setX(600);
        lecso.setX(600);
        lecso.setY((float) (Math.random() * 100 + 300));
        felcso.setY(lecso.getY()-felcso.getHeight()-180);
        felcso.setRotation(180);
        birdActor.setPosition(30,getViewport().getWorldHeight()/2-birdActor.getWidth()/2);
        cityActor2.setX(cityActor.getX()+cityActor.getWidth());
        addActor(cityActor);
        addActor(cityActor2);
        addActor(felcso);
        addActor(lecso);
        addActor(birdActor);
        cityActor.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                birdActor.setY(birdActor.getY()+50);
                birdActor.setRotation(25);
            }
        });
        cityActor2.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                birdActor.setY(birdActor.getY()+50);
                birdActor.setRotation(25);
            }
        });
        menuButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
    }

    @Override
    public void init() {

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(overlaps(birdActor,lecso) || overlaps(birdActor,felcso))
        {
            cityActor.setTouchable(null);
            cityActor2.setTouchable(null);
            addActor(menuButton);
        }
        else {
            if (felcso.getX() < -felcso.getWidth()) {
                lecso.setY((float) (Math.random() * 100 + 300));
                felcso.setY(lecso.getY() - felcso.getHeight() - 100);
            }
        }
    }
}
