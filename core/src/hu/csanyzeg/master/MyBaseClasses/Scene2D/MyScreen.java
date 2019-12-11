package hu.csanyzeg.master.MyBaseClasses.Scene2D;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Array;

import java.util.Comparator;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.InitableInterface;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
abstract public class MyScreen implements Screen, InitableInterface {



    public float r=0,g=0,b=0;

    private boolean assetsLoaded = false;

    public final MyGame game;

    protected Array<MyStage> stages = new Array<MyStage>();

    protected InputMultiplexer inputMultiplexer = new InputMultiplexer();

    public MyScreen(MyGame game) {
        this.game = game;
        game.getMyAssetManager().changeAssets(this.getAssetList());
        Gdx.input.setInputProcessor(inputMultiplexer);
        init();
    }

    public void addStage(MyStage stage, int zIndex, boolean processInput){
        stages.add(stage);
        stage.setScreen(this);
        stage.setZIndex(zIndex);
        if (processInput) {
            inputMultiplexer.addProcessor(stage);
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
            afterAssetsLoaded();
        }
        for(MyStage s : stages){
            s.act(delta);
        }
        for(MyStage s : stages){
            s.draw();
        }
    }

    @Override
    public void resize(int width, int height) {
        for(MyStage s : stages){
            s.resize(width, height);
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


    public abstract AssetList getAssetList();

    protected abstract void afterAssetsLoaded();

}
