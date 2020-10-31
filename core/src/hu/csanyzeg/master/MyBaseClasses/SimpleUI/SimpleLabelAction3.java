package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;
import sun.security.jgss.GSSUtil;


public class SimpleLabelAction3 implements SimpleLabelListener {

    public float changeingTime = 0.3f;
    public float appearingTime = 1f;
    public float appearDiffTime = 0.1f;

    @Override
    public void onShow(SimpleLabel sender, Array<SimpleChar> simpleCharArray) {
        sender.setColorMode(SimpleLabel.ColorMode.byChar);
    }

    public SimpleLabelAction3(float changeingTime, float appearingTime, float appearDiffTime) {
        this.changeingTime = changeingTime;
        this.appearingTime = appearingTime;
        this.appearDiffTime = appearDiffTime;
    }

    public SimpleLabelAction3() {
    }

    @Override
    public void onCharAdd(SimpleLabel sender, SimpleChar simpleChar, int index) {
        Color c = simpleChar.getBody().getColor();
        simpleChar.getBody().setColor(c.r, c.g, c.b, 0f);
        simpleChar.getBody().colorToFixTime(appearingTime + index * appearDiffTime, c.r, c.g, c.b, 1f);
    }


    @Override
    public boolean onCharChange(SimpleLabel sender, SimpleChar oldSimpleChar, SimpleChar newSimpleChar) {
        if (sender.getSimpleLabelStyle().fontWidthMode == SimpleLabel.FontWidthMode.variable || oldSimpleChar.getChar() != newSimpleChar.getChar() ||
                sender.getSimpleLabelStyle().fontWidthMode == SimpleLabel.FontWidthMode.variable) {
            SimpleBody bodyOld = oldSimpleChar.getBody();
            SimpleBody bodyNew = newSimpleChar.getBody();

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
