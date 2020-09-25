package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;

public class StarShipActor extends OneSpriteStaticActor {
    public static String asset =  "spaceinvaders/starship.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(asset);
    }


    public StarShipActor(MyGame game, SimpleWorld world, float x, float y) {
        super(game, asset);

        setPosition(x, y);

        setWidthWhithAspectRatio(12);

        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Sensor));

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getStage().addActor(new StarshipBulletActor(game, world, getX()+6, getY()));
            }
        });
    }

    public void moveTo(float x){
        ((SimpleWorldHelper)getActorWorldHelper()).body.moveToFixSpeed(x - getWidth() / 2, getY(), 100, PositionRule.LeftBottom);
    }
}
