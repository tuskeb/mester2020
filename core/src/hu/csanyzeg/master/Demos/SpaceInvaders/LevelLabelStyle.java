package hu.csanyzeg.master.Demos.SpaceInvaders;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction1;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction2;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelStyle;

public class LevelLabelStyle extends SimpleLabelStyle {

    public static AssetList assetList = new AssetList();
    static {
        assetList.addFont("spaceinvaders/littleboxes2.ttf");
    }


    public LevelLabelStyle() {
        fontSize = 220;
        fontHash = "spaceinvaders/littleboxes2.ttf";
        fontSpacing = 60;
        simpleUIListener = new SimpleLabelAction2(2.5f);
    }
}
