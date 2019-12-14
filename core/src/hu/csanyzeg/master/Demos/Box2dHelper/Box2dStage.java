package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public class Box2dStage extends MyStage {

    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(BoxActor.class, assetList);
        AssetList.collectAssetDescriptor(WallActor.class, assetList);
    }

    private World world;
    Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();

    public Box2dStage(MyGame game) {
        super(new ExtendViewport(16,9), game);
        world  = new World(new Vector2(0,-9.81f), false);
        world.setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

                if (contact.getFixtureA().getUserData() instanceof BoxActor){
                    ((BoxActor)contact.getFixtureA().getUserData()).setFlash();
                }
                if (contact.getFixtureB().getUserData() instanceof BoxActor){
                    ((BoxActor)contact.getFixtureA().getUserData()).setFlash();
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



        BoxActor b;
        addActor(new BoxActor(game, world,3,5,10));
        addActor(new BoxActor(game, world,5,5,20));
        addActor(b = new BoxActor(game, world,7,5,30));
        addActor(new WallActor(game, world,0,0,16,1,0));

        b.setSize(2,2);
    }

    @Override
    public void init() {

    }


    @Override
    public void act(float delta) {
        world.step(delta,5,5);
        super.act(delta);
    }

    @Override
    public void draw() {
        super.draw();
        box2DDebugRenderer.render(world, getCamera().combined);

    }
}
