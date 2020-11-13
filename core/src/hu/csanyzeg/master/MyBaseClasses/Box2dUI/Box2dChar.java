package hu.csanyzeg.master.MyBaseClasses.Box2dUI;

import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldChar;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class Box2dChar extends WorldChar<Box2DWorldHelper, World> {

    public Box2dChar(MyGame game, World world, WorldLabelStyle simpleLabelStyle, char c) {
        super(game, world, simpleLabelStyle, c);
    }

    @Override
    public Box2DWorldHelper createHelper(World world) {
        return new Box2DWorldHelper(world, this, ShapeType.Rectangle, new MyFixtureDef(), BodyDef.BodyType.DynamicBody);
    }
}
