package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;

public class ExplosionActor extends OneSpriteAnimatedActor {
    public static String asset =  "spaceinvaders/explosion.atlas";
    public static RandomXS128 randomXS128 = new RandomXS128();

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTextureAtlas(asset);
    }


    public ExplosionActor(MyGame game, SimpleWorld world, Actor source) {
        super(game, asset);

        copyFrom(source);

        setOrigintoCenter();

        setRotation(randomXS128.nextInt(360));

        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Sensor));
        ((SimpleWorldHelper)getActorWorldHelper()).body.sizeToFixSpeed(getWidth()*5,getHeight()*5,8, PositionRule.Center);
        ((SimpleWorldHelper)getActorWorldHelper()).body.setAngularVelocity(randomXS128.nextInt(500)-250);
    }

    @Override
    protected void repeated() {
        super.repeated();
        getActorWorldHelper().remove();
    }
}
