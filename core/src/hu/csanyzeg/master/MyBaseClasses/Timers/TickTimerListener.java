package hu.csanyzeg.master.MyBaseClasses.Timers;

public interface TickTimerListener extends TimerListener<Timer> {
    public void onRepeat(TickTimer sender);
}
