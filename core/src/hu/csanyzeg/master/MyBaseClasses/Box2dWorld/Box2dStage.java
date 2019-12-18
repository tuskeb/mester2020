package hu.csanyzeg.master.MyBaseClasses.Box2dWorld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

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

    @Override
    public void init() {
        super.init();
        if (game.debug){
            addTimer(new TickTimer(1.017f, true, new TickTimerListener() {
                @Override
                public void onRepeat(TickTimer sender) {
                    Gdx.app.log("world", "DT stage: " + (lastDelta * 1000f) +" ms; \tDT world: " + (worldDelta*1000f) + " ms. \tET real: " + realElapsedTime + " \tET world & B2Dstage: " + elapsedTime + " \tWorld iterations per delta: " + iterations + ".\tWorldT-stageT diff: " + (elapsedTime - realElapsedTime)+ " s");
                }

                @Override
                public void onTick(Timer sender, float correction) {

                }

                @Override
                public void onStop(Timer sender) {

                }

                @Override
                public void onStart(Timer sender) {

                }
            }));
        };
    }

    public WorldBodyEditorLoader getLoader() {
        return loader;
    }

    public void setLoader(String filename) {
        this.loader = new WorldBodyEditorLoader(filename);
    }

    private long lastWorldMs = 0;

    protected float worldDelta = 0.015f;
    protected float realElapsedTime = 0;
    protected float lastDelta;
    protected int iterations = 1;
    protected float minFps = 15f;
    protected float iterationPerSec = 666f;

    public float getMinFps() {
        return minFps;
    }

    public void setMinFps(float minFps) {
        this.minFps = minFps;
    }

    public float getIterationPerSec() {
        return iterationPerSec;
    }

    public void setIterationPerSec(float iterationPerSec) {
        this.iterationPerSec = iterationPerSec;
    }

    @Override
    public void act(float delta) {
        float fps = 1f / delta;
        float delta2;
        long m = TimeUtils.millis();
        if (fps < minFps){
            if (worldDelta > 0.001) {
                worldDelta -= 0.001;
            }
            delta2 = worldDelta;
        }else{
            worldDelta = delta;
            delta2 = delta;
        }
        iterations = 1 + (int)(delta2*iterationPerSec);
        world.step(delta2, iterations, iterations);
        realElapsedTime += delta;
        lastDelta = delta;
        super.act(delta2);
    }

    @Override
    public void draw() {
        super.draw();
        if(game.debug) box2DDebugRenderer.render(world, getCamera().combined);
    }
}
