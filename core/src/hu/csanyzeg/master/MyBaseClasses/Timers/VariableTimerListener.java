package hu.csanyzeg.master.MyBaseClasses.Timers;

public class VariableTimerListener extends TimerListener<VariableTimer>   {
    @Override
    public final void onTick(VariableTimer sender, float correction) {
        onTick(sender, correction, 0);
        System.out.println("A VariableTimerListener.onTick(MultiTickTimer sender, float correction) hivas helyett az onTick(MultiTickTimer sender, float correction, int count) metodust kell hasznalni");
    }

    public void onTick(VariableTimer sender, float correction, int count) {
    }
}
