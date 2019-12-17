package hu.csanyzeg.master.MyBaseClasses.Scene2D;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetCollector;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Game.InitableInterface;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyScreen implements Screen, InitableInterface, AssetCollector {



    public float r=0,g=0,b=0;

    private boolean assetsLoaded = false;

    public final MyGame game;

    protected Array<MyStage> stages = new Array<MyStage>();

    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public MyScreen(MyGame game) {
        this.game = game;
        game.getMyAssetManager().changeAssets(this.getAssetList());
        if (game.getLoadingStage()!= null) {
            game.getLoadingStage().show();
        }
        Gdx.input.setInputProcessor(inputMultiplexer);
        init();
    }

    public void addStage(final MyStage stage, int zIndex, boolean processInput){
        stages.add(stage);
        stage.setScreen(this);
        stage.setZIndex(zIndex);
        if (processInput) {
            if (stage.visible && !stage.pause) {
                inputMultiplexer.addProcessor(stage);
            }
            stage.addVisibleChangeListener(new MyStage.VisibleChangeListener() {
                @Override
                public void change(boolean visible) {
                    if (visible){
                        if (!inputMultiplexer.getProcessors().contains(stage,true)) {
                            inputMultiplexer.addProcessor(stage);
                        }
                    }else{
                        inputMultiplexer.removeProcessor(stage);
                    }
                }
            });

            stage.addPauseChangeListener(new MyStage.PauseChangeListener() {
                @Override
                public void change(boolean pause) {
                    if (!pause){
                        if (!inputMultiplexer.getProcessors().contains(stage,true)) {
                            inputMultiplexer.addProcessor(stage);
                        }
                    }else{
                        inputMultiplexer.removeProcessor(stage);
                    }
                }
            });
        }
    }

    public void removeStage(MyStage stage){
        stages.removeValue(stage, true);
        inputMultiplexer.removeProcessor(stage);
    }


    public void sortStagesByZindex(){

        stages.sort(new Comparator<MyStage>() {
            @Override
            public int compare(MyStage actor, MyStage t1) {
                    return actor.zIndex - t1.zIndex;
            }
        });

    }


    @Override
    public void dispose() {
        for(MyStage s : stages){
            s.dispose();
        }
    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(r, g, b, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if(!game.getMyAssetManager().isLoadingComplete()){
            if (game.getLoadingStage()!= null){
                game.getLoadingStage().act(delta);
                game.getLoadingStage().draw();
            }else{
                game.getMyAssetManager().updateManager();
            }
            return;
        }
        if (!assetsLoaded){
            assetsLoaded = true;
            if (game.getLoadingStage() != null) {
                game.getLoadingStage().hide();
            }
            afterAssetsLoaded();
        }
        for(MyStage s : stages){
            if (s.visible && !s.pause) {
                s.act(delta);
            }
        }
        for(MyStage s : stages){
            if (s.visible){
                //s.getBatch().setProjectionMatrix(s.getCamera().projection);
                s.draw();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        if (game.getLoadingStage()!=null){
            game.getLoadingStage().getViewport().update(width,height);
        }
        for(MyStage s : stages){
            s.resize(width, height);
            s.getViewport().update(width, height);
        }
    }

    @Override
    public void resume() {

    }

    @Override
    public void show() {

    }

    public Game getGame() {
        return game;
    }

    public void setBackGroundColor(float r, float g, float b)
    {
        this.r=r;
        this.g = g;
        this.b = b;
    }

    public InputMultiplexer getInputMultiplexer() {
        return inputMultiplexer;
    }


    protected abstract void afterAssetsLoaded();


}
