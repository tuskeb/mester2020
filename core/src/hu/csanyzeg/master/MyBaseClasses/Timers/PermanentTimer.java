package hu.csanyzeg.master.MyBaseClasses.Timers;

public class PermanentTimer extends Timer<TimerListener> {
    public PermanentTimer() {
        start();
    }

    @Override
    public void act(float delta) {
        if (enabled && timerListener != null){
            timerListener.onTick(this, delta);
        }
    }
}
