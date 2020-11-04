package hu.csanyzeg.master.MyBaseClasses.WorldUI;

import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;

public interface WorldMultiLineTextListener {
    /*Akkor hajtódik végre, amikor a teljes label megjelenik.*/
    default void onShow(WorldMultiLineText sender, Array<WorldLabel> simpleLabels, MyActor background){

    };

    /*Akkor hajtódik végre, amikor a teljes label eltűnik.*/
    default void onHide(WorldMultiLineText sender, Array<WorldLabel> simpleLabels, MyActor background){

    };

    /*Akkor hajtódik végre, amikor egy sor megjelenik.*/
    default void onRowAdd(WorldMultiLineText sender, WorldLabel simpleLabel, MyActor background, int index){

    };


}
