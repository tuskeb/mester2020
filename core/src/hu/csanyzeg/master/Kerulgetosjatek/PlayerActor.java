package hu.csanyzeg.master.Kerulgetosjatek;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Stage;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class PlayerActor extends OneSpriteStaticActor {
    float targetX;


    public PlayerActor(MyGame game) {
        super(game, "badlogic.jpg");
        setSize(5,5);
        targetX = getX();
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        setPositionCenter();
        moveTo(getX() + getWidth() / 2);
    }

    public void moveTo(float x){
        targetX = x - getWidth() / 2;
        moving = true;
    }

    boolean moving = false;

    @Override
    public void act(float delta) {
        super.act(delta);
        if (moving) {
            if (getX() < targetX) {
                setX(getX() + delta * 50);
            } else {
                setX(getX() - delta * 50);
            }
            if (Math.abs(getX() - targetX) < 1){
                moving = false;
            }
        }
    }
}
