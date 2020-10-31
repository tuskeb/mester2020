package hu.csanyzeg.master.MyBaseClasses.Timers;

public abstract class ETTimer<Listener> extends Timer<Listener> {
    protected float elapsedTime = 0;

    public void start(){
        super.start();
        elapsedTime = 0;
    }

    @Override
    public void act(float delta) {
        if (!enabled) return;
        elapsedTime += delta;
    }

    public float getElapsedTime() {
        return elapsedTime;
    }

    protected void setElapsedTime(float elapsedTime) {
        this.elapsedTime = elapsedTime;
    }
}
