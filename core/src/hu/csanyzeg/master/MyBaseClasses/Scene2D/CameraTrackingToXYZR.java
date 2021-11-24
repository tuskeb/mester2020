package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CameraTrackingToXYZR extends CameraTracking{

    public static float CAMERAINVALID = Float.POSITIVE_INFINITY;

    private float cameraTargetX = CAMERAINVALID;
    private float cameraTargetY = CAMERAINVALID;
    private float cameraTargetZoom = CAMERAINVALID;
    private float cameraMoveSpeed = 20f;
    private float cameraZoomSpeed = 0.2f;

    public CameraTrackingToXYZR() {
    }

    public CameraTrackingToXYZR(Stage stage) {
        super(stage);
    }

    public float getCameraMoveToX() {
        return cameraTargetX;
    }

    public void setCameraMoveToX(float cameraTargetX) {
        this.cameraTargetX = cameraTargetX;
    }

    public float getCameraMoveToY() {
        return cameraTargetY;
    }

    public void setCameraMoveToY(float cameraTargetY) {
        this.cameraTargetY = cameraTargetY;
    }

    public float getCameraMoveToZoom() {
        return cameraTargetZoom;
    }

    public void setCameraMoveToZoom(float cameraTargetZoom) {
        this.cameraTargetZoom = cameraTargetZoom;
    }

    public float getCameraMoveSpeed() {
        return cameraMoveSpeed;
    }

    public void setCameraMoveSpeed(float cameraMoveSpeed) {
        this.cameraMoveSpeed = cameraMoveSpeed;
    }

    public float getCameraZoomSpeed() {
        return cameraZoomSpeed;
    }

    public void setCameraZoomSpeed(float cameraZoomSpeed) {
        this.cameraZoomSpeed = cameraZoomSpeed;
    }

    public void setCameraMoveToXY(float x, float y)
    {
        cameraTargetX = x;
        cameraTargetY = y;
    }


    public void setCameraMoveToXY(float x, float y, float zoom)
    {
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
    }

    @Deprecated
    public void setCameraMoveToXY(float x, float y, float zoom, float speed)
    {
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
        cameraMoveSpeed = speed;
        cameraZoomSpeed = speed;
    }

    @Deprecated
    public void setCameraMoveToXY(float x, float y, float zoom, float zoomSpeed, float moveSpeed)
    {
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
        cameraMoveSpeed = moveSpeed;
        cameraZoomSpeed = zoomSpeed;
    }

    public void setCameraZoomXY(float x, float y, float zoom)
    {
        OrthographicCamera c = orthographicCamera;
        c.zoom=zoom;
        c.position.set(x,y,0);
        cameraTargetX = x;
        cameraTargetY = y;
        cameraTargetZoom = zoom;
        c.update();
    }

    @Override
    public void act(float delta) {

        OrthographicCamera c = orthographicCamera;
        if (cameraTargetX!=c.position.x || cameraTargetY!=c.position.y || cameraTargetZoom!=c.zoom){
            if (cameraTargetX != CAMERAINVALID && cameraTargetY != CAMERAINVALID) {
                if (Math.abs(c.position.x - cameraTargetX) < cameraMoveSpeed * delta) {
                    c.position.x = (c.position.x + cameraTargetX) / 2f;
                } else {
                    if (c.position.x < cameraTargetX) {
                        c.position.x += cameraMoveSpeed * delta;
                    } else {
                        c.position.x -= cameraMoveSpeed * delta;
                    }
                }

                if (Math.abs(c.position.y - cameraTargetY) < cameraMoveSpeed * delta) {
                    c.position.y = (c.position.y + cameraTargetY) / 2f;
                } else {
                    if (c.position.y < cameraTargetY) {
                        c.position.y += cameraMoveSpeed * delta;
                    } else {
                        c.position.y -= cameraMoveSpeed * delta;
                    }
                }
                c.update();
            }
            if (cameraTargetZoom != CAMERAINVALID) {
                System.out.println(cameraTargetZoom == CAMERAINVALID);
                if (Math.abs(c.zoom - cameraTargetZoom) < cameraZoomSpeed * delta) {
                    c.zoom = (c.zoom + cameraTargetZoom) / 2f;
                } else {
                    if (c.zoom < cameraTargetZoom) {
                        c.zoom += cameraZoomSpeed * delta;
                    } else {
                        c.zoom -= cameraZoomSpeed * delta;
                    }
                }
                c.update();
            }
        }
    }
}
