package hu.csanyzeg.master.MyBaseClasses.Scene2D;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;

public abstract class CameraTracking{

    public Stage stage;
    public OrthographicCamera orthographicCamera;

    public CameraTracking() {
    }

    public CameraTracking(Stage stage) {
        this.stage = stage;
        this.orthographicCamera = (OrthographicCamera) stage.getCamera();
    }

    public void setStage(Stage stage){
        this.stage = stage;
        this.orthographicCamera = (OrthographicCamera) stage.getCamera();
    }

    public abstract void act(float delta_time);
}
