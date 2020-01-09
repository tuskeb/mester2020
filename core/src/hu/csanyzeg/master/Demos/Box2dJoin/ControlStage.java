package hu.csanyzeg.master.Demos.Box2dJoin;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import hu.csanyzeg.master.Demos.LoadingStage.DemoPreLoadingStage;
import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

//https://www.iforce2d.net/b2dtut/joints-overview
/*
Joints

Box2D has a number of 'joints' that can be used to connect two bodies together. These joints can be used to simulate interaction between objects to form hinges, pistons, ropes, wheels, pulleys, vehicles, chains, etc. Learning to use joints effectively helps to create a more engaging and interesting scene.

Lets take a quick look at the available joints, then go over their characteristics, then finally we'll make an example using a few of the most commonly used ones. Here are the joints in Box2D v2.1.2:

    Revolute - a hinge or pin, where the bodies rotate about a common point
    Distance - a point on each body will be kept at a fixed distance apart
    Prismatic - the relative rotation of the two bodies is fixed, and they can slide along an axis
    Line - a combination of revolute and prismatic joints, useful for modelling vehicle suspension
    Weld - holds the bodies at the same orientation
    Pulley - a point on each body will be kept within a certain distance from a point in the world,
    where the sum of these two distances is fixed, kinda... (sheesh... there is no succinct way to describe this)
    Friction - reduces the relative motion between the two bodies
    Gear - controls two other joints (revolute or prismatic) so that the movement of one affects the other
    Mouse - pulls a point on one body to a location in the world

Joints added after v2.1.2:

    Wheel - the line joint, renamed
    Rope - a point on each body will be constrained to a maximum distance apart

In the source code, the actual joint classes are named like b2RevoluteJoint, b2DistanceJoint, etc.
 */
public class ControlStage extends MyStage {
    Box2dJoinStage box2dJoinStage;
    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(MenuButton.class, assetList);
    }

    public ControlStage(MyGame game, final Box2dJoinStage box2dJoinStage) {
        super(new ExtendViewport(720,720), game);
        this.box2dJoinStage = box2dJoinStage;
        setCameraResetToLeftBottomOfScreen();
        addBackButtonScreenBackByStackPopListenerWithPreloadedAssets(new DemoPreLoadingStage(game));
        addActor(new MenuButton(game, "New Box"){
            @Override
            public void init() {
                super.init();
                setPosition(0,550);
                setSize(100,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        box2dJoinStage.addBox();
                    }
                });
            }
        });
        addActor(new MenuButton(game, "Prismatic"){
            @Override
            public void init() {
                super.init();
                setPosition(0,475);
                setSize(100,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        box2dJoinStage.addPrismatic();
                    }
                });
            }
        });



        addActor(new MenuButton(game, "Weld"){
            @Override
            public void init() {
                super.init();
                setPosition(0,400);
                setSize(100,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        box2dJoinStage.addWeld();
                    }
                });
            }
        });

        addActor(new MenuButton(game, "Revolute"){
            @Override
            public void init() {
                super.init();
                setPosition(0,325);
                setSize(100,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        box2dJoinStage.addRevolute();
                    }
                });
            }
        });

        addActor(new MenuButton(game, "Distance"){
            @Override
            public void init() {
                super.init();
                setPosition(0,250);
                setSize(100,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        box2dJoinStage.addDistance();
                    }
                });
            }
        });


        addActor(new MenuButton(game, "Mouse"){
            @Override
            public void init() {
                super.init();
                setPosition(0,175);
                setSize(100,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        box2dJoinStage.addMouse();
                    }
                });
            }
        });


        addActor(new MenuButton(game, "Rope"){
            @Override
            public void init() {
                super.init();
                setPosition(0,100);
                setSize(100,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        box2dJoinStage.addRope();
                    }
                });
            }
        });
    }
}
