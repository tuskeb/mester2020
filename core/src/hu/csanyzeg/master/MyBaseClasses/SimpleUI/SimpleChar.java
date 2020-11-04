package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldChar;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class SimpleChar extends WorldChar<SimpleWorldHelper, SimpleWorld> {


    public SimpleChar(MyGame game, SimpleWorld world, WorldLabelStyle simpleLabelStyle, char c) {
        super(game, world, simpleLabelStyle, c);
    }

    @Override
    public SimpleWorldHelper createHelper(SimpleWorld world) {
        return new SimpleWorldHelper(world, this, ShapeType.Null, SimpleBodyType.Ghost);
    }
}
