package hu.csanyzeg.master.MyBaseClasses.Timers;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.ITimer;

public abstract class Timer<Listener> {

    protected Listener timerListener;
    protected ITimer parent = null;

    public Listener getTimerListener() {
        return timerListener;
    }

    public void setTimerListener(Listener timerListener) {
        this.timerListener = timerListener;
    }

    protected boolean enabled = true;

    public abstract void act(float delta);

    public void start(){
        enabled = true;
        ((TimerListener)timerListener).onStart(this);
    }

    public void stop(){
        enabled = false;
        ((TimerListener)timerListener).onStop(this);
    }

    public ITimer getParent() {
        return parent;
    }

    public void setParent(ITimer parent) {
        this.parent = parent;
    }

    public void remove(){
        if (parent!=null){
            parent.removeTimer(this);
        }
    }
}
