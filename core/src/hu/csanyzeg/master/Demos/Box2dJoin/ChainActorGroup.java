package hu.csanyzeg.master.Demos.Box2dJoin;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.RopeJoint;
import com.badlogic.gdx.physics.box2d.joints.RopeJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJoint;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyJoint;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.WorldBodyEditorLoader;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;

public class ChainActorGroup extends MyGroup {
    World world;
    WorldBodyEditorLoader loader;
    public float linkCount = 18;
    protected Array<ChainLinkActor> chainLinkActors = new Array<>();
    public Array<WeldJoint> weldJoints = new Array<>();
    protected JointDef.JointType jointType;

    public ChainActorGroup(MyGame game, World world, WorldBodyEditorLoader loader, float x, float y, JointDef.JointType jointType) {
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
        this.jointType = jointType;
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (stage != null) {
            for (int i = 1; i < chainLinkActors.size; i++) {
                switch (jointType){
                    case WeldJoint:
                        MyJoint.createWeldJoint(chainLinkActors.get(i-1), chainLinkActors.get(i));
                        break;
                    case RevoluteJoint:
                        MyJoint.createRevoluteJoint(chainLinkActors.get(i-1), chainLinkActors.get(i));
                        break;
                    case DistanceJoint:
                        MyJoint.createDistanceJoint(chainLinkActors.get(i-1), chainLinkActors.get(i));
                        break;
                    case MouseJoint:
                        if (i%2 == 1) {
                            MyJoint.createMouseJoint(chainLinkActors.get(i - 1), chainLinkActors.get(i), 30);
                        }
                        break;
                    case RopeJoint:
                        MyJoint.createRopeJoint(chainLinkActors.get(i - 1), chainLinkActors.get(i));
                        break;
                    case PrismaticJoint:
                        MyJoint.createPrismaticJoint(chainLinkActors.get(i - 1), chainLinkActors.get(i));
                        break;
                }
            }
            if (jointType == JointDef.JointType.RopeJoint){
                MyJoint.createRopeJoint(chainLinkActors.get(0), chainLinkActors.get(chainLinkActors.size-1));
            }
        }


    }
}
