package hu.csanyzeg.master.Math;

import com.badlogic.gdx.math.Vector2;

/**
 * https://hu.wikipedia.org/wiki/Ferde_haj%C3%ADt%C3%A1s
 * <p>
 * A kezdősebesség és a hajítás szöge alapján kiszámolja a röppályát egy megadott pontból kiindulva.
 */
public class Ballistics2 {
    protected float v0, x0 = 0, y0 = 0, a0;
    protected float g = 9.81f;

    /**
     * @param v0 Kezdősebesség
     * @param a0 Hajítás szövege (kezdőszög), radiánban
     */
    public Ballistics2(float v0, float a0) {
        this.v0 = v0;
        this.a0 = a0;
    }

    /**
     * @param v0      Kezdősebesség
     * @param a0      Hajítás szövege (kezdőszög), radiánban
     * @param offsetX Kezdőkoordináta
     * @param offsetY Kezdőkoordináta
     */
    public Ballistics2(float v0, float a0, float offsetX, float offsetY) {
        this.v0 = v0;
        this.x0 = offsetX;
        this.y0 = offsetY;
        this.a0 = a0;
    }

    /**
     * Megadja, hogy az x (d + x0) távolságon milyen magasan van (y + y0)
     *
     * @param x
     * @return
     */
    protected float getY(float x) {
        x -= x0;
        return (float) ((x) * Math.tan(a0) - (g / (2 * v0 * v0 * Math.cos(a0) * Math.cos(a0))) * (x) * (x)) + y0;
    }

    /**
     * Megadja, hogy a ferdehajítás milyen távolságban x (d + x0) éri el a megadott y (h + x0) magasságot.
     * A tömb 0. eleme az időben korábbi, az 1. eleme pedig a későbbi megoldás.
     *
     * @param y
     * @return
     */
    protected float[] getX(float y) {
        y -= y0;
        return new float[]
                {
                        (float) (((v0 * Math.cos(a0)) / g) * (v0 * Math.sin(a0) - Math.sqrt(Math.pow(v0 * Math.sin(a0), 2.0) + 2 * g * -y))) + x0,
                        (float) (((v0 * Math.cos(a0)) / g) * (v0 * Math.sin(a0) + Math.sqrt(Math.pow(v0 * Math.sin(a0), 2.0) + 2 * g * -y))) + x0
                };
    }

    /**
     * Megadja, hogy mekkora táwolságon éri el a hajítás a kezdőmagasságot (x)
     *
     * @return
     */
    public float getDistance() {
        return (float) (v0 * v0 * Math.sin(2 * a0)) / g;
    }

    /**
     * Szimulációhoz használható, megadja az eltelt idő függvényében a pozíciót.
     *
     * @param elapsedTime
     * @return
     */
/*    public Vector2 getPosition(float elapsedTime) {
        return new Vector2(elapsedTime , getY(elapsedTime));
    }
*/
    /**
     * Megadja, hogy egy bizonyos időpontban hol jár
     *
     * @param elapsedtime
     * @return x és y koordináták
     */
    public Vector2 getPosition(float elapsedtime) {
        return new Vector2(
                v0 * elapsedtime * ((float) Math.cos(a0)) + x0,
                v0 * elapsedtime * ((float) Math.sin(a0)) - 1f / 2f * g * elapsedtime * elapsedtime + y0
        );
    }

    /**
     * Az eltelt időt adja vissza az x pozíció függvényében
     * @param x A vízzintes pozíció
     * @return A pozícióig eltelt idő
     */
    public float getElapsedTimeFromX(float x){
        return  1.0f / (v0  * ((float) Math.cos(a0))) * (x - x0);
    }


    /**
     * Az eltelt időt adja vissza az x pozíció függvényében
     * @param x A vízzintes pozíció
     * @return A pozícióig eltelt idő
     */
    public float[] getElapsedTimeFromY(float y){
        return new float[]{
                getElapsedTimeFromX(getX(y)[0]),
                getElapsedTimeFromX(getX(y)[1])
        };
    }


    /**
     * Megmutatja a repülés útvonalát egy magasságtól (y) amíg el nem éri ugyan ezt a magasságot. Ha a magasság kisebb, mint a kiinduló magasság (y0), akkor negatív időkkel is számol.
     * Természetesen a pálya számolható még ez előtt is (negatív idő) és ez után is, de akkor az itt beírt pozíció alá esik a test.
     * @param y az a magasság, amelyet érint a ferde hajítás
     * @param fps A számolás részletessége
     */
    public void testFlight(float y, float fps) {
        for (float t = getElapsedTimeFromX(getX(y)[0]); getPosition(t).x <= getX(y)[1]; t += 1f / fps) {
            System.out.println(String.format("%.3f sec: \tx = %.3f m,\ty = %.3f m", t, getPosition(t).x, getPosition(t).y));
        }
    }

    @Override
    public String toString() {
        return "Ballistics2 {" +
                "\nkezdősebesség v0 = " + v0 +
                ",\nkiinduló pont x0 = " + x0 +
                ",\nkiinduló pont y0 = " + y0 +
                ",\nkiinduló szög a0 = " + a0 +
                ",\ngravitációs gyorsulás g = " + g +
                "\n}";
    }
}
