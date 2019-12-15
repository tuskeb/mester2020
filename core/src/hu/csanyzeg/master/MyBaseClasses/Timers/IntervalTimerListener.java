package hu.csanyzeg.master.MyBaseClasses.Timers;

public interface IntervalTimerListener extends TimerListener<IntervalTimer> {
    public void onRepeat(IntervalTimer sender);
}
