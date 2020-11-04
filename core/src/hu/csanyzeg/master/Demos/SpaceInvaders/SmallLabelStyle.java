package hu.csanyzeg.master.Demos.SpaceInvaders;

import hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos.DemoSimpleLabelListener1;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class SmallLabelStyle extends WorldLabelStyle {
    public SmallLabelStyle() {
        super("spaceinvaders/littleboxes2.ttf", 100);
        fontSpacing = 10;
        simpleLabelListener = new DemoSimpleLabelListener1();
    }
}
