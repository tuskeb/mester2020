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

import java.util.ArrayList;
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
                Gdx.app.log("Asset", "MyAssetManager: " + info);
            }
        };
    }

    public int getProgress() {
        return progress;
    }

    private void setProgress(int progress) {
        this.progress = progress;
        if (progressChangeListener != null) {
            progressChangeListener.change(progress);
        }
    }

    public boolean isLoadingComplete(){
        return assetManager.isFinished();
    }

    public void changeScreen(MyScreen to){
        setDebug("Loading...");
        setProgress(0);

        if (to.getAssetList() != null) {
            ArrayList<String> remove = new ArrayList<>();


            for (Map.Entry<String, AssetDescriptor> e : assetList.getMap().entrySet()) {
                if (!to.getAssetList().getMap().containsKey(e.getKey())){
                    remove.add(e.getKey());
//                    setDebug("Unused: " + e.getKey());
                }
            }

            for(String s : remove){
                AssetDescriptor assetDescriptor = assetList.getMap().remove(s);
                setDebug("Remove: " + assetDescriptor.fileName);
                assetManager.unload(assetDescriptor.fileName);
            }


            for (Map.Entry<String, AssetDescriptor> e : to.getAssetList().getMap().entrySet()) {
                if (!assetList.getMap().containsKey(e.getKey())) {
                    assetManager.load(e.getValue());
                    assetList.getMap().put(e.getKey(), e.getValue());
                    setDebug("Queue: " + e.getKey());
                }
            }

            setDebug("!!!! CALL updateManager FROM LOADINGSCREEN WHILE DONE. !!!!");
        }
    }

    public void updateManager(){
        assetManager.update();
        setProgress((int)(assetManager.getProgress()*100));
        setDebug(getProgress() + " % ("+ getActualLoadingName() +")");
    }

    public String getActualLoadingName(){
        String[] s = assetManager.getDiagnostics().split("\n");
        if (s.length == 1){
            return s[0].split(", ")[0];
        }
        return s[1].split(", ")[0];
    }


    public void loadAsset(AssetDescriptor assetDescriptor, String hash){
        if (!assetList.getMap().containsKey(hash)) {
            setDebug("Loading: " + hash);
            assetManager.load(assetDescriptor);
            assetList.add(assetDescriptor, hash);
            while (!assetManager.isFinished()) {
                assetManager.update();
            }
            setDebug("Loading: " + hash + " done.");
        }else{
            setDebug("Loading: " + hash + " already loaded.");
        }
    }

    public void loadAsset(AssetDescriptor assetDescriptor){
        loadAsset(assetDescriptor, assetDescriptor.fileName);
    }

    public void loadAsset(AssetList assetList) {
        for (Map.Entry<String, AssetDescriptor> e : assetList.getMap().entrySet()) {
            loadAsset(e.getValue(), e.getKey());
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
        System.out.println(assetManager.get((AssetDescriptor<BitmapFont>)(assetList.getAssetDescriptor(hash))));
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
