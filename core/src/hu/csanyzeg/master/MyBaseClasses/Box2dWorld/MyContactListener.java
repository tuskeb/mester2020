package hu.csanyzeg.master.MyBaseClasses.Box2dWorld;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.WorldHelper;

public abstract class MyContactListener {
    public abstract void beginContact(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper);

    public abstract void endContact(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper);

    public abstract void preSolve(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper);

    public abstract void postSolve(Contact contact, Box2DWorldHelper myHelper, Box2DWorldHelper otherHelper);
}
