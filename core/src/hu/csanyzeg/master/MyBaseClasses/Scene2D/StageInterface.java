package hu.csanyzeg.master.MyBaseClasses.Scene2D;

public interface StageInterface {

    /**
     * @author hdani1337
     * Ezt az interfészt azért hoztam létre, hogy egy kicsit szebbé és átláthatóbbá varázsolhassuk a kódunkat
     * Célszerű NyStageknél implementálni, s a különböző típusú értékek beállítása külön-külön voidokban történik meg
     * Például egy voidban értékeket adunk a változóknak, a másikban pozícionáljuk, stb, stb, stb...
     * Nem tudom kinek hasznos, de nekem nagyon jól jön, mivel amúgyis minden stageben létrehozom ezeket a voidokat, így pedig csak implementálnom kell ezt
     * **/

    /**
     * ÉRTÉKEK HOZZÁRENDELÉSE A VÁLTOZÓKHOZ ÉS OBJEKTUMOKHOZ
     * **/
    void assignment();

    /**
     * ACTOROK MÉRETEINEK BEÁLLÍTÁSA
     * **/
    void setSizes();

    /**
     * ACTOROK POZÍCIÓINAK BEÁLLÍTÁSA
     * **/
    void setPositions();

    /**
     * LISTENEREK HOZZÁADÁSA AZ ACTOROKHOZ
     * **/
    void addListeners();


    /**
     * ACTOROK Z-INDEXEINEK BEÁLLÍTÁSA
     * **/
    void setZIndexes();

    /**
     * ACTOROK HOZZÁADÁSA A STAGEHEZ
     * **/
    void addActors();
}
