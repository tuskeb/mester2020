package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public interface SimpleLabelListener {
    public void onShow(SimpleLabel sender, Array<SimpleChar> charArray);
    public void onHide(SimpleLabel sender, Array<SimpleChar> charArray);
    public void onCharAdd(SimpleLabel sender, SimpleChar simpleChar, int index);

    //public void onCharChange(SimpleLabel sender, SimpleBody body, MyGroup group, MyLabel label, int index);
}
