package hu.csanyzeg.master.Demos.SimpleUI;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos.DemoSimpleButtonStyle1;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos.DemoSimpleLabelListener2;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos.DemoSimpleMultiLineTextStyle1;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleButton;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabel;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleMultiLineText;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldMultiLineText;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;

public class SimpleUIStage extends SimpleWorldStage {
    public SimpleUIStage(MyGame game) {
        super(new ResponseViewport(512), game);
        SimpleLabel labelActor = new SimpleLabel(game, world, "asdf", new DemoSimpleButtonStyle1());
        addActor(labelActor);
        addActor(new SimpleButton(game,world,"Click here", new DemoSimpleButtonStyle1()));
        addActor(new SimpleMultiLineText(game, world, "Lorem ipsum dolor sit amet, consectetur adipiscing elit,\nsed do eiusmod tempor incididunt ut labore et dolore magna\naliqua. Ut enim ad minim veniam, quis nostrud exercitation\nullamco laboris nisi ut aliquip ex ea commodo consequat.\nDuis aute irure dolor in reprehenderit\nin voluptate velit esse cillum dolore eu fugiat nulla pariatur.\nExcepteur sint occaecat cupidatat non proident, sunt in\nculpa qui officia deserunt mollit anim id est laborum.", new DemoSimpleMultiLineTextStyle1()));
        ((WorldMultiLineText)getLastAdded()).setPositionCenterOfActorToCenterOfViewport();
    }
}
