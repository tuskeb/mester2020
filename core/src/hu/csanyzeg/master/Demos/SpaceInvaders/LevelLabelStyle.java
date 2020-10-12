package hu.csanyzeg.master.Demos.SpaceInvaders;

import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction1;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction2;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelStyle;

public class LevelLabelStyle extends SimpleLabelStyle {
    public LevelLabelStyle() {
        fontSize = 260;
        fontHash = "spaceinvaders/littleboxes2.ttf";
        simpleUIListener = new SimpleLabelAction2(1.5f);
    }
}
