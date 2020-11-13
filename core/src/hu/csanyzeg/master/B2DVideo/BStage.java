package hu.csanyzeg.master.B2DVideo;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Box2dUI.Box2dLabel;
import hu.csanyzeg.master.MyBaseClasses.Box2dUI.Box2dMultiLineText;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2dStage;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineText;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineTextStyle;

public class BStage extends Box2dStage {
    public BStage(MyGame game) {
        super(new ResponseViewport(160), game);


        world.setGravity(new Vector2(0, -9.81f));

        OneSpriteStaticActor actor = new OneSpriteStaticActor(game, "badlogic.jpg");
        actor.setSize(10,10);
        actor.setPosition(75, 80);
        actor.setActorWorldHelper(new Box2DWorldHelper(world, actor, ShapeType.Circle, new MyFixtureDef(0.2f, 0.5f, 5, false), BodyDef.BodyType.DynamicBody));
        addActor(actor);

        //getBody(actor).setAngularVelocity(10);
        //getBody(actor).setLinearVelocity(new Vector2(10,2));

/*
        OneSpriteStaticActor actor2 = new OneSpriteStaticActor(game, "badlogic.jpg");
        actor2.setSize(10,10);
        actor2.setPosition(83, 100);
        actor2.setActorWorldHelper(new Box2DWorldHelper(world, actor2, ShapeType.Circle, new MyFixtureDef(3.2f, 1f, 5, false), BodyDef.BodyType.DynamicBody));
        addActor(actor2);
*/
        OneSpriteStaticActor floor = new OneSpriteStaticActor(game, "badlogic.jpg");
        floor.setSize(160,5);
        floor.setActorWorldHelper(new Box2DWorldHelper(world, floor, ShapeType.Rectangle, new MyFixtureDef(), BodyDef.BodyType.StaticBody));
        addActor(floor);

        WorldMultiLineTextStyle worldLabelStyle = new WorldMultiLineTextStyle("demoflappy/flappyfont.ttf", 10);

        Box2dLabel l = new Box2dLabel(game, world, "Teszt Teszt Teszt Teszt Teszteszt Teszt Teszt Teszt Teszt TesztTeszt Teszt Teszt", worldLabelStyle);
        addActor(l);
        l.setPosition(0,60);

    }
}
