package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineText;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineTextStyle;

public class SimpleMultiLineText extends WorldMultiLineText<SimpleWorldHelper, SimpleWorld> {
    public SimpleMultiLineText(MyGame game, SimpleWorld world, String text, WorldMultiLineTextStyle simpleMultiLineTextStyle) {
        super(game, world, text, simpleMultiLineTextStyle);
    }

    @Override
    public SimpleWorldHelper createHelper(SimpleWorld world, Actor actor) {
        return new SimpleWorldHelper(world, this, ShapeType.Rectangle, SimpleBodyType.Ghost);
    }

    @Override
    public WorldLabel<SimpleWorldHelper, SimpleWorld> createLabel(MyGame game, SimpleWorld world, CharSequence text, WorldLabelStyle simpleButtonStyle) {
        return new SimpleLabel(game,world,text,simpleButtonStyle);
    }
}
