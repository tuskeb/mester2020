package hu.csanyzeg.master.Demos.SpaceInvaders;

import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction2;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelStyle;

public class PointLabelStyle  extends SimpleLabelStyle {
    public PointLabelStyle() {
        fontSize = 80;
        fontHash = "spaceinvaders/littleboxes2.ttf";
        simpleUIListener = new SimpleLabelAction2(1);
    }
}
