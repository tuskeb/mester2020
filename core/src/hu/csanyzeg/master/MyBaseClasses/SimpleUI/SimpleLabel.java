package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldChar;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class SimpleLabel extends WorldLabel<SimpleWorldHelper, SimpleWorld> {
    public SimpleLabel(MyGame game, SimpleWorld world, CharSequence text, WorldLabelStyle simpleLabelStyle) {
        super(game, world, text, simpleLabelStyle);
    }

    @Override
    public SimpleWorldHelper createHelper(SimpleWorld world, Actor actor) {
        return new SimpleWorldHelper(world, actor, ShapeType.Rectangle, SimpleBodyType.Ghost);
    }

    @Override
    public WorldChar<SimpleWorldHelper, SimpleWorld> createChar(MyGame game, SimpleWorld world, WorldLabelStyle simpleLabelStyle, char c) {
        return new SimpleChar(game, world, simpleLabelStyle, c);
    }
}
