package hu.csanyzeg.master.Math;

import com.badlogic.gdx.math.Vector2;

/**
 * Egy megadott ponton áthaladó hajítás két lehetséges szögét adja meg.
 */
public class Ballistics1 {


    static public final float PI = 3.1415927f;
    /**
     * multiply by this to convert from radians to degrees
     */
    static public final float radiansToDegrees = 180f / PI;
    static public final float radDeg = radiansToDegrees;
    /**
     * multiply by this to convert from degrees to radians
     */
    static public final float degreesToRadians = PI / 180;
    static public final float degRad = degreesToRadians;


    float x, y, v0, x0 = 0, y0 = 0;
    float g = 9.81f;

    public void setV0(float v0) {
        this.v0 = v0;
    }

    public float getV0() {
        return v0;
    }

    public float[] getDeg() {
        return getAnglesByDeg();
    }

    /**
     * A haítás szögeit adja meg, amellyel el lehet találni az x és y koordinátát
     *
     * @return
     */
    public float[] getAnglesByRad() {
        float a0 = (float) Math.atan((v0 * v0 + Math.sqrt(v0 * v0 * v0 * v0 - g * (g * (x - x0) * (x - x0) + 2 * (y - y0) * v0 * v0))) / (g * (x - x0)));
        float a1 = (float) Math.atan((v0 * v0 - Math.sqrt(v0 * v0 * v0 * v0 - g * (g * (x - x0) * (x - x0) + 2 * (y - y0) * v0 * v0))) / (g * (x - x0)));
        return new float[]{a0, a1};
    }

    /**
     * A haítás szögeit adja meg, amellyel el lehet találni az x és y koordinátát
     *
     * @return
     */
    public float[] getAnglesByDeg() {
        float f[] = getAnglesByRad();
        return new float[]{f[0] * radiansToDegrees, f[1] * radiansToDegrees};
    }

    /**
     * Megadja, hogy egy bizonyos időpontban hol jár
     *
     * @param elapsedtime
     * @param indexOfAngles
     * @return x és y koordináták
     */
    public Vector2 getPosition(float elapsedtime, int indexOfAngles) {
        return new Vector2(
                v0 * elapsedtime * ((float) Math.cos(getAnglesByRad()[indexOfAngles])) + x0,
                v0 * elapsedtime * ((float) Math.sin(getAnglesByRad()[indexOfAngles])) - 1f / 2f * g * elapsedtime * elapsedtime + y0
        );
    }



    /**
     * Megadja, hogy mikor esik le a földre
     *
     * @param indexOfAngles Mivel két megoldása lehet a feladatnak, ki kell választani, hogy melyik legyen.
     * @return
     */
    public float getDistance(int indexOfAngles) {
        return (2 * v0 * (float) Math.sin(getAnglesByRad()[indexOfAngles])) / (g);
    }

    /**
     * @param x  Az a pont, amelyet a ferdehajítás metszeni fog
     * @param y  Az a pont, amelyet a ferdehajítás metszeni fog
     * @param v0 A hajítás sebessége
     * @throws Exception Ha nem lehet elérni a sebességgel a pontot.
     */
    public Ballistics1(float x, float y, float v0) throws Exception {
        this.x = x;
        this.y = y;
        this.v0 = v0;
        if (Float.isNaN(getPosition(0, 0).x)) {
            throw new Exception("Nem lehet ekkorát dobni.");
        }
    }

    /**
     * @param x       Az a pont, amelyet a ferdehajítás metszeni fog
     * @param y       Az a pont, amelyet a ferdehajítás metszeni fog
     * @param v0      A hajítás sebessége
     * @param offsetX A hajítás kezdete
     * @param offsetY A hajítás kezdete
     * @throws Exception Ha nem lehet elérni a sebességgel a pontot.
     */
    public Ballistics1(float x, float y, float v0, float offsetX, float offsetY) throws Exception {
        this.x = x;
        this.y = y;
        this.x0 = offsetX;
        this.y0 = offsetY;
        this.v0 = v0;
        if (Float.isNaN(getPosition(0, 0).x)) {
            throw new Exception("Nem lehet ekkorát dobni.");
        }
    }

    /**
     * Megmutatja a repülés útvonalát a kiinduló ponttól és magasságtól kezdve addig, amíg ugyan azt a magasságot el nem éri.
     * Természetesen a pálya számolható még ez előtt is (nehatív idő) és ez után is, de akkor a kiinduló pozíció alá esik a test.
     * @param indexOfAngles 0,1 a szögnek az indexe, amelyik megoldást választjuk a kettő közül.
     * @param fps A számolás részletessége
     */
    public void testFlight(int indexOfAngles, float fps) {
        float timeoffl = getDistance(indexOfAngles);
        for (float t = 0; t <= timeoffl; t += 1f / fps) {
            System.out.println(String.format("%.3f sec: \tx = %.3f m,\ty = %.3f m", t, getPosition(t, indexOfAngles).x, getPosition(t, indexOfAngles).y));
        }
    }

    @Override
    public String toString() {
        return "Ballistics1 {" +
                "\nérintett pont x = " + x +
                "\nérintett pont y = " + y +
                "\nkezdősebesség v0 = " + v0 +
                "\nkezdőpozíció x0 = " + x0 +
                "\nkezdőpozíció y0 = " + y0 +
                "\ngravitációs állandó g = " + g +
                "\n}";
    }
}
