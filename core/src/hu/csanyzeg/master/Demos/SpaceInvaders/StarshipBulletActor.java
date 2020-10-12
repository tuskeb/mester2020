package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Stage;

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

public class StarshipBulletActor extends OneSpriteStaticActor {
    public static String asset =  "spaceinvaders/starshipbullet.png";

    private SimpleWorld world;

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(asset);
    }


    public StarshipBulletActor(MyGame game, SimpleWorld world, float x, float y) {
        super(game, asset);

        setPosition(x, y);

        setWidthWhithAspectRatio(10);

        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Sensor));

        this.world = world;
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        ((SimpleWorldHelper)getActorWorldHelper()).body.moveToFixSpeed(getX(), getStage().getHeight(), 800, PositionRule.LeftBottom);

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
                if (otherHelper.actor instanceof EnemyActor){
                    ((EnemyActor)otherHelper.actor).death();
                    myHelper.remove();
                }
            }
        });
    }
}
