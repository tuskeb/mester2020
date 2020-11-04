package hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineText;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineTextListener;

public class DemoSimpleMultiLineTextListener1 implements WorldMultiLineTextListener {
    @Override
    public void onRowAdd(WorldMultiLineText sender, WorldLabel simpleLabel, MyActor background, int index) {

        //SimpleWorldStage.getBody(simpleLabel).rotateTo(1,10);
    }
}
