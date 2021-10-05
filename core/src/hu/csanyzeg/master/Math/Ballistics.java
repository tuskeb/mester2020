package hu.csanyzeg.master.Math;

/**
 * Created by Majzer on 22/10/2017.
 */

public class Ballistics {


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



    float x, y, v0;
    float g = 9.81f;

    public void setV0(float v0) {
        this.v0 = v0;
    }

    public float getV0() {
        return v0;
    }

    public float[] getDeg(){
        return getAnglesByDeg();
    }



    /**
     * A haítás szögeit adja meg, amellyel el lehet találni az x és y koordinátát
     * @return
     */
    public float[] getAnglesByRad() {
        float a0 = (float) Math.atan((v0 * v0 + Math.sqrt(v0 * v0 * v0 * v0 - g * (g * x * x + 2 * y * v0 * v0))) / (g * x));
        float a1 = (float) Math.atan((v0 * v0 - Math.sqrt(v0 * v0 * v0 * v0 - g * (g * x * x + 2 * y * v0 * v0))) / (g * x));
        //float a0 = (float)Math.atan(x/y + Math.sqrt(y*y/x*x+1))
        return new float[]{a0, a1};
    }

    /**
     * A haítás szögeit adja meg, amellyel el lehet találni az x és y koordinátát
     * @return
     */
    public float[] getAnglesByDeg() {
        float f[] = getAnglesByRad();
        //float a0 = (float)Math.atan(x/y + Math.sqrt(y*y/x*x+1))
        return new float[]{f[0] * radiansToDegrees, f[1] * radiansToDegrees};
    }

    /**
     * Megadja, hogy egy bizonyos időpontban hol jár
     * @param elapsedtime
     * @param indexOfAngles
     * @return x és y koordináták
     */
    public float[] getXYbyTime(float elapsedtime, int indexOfAngles){
        return new float[]{
                v0*elapsedtime*((float)Math.cos(getAnglesByRad()[indexOfAngles])), //x
                v0*elapsedtime*((float)Math.sin(getAnglesByRad()[indexOfAngles]))-1f/2f*g*elapsedtime*elapsedtime
        };
    }

    /**
     * Megadja, hogy mikor esik le a földre
     * @param indexOfAngles Mivel két megoldása lehet a feladatnak, ki kell választani, hogy melyik legyen.
     * @return
     */
    public float getTimeOfFlight(int indexOfAngles){
        return (2*v0*(float)Math.sin(getAnglesByRad()[indexOfAngles]))/(g);
    }


    /**
     *
     * @param x Az a pont, amelyet a ferdehajítás metszeni fog
     * @param y Az a pont, amelyet a ferdehajítás metszeni fog
     * @param v0 A hajítás sebessége
     * @throws Exception Ha nem lehet elérni a sebességgel a pontot.
     */
    public Ballistics(float x, float y, float v0) throws Exception {
        this.x = x;
        this.y = y;
        this.v0 = v0;
        System.out.println(getXYbyTime(0,0)[0]);

        if (Float.isNaN(getXYbyTime(0,0)[0])){
            throw new Exception("Nem lehet ekkorát dobni.");
        }
    }

    public void testFlight(int indexOfAngles){
        float timeoffl = getTimeOfFlight(indexOfAngles);
        for(float t=0; t<=timeoffl; t+=1f/60f){
            float[] pos = getXYbyTime(t,indexOfAngles);
            System.out.println("X=" + pos[0]+" Y="  + pos[1]);
        }
    }

}
