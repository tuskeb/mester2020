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
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineText;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineTextStyle;

public class Box2dMultiLineText extends WorldMultiLineText<Box2DWorldHelper, World> {

    public Box2dMultiLineText(MyGame game, World world, String text, WorldMultiLineTextStyle simpleMultiLineTextStyle) {
        super(game, world, text, simpleMultiLineTextStyle);
    }

    @Override
    public Box2DWorldHelper createHelper(World world, Actor actor) {
        MyFixtureDef fd = new MyFixtureDef();
        fd.isSensor = true;
        return new Box2DWorldHelper(world, actor, ShapeType.Rectangle, fd, BodyDef.BodyType.DynamicBody);
    }

    @Override
    public WorldLabel<Box2DWorldHelper, World> createLabel(MyGame game, World world, CharSequence text, WorldLabelStyle simpleButtonStyle) {
        return new Box2dLabel(game, world, text, simpleButtonStyle);
    }
}
