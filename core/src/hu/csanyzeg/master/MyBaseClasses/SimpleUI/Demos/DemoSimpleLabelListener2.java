package hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldChar;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimerListener;

public class DemoSimpleLabelListener2 implements WorldLabelListener {
    float playTime;

    public DemoSimpleLabelListener2(float playTime) {
        this.playTime = playTime;
    }

    @Override
    public void onShow(WorldLabel sender2, Array<WorldChar> charArray) {
        sender2.setColorMode(WorldLabel.ColorMode.byChar);

        sender2.addTimer(new OneTickTimer(playTime, new OneTickTimerListener(){
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

        float w = body.getWidth();
        float h = body.getHeight();
        Color color = body.getColor();

        body.setColor(color.r, color.g, color.b, 0);

        body.setPosition(body.getWidth()/ 2 + body.getX(), body.getHeight() / 2);
        body.setSize(1,1);

        body.colorToFixTime( playTime / 2, color.r, color.g, color.b, 1);
        body.sizeToFixTime(w,h,playTime / 2, PositionRule.Center);
        simpleChar.addTimer(new OneTickTimer(playTime / 2,new OneTickTimerListener(){
            @Override
            public void onTick(OneTickTimer sender, float correction) {
                super.onTick(sender, correction);
                body.colorToFixTime( playTime / 2f, color.r, color.g, color.b, 0);
                body.sizeToFixTime(w*1.5f,h*1.5f,1f, PositionRule.Center);
            }
        }));
    }

}
