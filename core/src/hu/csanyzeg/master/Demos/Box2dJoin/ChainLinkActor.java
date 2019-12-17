package hu.csanyzeg.master.Demos.Box2dJoin;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WeldJoint;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TimerListener;

public class ChainLinkActor extends OneSpriteStaticActor {
    public static final String linkTexture = "box2dhelper/link.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(linkTexture);
    }

    public ChainLinkActor(MyGame game, World world, WorldBodyEditorLoader loader, float x, float y, float scale, float rotation) {
        super(game, linkTexture);
        setSize(1.28f,0.57f);
        setPosition(x,y);
        setRotation(rotation);
        setActorWorldHelper(new Box2DWorldHelper(world, this, loader, "link.png", new MyFixtureDef(), BodyDef.BodyType.DynamicBody));
        addTimer(new PermanentTimer(new TimerListener<PermanentTimer>() {
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                setColor(getColor().add(0,0.5f * correction,0.5f  * correction,0));
            }

            @Override
            public void onStop(PermanentTimer sender) {

            }

            @Override
            public void onStart(PermanentTimer sender) {

            }
        }));
    }

    public void setForceColor(float force){
        setColor(getColor().sub(0,force / 50,force / 50,0));
    }
}
