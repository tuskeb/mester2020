package hu.csanyzeg.master.Demos.SpaceInvaders;

import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction1;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelStyle;

public class SmallLabelStyle extends SimpleLabelStyle {
    public SmallLabelStyle() {
        fontSize = 90;
        fontSpacing = 10;
        fontHash = "spaceinvaders/littleboxes2.ttf";
        simpleUIListener = new SimpleLabelAction1();
    }
}
