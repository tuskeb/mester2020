package hu.csanyzeg.master.MyBaseClasses.Box2dWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;

public class MyJoint {
    public final MyActor bActor;
    public final MyActor aActor;
    public final Joint joint;
    private boolean removed = false;

    public boolean isRemoved() {
        return removed;
    }

    public void remove(){
        removeJoint(this);
    }

    private MyJoint(MyActor bActor, MyActor aActor, Joint joint) {
        this.bActor = bActor;
        this.aActor = aActor;
        this.joint = joint;
    }




    public static void createWeldJoint(MyActor a, MyActor b){
        WeldJointDef jointDef = new WeldJointDef();
        Body bodyA = (Body) a.getActorWorldHelper().getBody();
        Body bodyB = (Body) b.getActorWorldHelper().getBody();
        Vector2 anchor = new Vector2();
        anchor.add(bodyA.getPosition());
        anchor.add(bodyB.getPosition());
        anchor.scl(0.5f);
        jointDef.initialize(bodyA, bodyB, anchor);
        createJoint(a,b,jointDef);
    }

    public static void createRevoluteJoint(MyActor a, MyActor b){
        RevoluteJointDef jointDef = new RevoluteJointDef();
        Body bodyA = (Body) a.getActorWorldHelper().getBody();
        Body bodyB = (Body) b.getActorWorldHelper().getBody();
        Vector2 anchor = new Vector2();
        anchor.add(bodyA.getPosition());
        anchor.add(bodyB.getPosition());
        anchor.scl(0.5f);
        jointDef.initialize(bodyA, bodyB, anchor);
        createJoint(a,b,jointDef);
    }

    public static void removeJoint(final MyJoint joint) {
        ((Box2DWorldHelper) joint.bActor.getActorWorldHelper()).invoke(new Runnable() {
                                                                          @Override
                                                                          public void run() {
                                                                              if (!joint.removed) {
                                                                                  joint.removed = true;
                                                                                  Box2DWorldHelper helper = ((Box2DWorldHelper) joint.bActor.getActorWorldHelper());
                                                                                  helper.getJoints().removeValue(joint, true);
                                                                                  helper.world.destroyJoint(joint.joint);
                                                                                  ((Box2DWorldHelper) joint.aActor.getActorWorldHelper()).getJoints().removeValue(joint, true);
                                                                              }
                                                                          }
                                                                      }
        );
    }

    public static void createJoint(MyActor a, MyActor b, JointDef jointDef){
        Joint w = ((Box2DWorldHelper)a.getActorWorldHelper()).world.createJoint(jointDef);
        MyJoint myJoint = new MyJoint(a,b,w);
        ((Box2DWorldHelper)(a.getActorWorldHelper())).addJoint(myJoint);
        ((Box2DWorldHelper)(b.getActorWorldHelper())).addJoint(myJoint);
    }


}
