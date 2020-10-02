package hu.csanyzeg.master.Kerulgetosjatek;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.scenes.scene2d.Stage;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.PermanentTimerListener;

public class WallActor extends OneSpriteStaticActor {
    static RandomXS128 randomXS128 = new RandomXS128();

    public WallActor(MyGame game) {
        super(game, "box2dhelper/wall.png");
        setWidth(randomXS128.nextInt(10) * 5 + 5);
        setHeight(5);

        addTimer(new PermanentTimer(new PermanentTimerListener(){
            @Override
            public void onTick(PermanentTimer sender, float correction) {
                super.onTick(sender, correction);
                setY(getY() - correction * 30);
                if (getY() < - getHeight()){
                    getStage().getActors().removeValue(WallActor.this, true);
                }
            }
        }));
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        setY(stage.getViewport().getWorldHeight());
        setX(randomXS128.nextInt((int)(stage.getViewport().getWorldWidth() - getWidth())) / 5 * 5 );
    }



}
