package hu.csanyzeg.master.MyBaseClasses.Scene2D;

public class Timer {
    public interface TickListener{
        public void Tick(float correction);
    }

    public TickListener getTickListener() {
        return tickListener;
    }

    public void setTickListener(TickListener tickListener) {
        this.tickListener = tickListener;
    }

    public boolean isRepeat() {
        return repeat;
    }

    public void setRepeat(boolean repeat) {
        this.repeat = repeat;
    }

    public float getInterval() {
        return interval;
    }

    public void setInterval(float interval) {
        this.interval = interval;
        elapsedTime = 0;
    }

    protected TickListener tickListener;
    protected boolean repeat;
    protected boolean enabled = true;
    protected float interval;

    public Timer(float interval, boolean repeat, TickListener tickListener) {
        this.tickListener = tickListener;
        this.repeat = repeat;
        this.interval = interval;
    }

    public float elapsedTime = 0;

    public void act(float delta){
        if (!enabled) return;
        elapsedTime += delta;
        if (elapsedTime >= interval){
            if (tickListener !=null){
                float correction = elapsedTime-interval;
                elapsedTime = correction;
                if (!repeat) enabled = false;
                tickListener.Tick(correction);
            }
        }
    }

    public void start(){
        enabled = true;
        elapsedTime = 0;
    }

    public void stop(){
        enabled = false;
        elapsedTime = 0;
    }
}
