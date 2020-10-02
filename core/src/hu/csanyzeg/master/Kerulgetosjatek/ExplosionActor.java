package hu.csanyzeg.master.Kerulgetosjatek;

import com.badlogic.gdx.scenes.scene2d.Actor;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;

public class ExplosionActor extends OneSpriteAnimatedActor {
    public ExplosionActor(MyGame game, Actor other) {
        super(game, "spaceinvaders/explosion.atlas");
        copyFrom(other);
    }

    @Override
    protected void repeated() {
        super.repeated();
        getStage().getActors().removeValue(this, true);
    }
}
