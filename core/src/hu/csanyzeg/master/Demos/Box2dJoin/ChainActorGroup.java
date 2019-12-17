package hu.csanyzeg.master.Demos.Box2dJoin;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJoint;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;

public class ChainActorGroup extends MyGroup {
    World world;
    WorldBodyEditorLoader loader;
    public float linkCount = 17;
    protected Array<ChainLinkActor> chainLinkActors = new Array<>();
    public Array<WeldJoint> weldJoints = new Array<>();

    public ChainActorGroup(MyGame game, World world, WorldBodyEditorLoader loader, float x, float y) {
        super(game);
        //setPosition(x,y);
        this.world = world;
        this.loader = loader;
        ChainLinkActor chainLinkActor = null;
        for(int i = 0; i< linkCount; i++){
            chainLinkActor = new ChainLinkActor(game, world, loader, x + (chainLinkActor == null ? 0 : chainLinkActor.getWidth() * i) * 0.6f , y,1,0);
            addActor(chainLinkActor);
            chainLinkActors.add(chainLinkActor);
        }
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (stage != null) {
            for (int i = 1; i < chainLinkActors.size; i++) {
                RevoluteJointDef jointDef = new RevoluteJointDef();
                //WeldJointDef jointDef = new WeldJointDef();
                Body bodyA = (Body) chainLinkActors.get(i - 1).getActorWorldHelper().getBody();
                Body bodyB = (Body) chainLinkActors.get(i).getActorWorldHelper().getBody();
                Vector2 anchor = new Vector2();
                anchor.add(bodyA.getPosition());
                anchor.add(bodyB.getPosition());
                anchor.scl(0.5f);
                jointDef.initialize(bodyA, bodyB, anchor);
                Joint w = world.createJoint(jointDef);
                //chainLinkActors.get(i-1).joint1 = w;
                chainLinkActors.get(i).joint2 = w;
                //weldJoints.add(w);
            }
        }

    }
}
