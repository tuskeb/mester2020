package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public interface SimpleLabelListener {
    /*Akkor hajtódik végre, amikor a teljes label megjelenik.*/
    default void onShow(SimpleLabel sender, Array<SimpleChar> simpleCharArray){

    };

    /*Akkor hajtódik végre, amikor a teljes label eltűnik.*/
    default void onHide(SimpleLabel sender, Array<SimpleChar> simpleCharArray){

    };

    default void onCharAdd(SimpleLabel sender, SimpleChar simpleChar, int index){

    };

    /*A visszatérési értéke true, akkor az osztály leszedi, ha false, akkor a metódusban kell leszedni. Akkor hajtódik végre, amikor egy karakter törlésre kerül. Ez lehet szöveg módosítása és teljes szöveg leszedése is.*/
    default boolean onCharRemove(SimpleLabel sender, SimpleChar simpleChar, int index){
        return true;
    };

    /*A visszatérési értéke true, akkor az osztály leszedi, ha false, akkor a metódusban kell leszedni, vagy ott marad.*/
    default boolean onCharChange(SimpleLabel sender, SimpleChar oldSimpleChar, SimpleChar newSimpleChar){
        return true;
    };

    /*Amikor a változó hosszúságú betűk miatt át kell rendezni a címkét. Ha false a visszatérés, akkor itt kell kezelni, ha true, akkor automatikusan kezeli*/
    default boolean onCharPositonChange(SimpleLabel sender, SimpleChar simpleChar, int index){
        return true;
    }
}
