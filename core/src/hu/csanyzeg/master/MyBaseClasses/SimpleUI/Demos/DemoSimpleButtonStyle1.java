package hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos;

import com.badlogic.gdx.graphics.Color;

import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldButtonStyle;

public class DemoSimpleButtonStyle1 extends WorldButtonStyle {
    public DemoSimpleButtonStyle1() {
        super("demoflappy/flappyfont.ttf", 40,512, 134, "buttonblue.png");
        backgroundClickedHash = "buttonbluedark.png";
        fontColor = new Color(1f / 255f,24f/ 255f,84f/ 255f, 1);
        simpleButtonListener = new DemoSimpleButtonListener1();
        simpleLabelListener = new DemoSimpleLabelListener3();
    }
}
