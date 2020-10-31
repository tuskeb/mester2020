package hu.csanyzeg.master.MyBaseClasses.Timers;

public class MultiTickTimerListener extends TimerListener<MultiTickTimer>  {

    @Override
    public final void onTick(MultiTickTimer sender, float correction) {
        onTick(sender, correction, 0);
        System.out.println("A MultiTickTimerListener.onTick(MultiTickTimer sender, float correction) hivas helyett az onTick(MultiTickTimer sender, float correction, int count) metodust kell hasznalni");
    }

    public void onTick(MultiTickTimer sender, float correction, int count) {
    }
}
