package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import static java.lang.Math.max;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.SnapshotArray;

public class CameraTrackingToActors extends CameraTracking {
    public SnapshotArray<Actor> actors = new SnapshotArray<>();
    public float marginLeft = 0.2f;
    public float marginBottom = 0.2f;
    public float marginRight = 0.2f;
    public float marginTop = 0.2f;
    public float zoomMin = 1f;
    public float zoomSpeed = 0.05f;
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
        float right = Float.MIN_VALUE;
        float left = Float.MAX_VALUE;
        float top = Float.MIN_VALUE;
        float bottom = Float.MAX_VALUE;

        if (actors.size > 0) {
            for (Actor a : actors) {
                if (a.getX() + a.getWidth()  > right) {
                    right = a.getX() + a.getWidth();
                }
                if (a.getX() < left) {
                    left = a.getX();
                }
                if (a.getY() + a.getHeight()  > top) {
                    top = a.getY() + a.getHeight();
                }
                if (a.getY() < bottom) {
                    bottom = a.getY();
                }
            }
        }
        left -= stage.getViewport().getWorldWidth() * marginLeft * orthographicCamera.zoom;
        right += stage.getViewport().getWorldWidth() * marginRight * orthographicCamera.zoom;
        top += stage.getViewport().getWorldHeight() * marginTop * orthographicCamera.zoom;
        bottom -= stage.getViewport().getWorldHeight() * marginBottom * orthographicCamera.zoom;
        //System.out.println(" left: " + left + "  top: " + top + "  right: " + right + "  bottom: " + bottom);
        float targetZoom = max(zoomMin, max((right - left) / stage.getViewport().getWorldWidth(), (top - bottom) / stage.getViewport().getWorldHeight()));
        orthographicCamera.zoom -= (orthographicCamera.zoom - targetZoom) * zoomSpeed * delta_time * 60;
        float targetX = left + stage.getViewport().getWorldWidth() / 2 * orthographicCamera.zoom;
        orthographicCamera.position.x -= (orthographicCamera.position.x - targetX) * moveSpeed * delta_time * 60;
        float targetY = bottom  + stage.getViewport().getWorldHeight() / 2 * orthographicCamera.zoom;
        orthographicCamera.position.y -= (orthographicCamera.position.y - targetY) * moveSpeed * delta_time * 60;

        //orthographicCamera.update();
    }
}
