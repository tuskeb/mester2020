package hu.csanyzeg.master.Demos.GameOfLife;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;

public class LifeStage extends SimpleWorldStage {

    protected boolean focusChanged = false;
    protected LifeActor onFocusActor = null;

    public void setLifeActorFocus(LifeActor lifeActor){
        for (Actor a : getActors()){
            if (a instanceof LifeActor){
                ((LifeActor) a).setFocus(false);
            }
        }
        lifeActor.setFocus(true);
        onFocusActor = lifeActor;
        focusChanged = true;
    }

    public void setSelectedLifeActorTarget(float x, float y){
        if (!focusChanged) {
            if (onFocusActor != null){
                onFocusActor.moveto(x, y, true);
            }
        }
    }

    public LifeStage(MyGame game) {
        super(new ResponseViewport(1080), game);
        for (int i = 0; i<30; i++) {
            LifeActor l = new LifeActor(game, getWorld(), 96, true);
            addActor(l);
            l.addListener(new ClickListener(){
                @Override
                public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                    setLifeActorFocus(l);
                    return super.touchDown(event, x, y, pointer, button);
                }

                @Override
                public void touchDragged(InputEvent event, float x, float y, int pointer) {
                    setLifeActorFocus(l);
                    super.touchDragged(event, x, y, pointer);
                }
            });
        }


        addListener(new ClickListener(){

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                setSelectedLifeActorTarget(x,y);
                return super.touchDown(event, x, y, pointer, button);
            }

            @Override
            public void touchDragged(InputEvent event, float x, float y, int pointer) {
                setSelectedLifeActorTarget(x,y);
                super.touchDragged(event, x, y, pointer);
            }
        });
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        focusChanged = false;
    }
}
