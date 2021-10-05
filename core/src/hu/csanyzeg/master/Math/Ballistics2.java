package hu.csanyzeg.master.Math;

import com.badlogic.gdx.math.Vector2;

public class Ballistics2 {
    float v0, x0 = 0, y0 = 0, a0;
    final static float g = 9.81f;


    /**
     *
     * @param v0 Kezdősebesség
     * @param a0 Hajítás szövege (kezdőszög), radiánban
     */
    public Ballistics2(float v0, float a0) {
        this.v0 = v0;
        this.a0 = a0;
    }

    /**
     *
     * @param v0 Kezdősebesség
     * @param x0 Kezdőkoordináta
     * @param y0 Kezdőkoordináta
     * @param a0 Hajítás szövege (kezdőszög), radiánban
     */
    public Ballistics2(float v0, float a0, float x0, float y0) {
        this.v0 = v0;
        this.x0 = x0;
        this.y0 = y0;
        this.a0 = a0;
    }

    protected float y(float x) {
        return (float)((x) * Math.tan(a0) - (g / (2 * v0 * v0 * Math.cos(a0) * Math.cos(a0))) * (x) * (x));
    }

    /**
     * A hajítás időtartama
     * @return
     */
    public float get_end(){
        return (float)(v0*v0*Math.sin(2*a0)) / g;
    }

    public Vector2 get_position(float elapsedTime){
        return new Vector2(elapsedTime + x0, y(elapsedTime) + y0);
    }
}
