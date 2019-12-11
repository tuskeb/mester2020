package hu.csanyzeg.master.MyBaseClasses.Assets;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

import java.util.HashMap;


public class AssetList {
    public static final String CHARS = "0123456789öüóqwertzuiopőúasdfghjkléáűíyxcvbnm'+!%/=()ÖÜÓQWERTZUIOPŐÚASDFGHJKLÉÁŰÍYXCVBNM?:_*<>#&@{}[],-.";

    private HashMap<String, AssetDescriptor> map = new HashMap<String, AssetDescriptor>();

    public void addTexture(String fileName){
        addTexture(fileName, fileName);
    }
    public void addTexture(String fileName, String hash){
        if (!map.containsKey(hash)){
            map.put(hash != null ? hash : fileName, new AssetDescriptor<Texture>(fileName, Texture.class));
        }
    }

    public void addTextureAtlas(String fileName){
        addTextureAtlas(fileName,fileName);
    }
    public void addTextureAtlas(String fileName, String hash){
        if (!map.containsKey(hash)){
            map.put(hash != null ? hash : fileName, new AssetDescriptor<TextureAtlas>(fileName, TextureAtlas.class));
        }
    }

    public void addSound(String fileName) {
        addSound(fileName, fileName);
    }
    public void addSound(String fileName, String hash){
        if (!map.containsKey(hash)){
            map.put(hash != null ? hash : fileName, new AssetDescriptor<Sound>(fileName, Sound.class));
        }
    }

    public void addFont(String fileName, int size, Color color) {
        addFont(fileName, fileName, size, color);
    }

    public void addFont(String fileName, int size) {
        addFont(fileName, fileName, size, Color.WHITE);
    }

    public void addFont(String fileName, String hash, int size, Color color) {
        FreetypeFontLoader.FreeTypeFontLoaderParameter fontParameter = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
        fontParameter.fontFileName = fileName;
        fontParameter.fontParameters.size = size;
        fontParameter.fontParameters.characters = CHARS;
        fontParameter.fontParameters.color = color;

        if (!map.containsKey(hash)) {
            map.put(hash != null ? hash : fileName, new AssetDescriptor<BitmapFont>(fontParameter.fontFileName, BitmapFont.class, fontParameter));
        }
    }

    public AssetDescriptor getAssetDescriptor(String hash){
        return map.get(hash);
    }

    public HashMap<String, AssetDescriptor> getMap() {
        return map;
    }

}
