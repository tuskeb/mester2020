package hu.csanyzeg.master.MyBaseClasses.Timers;

public class MultiTickTimer extends ETTimer<MultiTickTimerListener> {
    protected float interval;
    protected int count = 0;
    protected int targetCount;

    public MultiTickTimer(float interval, int count, MultiTickTimerListener timerListener) {
        setTimerListener(timerListener);
        this.interval = interval;
        this.targetCount = count;
        start();
    }

    @Override
    public void act(float delta){
        super.act(delta);
        if (!enabled) return;
        if (elapsedTime >= interval){
            count++;
            float correction = elapsedTime-interval;
            if (timerListener !=null){
                timerListener.onTick(this, correction, count);
            }
            elapsedTime = correction;
            if (count == targetCount){
                remove();
            }
        }
    }

    public int getCount() {
        return count;
    }

    @Override
    public void start() {
        super.start();
        count = 0;
    }
}
