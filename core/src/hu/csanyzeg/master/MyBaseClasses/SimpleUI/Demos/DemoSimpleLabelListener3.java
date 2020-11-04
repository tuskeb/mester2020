package hu.csanyzeg.master.MyBaseClasses.SimpleUI.Demos;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldChar;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldUI.WorldLabelListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;


public class DemoSimpleLabelListener3 implements WorldLabelListener {

    public float changeingTime = 0.3f;
    public float appearingTime = 1f;
    public float appearDiffTime = 0.1f;

    @Override
    public void onShow(WorldLabel sender, Array<WorldChar> simpleCharArray) {
        sender.setColorMode(WorldLabel.ColorMode.byChar);
    }

    public DemoSimpleLabelListener3(float changeingTime, float appearingTime, float appearDiffTime) {
        this.changeingTime = changeingTime;
        this.appearingTime = appearingTime;
        this.appearDiffTime = appearDiffTime;
    }

    public DemoSimpleLabelListener3() {
    }

    @Override
    public void onCharAdd(WorldLabel sender, WorldChar simpleChar, int index) {
        Color c = SimpleWorldStage.getBody(simpleChar).getColor();
        SimpleWorldStage.getBody(simpleChar).setColor(c.r, c.g, c.b, 0f);
        SimpleWorldStage.getBody(simpleChar).colorToFixTime(appearingTime + index * appearDiffTime, c.r, c.g, c.b, 1f);
    }


    @Override
    public boolean onCharChange(WorldLabel sender, WorldChar oldSimpleChar, WorldChar newSimpleChar) {
        if (sender.getSimpleLabelStyle().fontWidthMode == WorldLabel.FontWidthMode.variable || oldSimpleChar.getChar() != newSimpleChar.getChar() ||
                sender.getSimpleLabelStyle().fontWidthMode == WorldLabel.FontWidthMode.variable) {
            SimpleBody bodyOld = SimpleWorldStage.getBody(oldSimpleChar);
            SimpleBody bodyNew = SimpleWorldStage.getBody(newSimpleChar);

            if (oldSimpleChar.getChar() != newSimpleChar.getChar()) {
                bodyOld.moveToFixTime(bodyOld.getX(), bodyOld.getY() + bodyOld.getHeight(), changeingTime, PositionRule.LeftBottom);
                bodyOld.colorToFixTime(changeingTime, bodyOld.getColor().r, bodyOld.getColor().g, bodyOld.getColor().b, 0f);

                bodyNew.setPosition(bodyNew.getX(), bodyNew.getY() - bodyNew.getHeight());
                bodyNew.setColor(bodyNew.getColor().r, bodyNew.getColor().g, bodyNew.getColor().b, 0f);
                bodyNew.moveToFixTime(bodyNew.getX(), bodyNew.getY() + bodyNew.getHeight(), changeingTime, PositionRule.LeftBottom);
                bodyNew.colorToFixTime(changeingTime, bodyNew.getColor().r, bodyNew.getColor().g, bodyNew.getColor().b, 1f);

                oldSimpleChar.addTimer(new TickTimer(changeingTime, false, new TickTimerListener() {
                    @Override
                    public void onTick(Timer send, float correction) {
                        super.onTick(send, correction);
                        oldSimpleChar.remove();
                    }
                }));
            } else {
                float x = bodyNew.getX();
                float y = bodyNew.getY();
                bodyNew.setPosition(bodyOld.getX(),bodyOld.getY());
                bodyNew.moveToFixTime(x, y , changeingTime, PositionRule.LeftBottom);
                oldSimpleChar.remove();
            }
            return false;
        }
        return true;
    }
}
