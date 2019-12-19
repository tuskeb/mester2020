package hu.csanyzeg.master.Demos.Box2dJoin;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.WeldJoint;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyContactListener;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyJoint;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

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
        addTimer(new PermanentTimer(new PermanentTimerListener() {
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

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                remove();
            }
        });

        ((Box2DWorldHelper)getActorWorldHelper()).addContactListener(new MyContactListener() {
            @Override
            public void beginContact(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper) {
                if (otherHelper.getActor() instanceof ChainLinkActor){
                    for(MyJoint j : otherHelper.getJoints()){
                        ((ChainLinkActor)otherHelper.getActor()).setForceColor(j.joint.getReactionForce(1).len());
                        if (j.joint.getReactionForce(1).len()>18){
                            j.remove();
                        }
                    }
                }
            }

            @Override
            public void endContact(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper) {

            }

            @Override
            public void preSolve(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper) {

            }

            @Override
            public void postSolve(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper) {

            }
        });
    }

    public void setForceColor(float force) {
        float target = 1f - force / 10f < 0f ? 0f : 1f - force / 10f;
        setColor(1f, target < getColor().g ? target : getColor().g, target < getColor().b ? target : getColor().b, 1f);
    }
}
