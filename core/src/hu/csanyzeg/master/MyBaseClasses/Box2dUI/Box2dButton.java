package hu.csanyzeg.master.MyBaseClasses.Box2dUI;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldButton;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldButtonStyle;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class Box2dButton extends WorldButton<Box2DWorldHelper, World> {

    public Box2dButton(MyGame game, World world, CharSequence text, WorldButtonStyle simpleButtonStyle) {
        super(game, world, text, simpleButtonStyle);
    }

    @Override
    public Box2DWorldHelper createHelper(World world, Actor actor) {
        return new Box2DWorldHelper(world, actor, ShapeType.Rectangle, new MyFixtureDef(), BodyDef.BodyType.KinematicBody);
    }

    @Override
    public WorldLabel<Box2DWorldHelper, World> createLabel(MyGame game, World world, CharSequence text, WorldLabelStyle simpleButtonStyle) {
        return new Box2dLabel(game, world, text, simpleButtonStyle);
    }
}
