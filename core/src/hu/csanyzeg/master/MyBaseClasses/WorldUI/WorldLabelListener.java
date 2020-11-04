package hu.csanyzeg.master.MyBaseClasses.WorldUI;

import com.badlogic.gdx.utils.Array;

public interface WorldLabelListener {
    /*Akkor hajtódik végre, amikor a teljes label megjelenik.*/
    default void onShow(WorldLabel sender, Array<WorldChar> simpleCharArray){

    };

    /*Akkor hajtódik végre, amikor a teljes label eltűnik.*/
    default void onHide(WorldLabel sender, Array<WorldChar> simpleCharArray){

    };

    default void onCharAdd(WorldLabel sender, WorldChar simpleChar, int index){

    };

    /*A visszatérési értéke true, akkor az osztály leszedi, ha false, akkor a metódusban kell leszedni. Akkor hajtódik végre, amikor egy karakter törlésre kerül. Ez lehet szöveg módosítása és teljes szöveg leszedése is.*/
    default boolean onCharRemove(WorldLabel sender, WorldChar simpleChar, int index){
        return true;
    };

    /*A visszatérési értéke true, akkor az osztály leszedi, ha false, akkor a metódusban kell leszedni, vagy ott marad.*/
    default boolean onCharChange(WorldLabel sender, WorldChar oldSimpleChar, WorldChar newSimpleChar){
        return true;
    };
}
