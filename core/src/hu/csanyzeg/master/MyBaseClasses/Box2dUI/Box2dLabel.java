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
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldChar;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class Box2dLabel extends WorldLabel<Box2DWorldHelper, World> {
    public Box2dLabel(MyGame game, World world, CharSequence text, WorldLabelStyle simpleLabelStyle) {
        super(game, world, text, simpleLabelStyle);
    }

    @Override
    public Box2DWorldHelper createHelper(World world, Actor actor) {
        MyFixtureDef fd = new MyFixtureDef();
        fd.isSensor = true;
        return new Box2DWorldHelper(world, actor, ShapeType.Rectangle, fd, BodyDef.BodyType.StaticBody);
    }

    @Override
    public WorldChar<Box2DWorldHelper, World> createChar(MyGame game, World world, WorldLabelStyle simpleLabelStyle, char c) {
        return new Box2dChar(game, world, simpleLabelStyle, c);
    }
}
