package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public class SimpleLabelAction1 implements SimpleLabelListener {


    @Override
    public void onShow(SimpleLabel sender2, Array<SimpleChar> charArray) {
        sender2.setColorMode(SimpleLabel.ColorMode.byChar);
        sender2.addTimer(new TickTimer(6, false, new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                sender2.removeTimer(sender);
                sender2.remove();
            }
        }));
    }

    @Override
    public void onHide(SimpleLabel sender, Array<SimpleChar> charArray) {

    }



    @Override
    public void onCharAdd(SimpleLabel sender, SimpleChar simpleChar, int index) {

        SimpleBody body = simpleChar.getBody();

        float time = 1f + 0.01f * (index + 1);
        float position = body.getX();
        body.setX(0);
        body.moveToFixTime(position, 0, 1, PositionRule.LeftBottom);

        body.setColor(1, 1, 1, 0);
        body.colorToFixTime(time * time * time, 1, 0, 1, 1);

        body.setRotation(-180);
        body.rotateToFixTime(0, 1, Direction.ClockWise);

        simpleChar.addTimer(new TickTimer(0.15f + time * 2, false, new TickTimerListener() {
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                //((SimpleBody)myGroup.getActorWorldHelper().getBody()).colorToFixTime(1,1,1,1,0.5f);
                body.moveToFixTime(body.getX(), 20, 0.2f, PositionRule.LeftBottom);
                simpleChar.removeTimer(sender);
            }
        }));

        simpleChar.addTimer(new TickTimer(0.3f + time * 2, false, new TickTimerListener() {
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                body.colorToFixTime(1, 0, 1, 0, 0.8f);
                body.moveToFixTime(body.getX(), 0, 0.1f, PositionRule.LeftBottom);
                simpleChar.removeTimer(sender);
            }
        }));

        simpleChar.addTimer(new TickTimer(2 + time * 2, false, new TickTimerListener() {
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                body.colorToFixTime(1, 1, 1, 1, 0);
                body.moveToFixTime(0, 0, 1, PositionRule.LeftBottom);
                body.rotateToFixTime(270, 1, Direction.ClockWise);
                simpleChar.removeTimer(sender);
            }
        }));

    }
}
