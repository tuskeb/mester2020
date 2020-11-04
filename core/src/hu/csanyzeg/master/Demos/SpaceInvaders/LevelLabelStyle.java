package hu.csanyzeg.master.Demos.SpaceInvaders;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos.DemoSimpleLabelListener2;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelStyle;

public class LevelLabelStyle extends WorldLabelStyle {

    public static AssetList assetList = new AssetList();
    static {
        assetList.addFont("spaceinvaders/littleboxes2.ttf");
    }


    public LevelLabelStyle() {
        super("spaceinvaders/littleboxes2.ttf", 220);
        fontSpacing = 60;
        simpleLabelListener = new DemoSimpleLabelListener2(2.5f);
    }
}
