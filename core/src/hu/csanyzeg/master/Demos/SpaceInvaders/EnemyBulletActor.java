package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.math.RandomXS128;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyContactListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleContact;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class EnemyBulletActor extends OneSpriteStaticActor {
    public static String asset =  "spaceinvaders/enemy1bullet.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(asset);
    }


    public EnemyBulletActor(MyGame game, SimpleWorld world, float x, float y) {
        super(game, asset);

        setPosition(x, y);

        setWidthWhithAspectRatio(30);

        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Sensor));

        ((SimpleWorldHelper)getActorWorldHelper()).body.moveToFixSpeed(getX(), 0 - getHeight(), 800, PositionRule.LeftBottom);

        ((SimpleWorldHelper)getActorWorldHelper()).body.setSimpleBodyBehaviorListener(new SimpleBodyBehaviorListener(){
            @Override
            public void onStop(SimpleBody sender) {
                super.onStop(sender);
                ((SimpleWorldHelper)getActorWorldHelper()).remove();
            }
        });

        ((SimpleWorldHelper)getActorWorldHelper()).addContactListener(new SimpleBodyContactListener(){
            @Override
            public void beginContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                super.beginContact(contact, myHelper, otherHelper);
                if (otherHelper.getActor() instanceof StarShipActor){
                    StarShipActor starShipActor = (StarShipActor) otherHelper.getActor();
                    starShipActor.death();
                    myHelper.remove();
                }
            }
        });
    }
}
