package hu.csanyzeg.master.MyBaseClasses.Box2dWorld;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;

public abstract class Box2dStage extends MyStage {

    protected World world;
    protected Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    protected WorldBodyEditorLoader loader;

    public Box2dStage(Viewport viewport, MyGame game, Vector2 gravity) {
        super(viewport, game);
        world  = new World(gravity, false);
    }

    public Box2dStage(Viewport viewport, MyGame game) {
        super(viewport, game);
        world  = new World(new Vector2(0,-9.81f), false);
    }

    public WorldBodyEditorLoader getLoader() {
        return loader;
    }

    public void setLoader(String filename) {
        this.loader = new WorldBodyEditorLoader(filename);
    }

    @Override
    public void act(float delta) {
        world.step(delta,5,5);
        super.act(delta);
    }

    @Override
    public void draw() {
        super.draw();
        if(game.debug) box2DDebugRenderer.render(world, getCamera().combined);
    }
}
