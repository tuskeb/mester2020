package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldButton;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldButtonStyle;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class SimpleButton extends WorldButton<SimpleWorldHelper, SimpleWorld> {
    public SimpleButton(MyGame game, SimpleWorld world, CharSequence text, WorldButtonStyle simpleButtonStyle) {
        super(game, world, text, simpleButtonStyle);
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
