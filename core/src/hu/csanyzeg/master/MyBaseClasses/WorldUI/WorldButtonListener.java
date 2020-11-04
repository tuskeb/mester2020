package hu.csanyzeg.master.MyBaseClasses.WorldUI;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;

public interface WorldButtonListener {
    /*Akkor hajtódik végre, amikor a teljes label megjelenik.*/
    default void onShow(WorldButton sender, WorldLabel simpleLabel, MyActor background, MyActor backgroundClicked){

    };

    /*Akkor hajtódik végre, amikor a teljes label eltűnik.*/
    default void onHide(WorldButton sender, WorldLabel simpleLabel, MyActor background, MyActor backgroundClicked){

    };

    /*Akkor hajtódik végre, amikor a felhasználó rákattint.*/
    default void onClick(WorldButton sender, WorldLabel simpleLabel, MyActor background, MyActor backgroundClicked){

    };
}
