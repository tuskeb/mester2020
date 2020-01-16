package hu.csanyzeg.master.Demos.LoadingStage;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

public class ProgressActor extends MyGroup {
    public static String fontHash = "loadingscreen/normal.ttf";
    public static String zoldHash = "loadingscreen/zold.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addFont(fontHash, fontHash, 20, Color.WHITE, AssetList.CHARS).protect = true;
        assetList.addTexture(zoldHash).protect = true;
    }


    private MyLabel percentLabel;
    private OneSpriteStaticActor percentBg;
    private OneSpriteStaticActor percentFg;

    public void setPercent(int percent){
        percentLabel.setText(percent + " %");
        percentFg.setWidth(getWidth() * percent / 100);
    }

    public ProgressActor(MyGame game) {
        super(game);
        setWidth(256);
        setHeight(25);
        percentBg = new OneSpriteStaticActor(game, zoldHash);
        percentBg.setSize(getWidth(),getHeight());
        percentBg.setAlpha(0.5f);

        percentFg = new OneSpriteStaticActor(game, zoldHash);
        percentFg.setSize(getWidth(), getHeight());

        percentLabel = new  MyLabel(game, "0%", new Label.LabelStyle(game.getMyAssetManager().getFont(fontHash), Color.WHITE)){
            @Override
            public void init() {

            }
        };
        percentLabel.setSize(getWidth(), getHeight());
        percentLabel.setAlignment(Align.center, Align.center);
        setPercent(0);

        addActor(percentBg);
        addActor(percentFg);
        addActor(percentLabel);
    }

    @Override
    protected void sizeChanged() {
        super.sizeChanged();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (percentBg.getElapsedTime() < 0.5){
            percentFg.setAlpha(percentBg.getElapsedTime() * 2);
        }
        percentBg.setAlpha((float)(0.3f + Math.abs(Math.sin(percentBg.getElapsedTime()*5))*0.3f));
    }

    public void reset(){
        percentFg.setElapsedTime(0);
    }
}
