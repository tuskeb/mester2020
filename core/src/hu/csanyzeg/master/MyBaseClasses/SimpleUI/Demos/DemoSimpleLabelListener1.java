package hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos;

import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldChar;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimerListener;

public class DemoSimpleLabelListener1 implements WorldLabelListener {


    @Override
    public void onShow(WorldLabel sender2, Array<WorldChar> charArray) {
        sender2.setColorMode(WorldLabel.ColorMode.byChar);
        sender2.addTimer(new OneTickTimer(6,  new OneTickTimerListener(){
            @Override
            public void onTick(OneTickTimer sender, float correction) {
                super.onTick(sender, correction);
                sender2.remove();
            }
        }));
    }

    @Override
    public void onHide(WorldLabel sender, Array<WorldChar> charArray) {

    }



    @Override
    public void onCharAdd(WorldLabel sender, WorldChar simpleChar, int index) {

        SimpleBody body = SimpleWorldStage.getBody(simpleChar);

        float time = 1f + 0.01f * (index + 1);
        float position = body.getX();
        body.setX(0);
        body.moveToFixTime(position, 0, 1, PositionRule.LeftBottom);

        body.setColor(1, 1, 1, 0);
        body.colorToFixTime(time * time * time, 1, 0, 1, 1);

        body.setRotation(-180);
        body.rotateToFixTime(0, 1, Direction.ClockWise);

        simpleChar.addTimer(new OneTickTimer(0.15f + time * 2, new OneTickTimerListener() {
            @Override
            public void onTick(OneTickTimer sender, float correction) {
                super.onTick(sender, correction);
                //((SimpleBody)myGroup.getActorWorldHelper().getBody()).colorToFixTime(1,1,1,1,0.5f);
                body.moveToFixTime(body.getX(), 20, 0.2f, PositionRule.LeftBottom);
            }
        }));

        simpleChar.addTimer(new OneTickTimer(0.3f + time * 2, new OneTickTimerListener() {
            @Override
            public void onTick(OneTickTimer sender, float correction) {
                super.onTick(sender, correction);
                body.colorToFixTime(1, 0, 1, 0, 0.8f);
                body.moveToFixTime(body.getX(), 0, 0.1f, PositionRule.LeftBottom);
            }
        }));

        simpleChar.addTimer(new OneTickTimer(2 + time * 2, new OneTickTimerListener() {
            @Override
            public void onTick(OneTickTimer sender, float correction) {
                super.onTick(sender, correction);
                body.colorToFixTime(1, 1, 1, 1, 0);
                body.moveToFixTime(0, 0, 1, PositionRule.LeftBottom);
                body.rotateToFixTime(270, 1, Direction.ClockWise);
            }
        }));

    }

}
