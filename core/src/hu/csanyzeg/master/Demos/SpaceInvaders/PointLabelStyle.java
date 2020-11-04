package hu.csanyzeg.master.Demos.SpaceInvaders;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos.DemoSimpleLabelListener3;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class PointLabelStyle  extends WorldLabelStyle {

    public static AssetList assetList = new AssetList();
    static {
        assetList.addFont("demoflappy/flappyfont.ttf");
    }


    public PointLabelStyle() {
        super("demoflappy/flappyfont.ttf", 80);
        simpleLabelListener = new DemoSimpleLabelListener3();
        fontSpacing = 5;
        fontWidthMode = WorldLabel.FontWidthMode.monospace;
    }
}
