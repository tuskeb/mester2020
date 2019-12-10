package hu.csanyzeg.master.MyBaseClasses.Assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetErrorListener;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import java.util.Map;

import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyScreen;

public class MyAssetManager {

    public interface DebugChangeListener{
        public void change(String info);
    }

    public interface ProgressChangeListener {
        public void change(int percent);
    }


    protected DebugChangeListener DebugChangeListener = null;
    protected ProgressChangeListener progressChangeListener = null;

    private AssetManager assetManager = new AssetManager();


    private String Debug = "null";
    private int progress = 0;

    AssetList assetList = new AssetList();

    public MyAssetManager() {
        FileHandleResolver resolver = new InternalFileHandleResolver();
        assetManager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        assetManager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));

        DebugChangeListener = new DebugChangeListener() {
            @Override
            public void change(String info) {
                Gdx.app.log("Asset", "MyAssetManager: " + info.replace('\n', ' '));
            }
        };
    }

    public void changeScreen(MyScreen to){
        setDebug("Loading...");
        if (progressChangeListener != null){
            progressChangeListener.change(0);
        }


        for(Map.Entry<String, AssetDescriptor> e : to.getAssetList().getMap().entrySet()){
            assetManager.load(e.getValue());
            assetList.getMap().put(e.getKey(), e.getValue());
            setDebug("Queue: " + e.getKey());
        }

        while (!assetManager.isFinished()){
            setDebug("Load: " + assetManager.getProgress() + " % (" + assetManager.getDiagnostics() + ")");
            if (progress != (int) assetManager.getProgress()) {
                progress = (int) assetManager.getProgress();
                if (progressChangeListener != null) {
                    progressChangeListener.change((int)assetManager.getProgress());
                }
            }
            assetManager.update();
        }

        setDebug("Load finished.");
        if (progressChangeListener != null){
            progressChangeListener.change(100);
        }
    }

    public Texture getTexture(String hash){
        return assetManager.get((AssetDescriptor<Texture>)(assetList.getAssetDescriptor(hash)));
    }

    public TextureAtlas getTextureAtlas(String hash){
        return assetManager.get((AssetDescriptor<TextureAtlas>)(assetList.getAssetDescriptor(hash)));
    }

    public Sound getSound(String hash){
        return assetManager.get((AssetDescriptor<Sound>)(assetList.getAssetDescriptor(hash)));
    }

    public BitmapFont getFont(String hash){
        return assetManager.get((AssetDescriptor<BitmapFont>)(assetList.getAssetDescriptor(hash)));
    }

    public String getDebug() {
        return Debug;
    }

    public DebugChangeListener getDebugChangeListener() {
        return DebugChangeListener;
    }

    public void setDebugChangeListener(DebugChangeListener DebugChangeListener) {
        this.DebugChangeListener = DebugChangeListener;
    }

    public void setDebug(String Debug) {
        if (Debug.compareTo(this.Debug) != 0){
            this.Debug = Debug;
            if (DebugChangeListener!=null){
                DebugChangeListener.change(Debug);
            }
        }
    }

    public void dispose(){
        assetManager.dispose();
        assetList.getMap().clear();
        setDebug("Dispose");
    }
}
