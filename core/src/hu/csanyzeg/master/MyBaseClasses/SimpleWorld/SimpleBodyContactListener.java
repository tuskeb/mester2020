package hu.csanyzeg.master.MyBaseClasses.SimpleWorld;

public abstract class SimpleBodyContactListener {
    public abstract void beginContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper);
    public abstract void endContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper);
}
