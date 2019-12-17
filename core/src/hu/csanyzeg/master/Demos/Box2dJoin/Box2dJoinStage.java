package hu.csanyzeg.master.Demos.Box2dJoin;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.DestructionListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanyzeg.master.Demos.Box2dHelper.BoxActor;
import hu.csanyzeg.master.Demos.Box2dHelper.WallActor;
import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2dStage;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

public class Box2dJoinStage extends Box2dStage {
    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(ChainLinkActor.class, assetList);
        AssetList.collectAssetDescriptor(WallActor.class, assetList);
        AssetList.collectAssetDescriptor(BoxActor.class, assetList);
        AssetList.collectAssetDescriptor(MenuButton.class, assetList);
    }

    public Box2dJoinStage(MyGame game) {
        super(new ExtendViewport(16,9), game);
        setLoader("box2dhelper/teszt.json");
        addActor(new WallActor(game, world,0,0,16,1,0));
        addActor(new BoxActor(game, world, 6,1,2,2));
        addActor(new ChainActorGroup(game, world, loader,1,8));
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {
                if (contact.getFixtureB().getUserData() instanceof ChainLinkActor){
                    final ChainLinkActor c = (ChainLinkActor) contact.getFixtureB().getUserData();
                    if (c.joint1!=null){
                        System.out.println(c.joint1.getReactionForce(1).len());
                        if (c.joint1.getReactionForce(1).len()>15f){
                            ((Box2DWorldHelper)c.getActorWorldHelper()).invoke(new Runnable() {
                                @Override
                                public void run() {
                                    if (c.joint1 != null) {
                                        world.destroyJoint(c.joint1);
                                        c.joint1 = null;
                                    }
                                }
                            });
                        }
                    }
                    if (c.joint2!=null){
                        System.out.println(c.joint2.getReactionForce(1).len());
                        if (c.joint2.getReactionForce(1).len()>15f){
                            ((Box2DWorldHelper)c.getActorWorldHelper()).invoke(new Runnable() {
                                @Override
                                public void run() {
                                    if (c.joint2!=null) {
                                        world.destroyJoint(c.joint2);
                                        c.joint2 = null;
                                    }
                                }
                            });
                        }
                    }

                }
            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }
}
