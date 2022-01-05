package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import static java.lang.Math.max;
import static java.lang.Math.min;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;

import jdk.internal.org.jline.keymap.KeyMap;

public class CameraTrackingToActors extends CameraTracking {
    public SnapshotArray<Actor> actors = new SnapshotArray<>();
    public float marginLeft = 0.2f;
    public float marginBottom = 0.15f;
    public float marginRight = 0.2f;
    public float marginTop = 0.15f;
    public float zoomMin = 1f;
    public float zoomSpeed = 0.1f;
    public float moveSpeed = 0.05f;

    public CameraTrackingToActors() {
    }

    public CameraTrackingToActors(Stage stage) {
        super(stage);
    }

    public int getActorCount() {
        return actors.size;
    }

    public void addActor(Actor a) {
        actors.add(a);
    }

    public void removeActor(Actor a) {
        actors.removeValue(a, true);
    }

    @Override
    public void act(float delta_time) {

        float right = -Float.MAX_VALUE;
        float left = Float.MAX_VALUE;
        float top = -Float.MAX_VALUE;
        float bottom = Float.MAX_VALUE;
        if (actors.size > 0) {
            for (Actor a : actors) {
                if (a.getX() + a.getWidth() > right) {
                    right = a.getX() + a.getWidth();
                    //System.out.println("aaaaaaa");
                }
                if (a.getX() < left) {
                    left = a.getX();
                }
                if (a.getY() + a.getHeight() > top) {
                    top = a.getY() + a.getHeight();
                }
                if (a.getY() < bottom) {
                    bottom = a.getY();
                }
            }

            float wz;
            float hz;
            double z =
                    max(
                            zoomMin,
                            max(
                                    wz = (right - left) / (stage.getViewport().getWorldWidth() - stage.getViewport().getWorldWidth() * marginRight - stage.getViewport().getWorldWidth() * marginLeft),
                                    hz = (top - bottom) / (stage.getViewport().getWorldHeight() - stage.getViewport().getWorldHeight() * marginTop - stage.getViewport().getWorldHeight() * marginBottom)
                            )
                    );
            //System.out.println(wz + " ----- " + hz);
            orthographicCamera.zoom += (z - orthographicCamera.zoom) * zoomSpeed * delta_time * 60.0;
            //z = 1;


            double r = orthographicCamera.position.x + stage.getViewport().getWorldWidth() * z / 2.0 - stage.getViewport().getWorldWidth() * marginRight * z - right;
            double l = orthographicCamera.position.x - stage.getViewport().getWorldWidth() * z / 2.0 + stage.getViewport().getWorldWidth() * marginLeft * z - left;

            double b = orthographicCamera.position.y - stage.getViewport().getWorldHeight() * z / 2.0 + stage.getViewport().getWorldHeight() * marginBottom * z - bottom;
            double t = orthographicCamera.position.y + stage.getViewport().getWorldHeight() * z / 2.0 - stage.getViewport().getWorldHeight() * marginTop * z - top;
            //System.out.println(t);
            //System.out.println(top);
            //delta_time = 0.03f;
            //System.out.println(delta_time);
            if (0.0 > r) {
                orthographicCamera.position.x -= r * moveSpeed * delta_time * 60.0;
            }
            if (0.0 < l) {
                orthographicCamera.position.x -= l * moveSpeed * delta_time * 60.0;
            }
            if (0.0 < b) {
                orthographicCamera.position.y -= b * moveSpeed * delta_time * 60.0;
            }
            if (0.0 > t) {
                orthographicCamera.position.y -= t * moveSpeed * delta_time * 60.0;
            }

            //System.out.println(wz + " " + hz);

/*        float l = left - orthographicCamera.position.x - stage.getViewport().getWorldWidth() * marginLeft;
        if (l < stage.getViewport().getWorldWidth() / 2) {
            orthographicCamera.position.x += l * moveSpeed * delta_time * 60;
        }

        float r = right - orthographicCamera.position.x - stage.getViewport().getWorldWidth() * marginRight;
        if (r > stage.getViewport().getWorldWidth() / 2) {
            orthographicCamera.position.x += r * moveSpeed * delta_time * 60;
        }
        float t = top - orthographicCamera.position.y - stage.getViewport().getWorldHeight() * marginTop;
        if (t < stage.getViewport().getWorldHeight() / 2) {
            orthographicCamera.position.y += t * moveSpeed * delta_time * 60;
        }
        float b = bottom - orthographicCamera.position.y - stage.getViewport().getWorldWidth() * marginBottom;
        if (b > stage.getViewport().getWorldHeight() / 2) {
            orthographicCamera.position.y += b * moveSpeed * delta_time * 60;
        }*/
//        orthographicCamera.position.x = stage.getViewport().getWorldWidth() / 2;
//        orthographicCamera.position.y = stage.getViewport().getWorldHeight() / 2;
            //System.out.println(orthographicCamera.position.x + " - " + orthographicCamera.position.y);
            //left -= stage.getViewport().getWorldWidth() * marginLeft * orthographicCamera.zoom;
            //right += stage.getViewport().getWorldWidth() * marginRight * orthographicCamera.zoom;
            //top += stage.getViewport().getWorldHeight() * marginTop * orthographicCamera.zoom;
            //bottom -= stage.getViewport().getWorldHeight() * marginBottom * orthographicCamera.zoom;
            //System.out.println(" left: " + left + "  top: " + top + "  right: " + right + "  bottom: " + bottom);
            //float targetZoom = max(zoomMin, max((right - left) / stage.getViewport().getWorldWidth(), (top - bottom) / stage.getViewport().getWorldHeight()));
            //orthographicCamera.zoom -= (orthographicCamera.zoom - targetZoom) * zoomSpeed * delta_time * 60;
            //float targetX = left + stage.getViewport().getWorldWidth() / 2 * orthographicCamera.zoom;
            //orthographicCamera.position.x -= (orthographicCamera.position.x - targetX) * moveSpeed * delta_time * 60;
            //float targetY = bottom  + stage.getViewport().getWorldHeight() / 2 * orthographicCamera.zoom;
            //orthographicCamera.position.y -= (orthographicCamera.position.y - targetY) * moveSpeed * delta_time * 60;


            //orthographicCamera.update();
        }
    }
}
