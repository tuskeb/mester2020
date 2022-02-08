package hu.csanyzeg.master.Demos.GameOfLife;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.Demos.SimpleUI.SimpleUIStage;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;

public class LifeStage extends SimpleWorldStage {

    public LifeStage(MyGame game) {
        super(new ResponseViewport(1080), game);
        for (int i = 0; i<30; i++) {
            LifeActor l = new LifeActor(game, getWorld());
            addActor(l);
            l.randomPos();
        }
        /*
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                System.out.println(x);
                System.out.println(y);
                //((SimpleWorldHelper)l.getActorWorldHelper()).getBody().moveToFixTime(x,y,1, PositionRule.Center);
                l.moveto(x, y);
            }
        });*/
    }

}
