package hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos;

import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldHorizontalAlign;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineTextStyle;

public class DemoSimpleMultiLineTextStyle1 extends WorldMultiLineTextStyle {
    public DemoSimpleMultiLineTextStyle1() {
        super("demoflappy/flappyfont.ttf", 20);
        simpleMultiLineTextListener = new DemoSimpleMultiLineTextListener1();
        simpleLabelListener = new DemoSimpleLabelListener3();
        horizontalAlign = WorldHorizontalAlign.center;
        lineSpacing = 4;

        //backgroundHash = "buttonbluedark.png";
    }

}
