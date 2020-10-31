package hu.csanyzeg.master.MyBaseClasses.Timers;

public class VariableTimer extends ETTimer<VariableTimerListener> {
    protected int count = 0;
    protected float[] intervals;

    public VariableTimer(float[] intervals, VariableTimerListener timerListener) {
        setTimerListener(timerListener);
        this.intervals = intervals;
        start();
    }

    @Override
    public void act(float delta){
        super.act(delta);
        if (!enabled) return;
        if (elapsedTime >= intervals[count]){
            float correction = elapsedTime-intervals[count];
            count++;

            if (timerListener !=null){
                timerListener.onTick(this, correction, count);
            }
            elapsedTime = correction;
            if (count == intervals.length){
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
