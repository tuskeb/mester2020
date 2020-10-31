package hu.csanyzeg.master.MyBaseClasses.Timers;

public class OneTickTimer extends ETTimer<OneTickTimerListener>  {
    protected float interval;

    public OneTickTimer(float interval, OneTickTimerListener timerListener) {
        setTimerListener(timerListener);
        this.interval = interval;
        start();
    }

    @Override
    public void act(float delta){
        super.act(delta);
        if (!enabled) return;
        if (elapsedTime >= interval){
            if (timerListener !=null){
                float correction = elapsedTime-interval;
                timerListener.onTick(this, correction);
            }
            remove();
        }
    }
}
