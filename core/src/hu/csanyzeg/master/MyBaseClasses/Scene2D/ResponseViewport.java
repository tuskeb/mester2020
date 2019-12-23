package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

public class ResponseViewport extends ScalingViewport {
    /**
     * @author hdani1337
     *
     * Ez a Viewport a FitViewport alapján jött létre
     * A ResponseViewport alkalmazkodik a telefon kijelzőjéhez
     * */

    /**
     * @param worldHeight Csak a stage magasságát kell átadni a ResponseViewportnak
     * */
    public ResponseViewport(float worldHeight) {
        super(Scaling.fit, width(worldHeight), worldHeight);
    }

    /**
     * @param worldHeight A stage magassága
     * @param camera A használandó kamera a stagehez
     * */
    public ResponseViewport(float worldHeight, Camera camera) {
        super(Scaling.fit, width(worldHeight), worldHeight, camera);
    }

    /**
     * Visszaadja a stage szélességét
     * @param worldHeight A szélességet ez alapján számolja ki
     * */
    private static float width(float worldHeight)
    {
        float keparany = Gdx.graphics.getWidth() / (Gdx.graphics.getHeight()/1.0f); //A képarány floatban
        float egyArany = worldHeight/9;//Egy arányra eső szélesség (worldHeight) magasságnál ((worldHeight/9)*x)
        int x = 1;//Alap szélességi arány
        while (keparany > (x/9.0f)) x++;//Kiszámolja a telefon szélességi arányát (pl. 16)

        if((int)keparany*(x*egyArany) != Gdx.graphics.getWidth()) return (int)(worldHeight/Gdx.graphics.getHeight() * Gdx.graphics.getWidth());
        //Ha nem pontos a képarány számítása, akkor a stage szélessége legyen a telefon kijelzőjének szélessége (worldHeight) pixelhez viszonyítva

        return x * egyArany;
    }
}
