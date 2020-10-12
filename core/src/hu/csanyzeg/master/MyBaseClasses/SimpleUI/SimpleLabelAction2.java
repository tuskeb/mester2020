package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public class SimpleLabelAction2 implements SimpleLabelListener {
    float playTime;

    public SimpleLabelAction2(float playTime) {
        this.playTime = playTime;
    }

    @Override
    public void onShow(SimpleLabel sender2, Array<SimpleChar> charArray) {
        sender2.setColorMode(SimpleLabel.ColorMode.byChar);

        sender2.addTimer(new TickTimer(playTime,false, new TickTimerListener(){
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

        float w = body.getWidth();
        float h = body.getHeight();
        Color color = body.getColor();

        body.setColor(color.r, color.g, color.b, 0);

        body.setPosition(body.getWidth()/ 2 + body.getX(), body.getHeight() / 2);
        body.setSize(1,1);

        body.colorToFixTime( playTime / 2, color.r, color.g, color.b, 1);
        body.sizeToFixTime(w,h,playTime / 2, PositionRule.Center);
        simpleChar.addTimer(new TickTimer(playTime / 2,false,new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                body.colorToFixTime( playTime / 2f, color.r, color.g, color.b, 0);
                body.sizeToFixTime(w*1.5f,h*1.5f,1f, PositionRule.Center);
                simpleChar.removeTimer(sender);
            }
        }));
    }

}
