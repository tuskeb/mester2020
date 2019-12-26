package hu.csanyzeg.master.MyBaseClasses.SimpleWorld;

/**
 * A típus a világ passzív állapotában a body ki és berakásával a helpren keresztül bármikor megváloztatható.
 */
public enum  SimpleBodyType {


    /**
     *  Részt vesz az átfedés éa a mozgás szimulációban egyaránt.
     *  Ütközés esetén az ütközésben részt vevő testek sebessége az átlag eredő lesz.
     *  A testek együtt haladnak tovább. Űjabb ütközésben ugyan azok nem vehetnek részt, amíg el nem hagyják egymást.
     *  Ütközés eseményt vált ki, amennyiben hozzáér egy új tárgyhoz vagy elhagyja azt.
     */
    Dinamic,


    /**
     * Részt vesz az átfedés éa a mozgás szimulációban egyaránt.
     * Ütközés esetén is továbbhalad, a sebességére nincs hatással a másik tárgy.
     * Ütközés eseményt vált ki, amennyiben hozzáér egy új tárgyhoz vagy elhagyja azt.
     **/
    Sensor,



    /**
     *  Csak a mozgásban vesz rész.
     *  Ütközés esetén is továbbhalad, a sebességére nincs hatással a másik tárgy.
     *  Nem vált ki ütközés eseményt.
     */
    Ghost,



    /**
     *  Csak az átfedésben, ütközésben vesz részt. Nem lehet sebessége, nem mozdulhat el.
     *  Ütközés eseményt vált ki, amennyiben hozzáér egy új tárgyhoz vagy elhagyja azt.
     */
    Static,



    /**
     * Nem vesz részt sem átfedés vizsgálatban, sem a mozgásban. Csak a világ része, de a világszámítás nem foglalkozik vele.
     * Nem vált ki eseményt.
     */
    Passive


}
