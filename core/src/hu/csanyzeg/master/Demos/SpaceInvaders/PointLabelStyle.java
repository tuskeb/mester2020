package hu.csanyzeg.master.Demos.SpaceInvaders;

import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabel;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction2;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction3;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelStyle;

public class PointLabelStyle  extends SimpleLabelStyle {
    public PointLabelStyle() {
        fontSize = 80;
//        fontHash = "spaceinvaders/littleboxes2.ttf";
        fontHash = "demoflappy/flappyfont.ttf";
        simpleUIListener = new SimpleLabelAction3();
        fontSpacing = 5;
        fontWidthMode = SimpleLabel.FontWidthMode.monospace;
    }
}
