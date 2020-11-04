package hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldButton;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldButtonListener;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;

public class DemoSimpleButtonListener1 implements WorldButtonListener {

    float labelX;
    float labelY;

    @Override
    public void onShow(WorldButton sender, WorldLabel simpleLabel, MyActor background, MyActor backgroundClicked) {
        SimpleBody bgbody = (SimpleBody) background.getActorWorldHelper().getBody();
        SimpleBody labelbody = SimpleWorldStage.getBody(simpleLabel);

        labelX = labelbody.getX();
        labelY = labelbody.getY();

        float x = labelbody.getX();
        float y = labelbody.getY();
        labelbody.setPosition(x-100, y);
        labelbody.setAlpha(0f);
        labelbody.alphaToFixTIme(0.3f, 1f);
        labelbody.moveToFixTime(x,y,0.3f, PositionRule.LeftBottom);

        bgbody.setPosition(-100, 0);
        bgbody.setAlpha(0f);
        bgbody.alphaToFixTIme(0.3f, 1f);
        bgbody.moveToFixTime(0,0f,0.3f, PositionRule.LeftBottom);
    }

    @Override
    public void onHide(WorldButton sender, WorldLabel simpleLabel, MyActor background, MyActor backgroundClicked) {

    }

    @Override
    public void onClick(WorldButton sender, WorldLabel simpleLabel, MyActor background, MyActor backgroundClicked) {
        SimpleBody bgbody = SimpleWorldStage.getBody(background);
        SimpleBody bgclbody = SimpleWorldStage.getBody(backgroundClicked);
        SimpleBody labelbody = SimpleWorldStage.getBody(simpleLabel);

        background.setVisible(true);

        bgbody.setAlpha(0);
        bgbody.setY(-10);
        bgbody.moveToFixTime(0,0,0.3f,PositionRule.LeftBottom);
        bgbody.alphaToFixTIme(0.3f,1);

        bgclbody.setAlpha(1);
        bgclbody.setY(-10);
        bgclbody.moveToFixTime(0,0,0.3f,PositionRule.LeftBottom);
        bgclbody.alphaToFixTIme(0.3f,0);

        labelbody.setAlpha(0.7f);
        labelbody.setY(labelY -10);
        labelbody.moveToFixTime(labelX, labelY,0.3f,PositionRule.LeftBottom);
        labelbody.alphaToFixTIme(0.3f*3f,1);
    }
}
