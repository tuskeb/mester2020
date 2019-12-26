package hu.csanyzeg.master.MyBaseClasses.SimpleWorld;

public abstract class SimpleBodyBehaviorListener {
    public abstract void onStop(SimpleBody sender);
    public abstract void onStart(SimpleBody sender);
    public abstract void onVelocityChanged(SimpleBody sender);
    public abstract void onRotationChanged(SimpleBody sender);
    public abstract void onSizeChanged(SimpleBody sender);
    public abstract void onContactAdded(SimpleBody sender, SimpleBody other);
    public abstract void onContactRemoved(SimpleBody sender, SimpleBody other);
}
