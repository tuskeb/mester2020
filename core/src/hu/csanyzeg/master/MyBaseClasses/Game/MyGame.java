package hu.csanyzeg.master.MyBaseClasses.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingStage;
import hu.csanyzeg.master.MyBaseClasses.Assets.MyAssetManager;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

import java.lang.reflect.InvocationTargetException;
import java.util.Stack;

/**
 * Created by tanulo on 2017. 10. 06..
 */

abstract public class MyGame extends Game {

    private LoadingStage loadingStage = null;
    private MyAssetManager myAssetManager;
    public boolean debug;

    public MyGame(boolean debug) {
        this.debug = debug;
    }

    public MyGame() {
        debug = false;
    }

    @Override
    public void create() {
        myAssetManager = new MyAssetManager();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setCatchMenuKey(true);
    }


    public final Stack<Class> backButtonStack = new Stack();

    @Override
    public void setScreen(Screen screen) {
        setScreen(screen,true);
    }

    public void setScreenBackByStackPop(){
        setScreenBackByStackPop(null);
    }

    public interface ScreenInit{
        public void init(MyScreen scr);
    }

    public void setScreenBackByStackPop(ScreenInit init){
        if (backButtonStack.size()>0){
            try {
                MyScreen scr = (MyScreen) backButtonStack.pop().getConstructor(MyGame.class).newInstance(this);
                if (init != null) {
                    init.init(scr);
                }
                setScreen(scr,false);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        else
        {
            Gdx.app.exit();
        }
    }


    public void setScreen(Screen screen, boolean pushToStack) {
        Screen prevScreen = getScreen();
        if (prevScreen!=null) {
            if (pushToStack) {backButtonStack.push(prevScreen.getClass());}
            prevScreen.dispose();
        }
        super.setScreen(screen);
    }

    public MyAssetManager getMyAssetManager() {
        return myAssetManager;
    }
    public LoadingStage getLoadingStage() {
        return loadingStage;
    }

    public void setLoadingStage(LoadingStage loadingStage) {
        this.loadingStage = loadingStage;
    }


    @Override
    public void dispose() {
        myAssetManager.dispose();
    }
}
