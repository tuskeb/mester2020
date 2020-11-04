package hu.csanyzeg.master.MyBaseClasses.WorldUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;
import hu.csanyzeg.master.MyBaseClasses.WorldHelper.WorldHelper;

public abstract class WorldChar<Helper, World> extends MyGroup {
    public int index = -1;
    protected World world;


    public abstract Helper createHelper(World world);

    public WorldChar(MyGame game, World world, WorldLabelStyle simpleLabelStyle, char c) {
        super(game);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.fontColor = simpleLabelStyle.fontColor;
        labelStyle.font = game.getMyAssetManager().getFont(simpleLabelStyle.fontHash);

        MyLabel myLabel = new MyLabel(game, c + "", labelStyle);
        float scale = simpleLabelStyle.fontSize / myLabel.getPrefHeight();
        myLabel.setFontScale(scale);
        myLabel.setWidthWhithAspectRatio(myLabel.getWidth() * scale);
        myLabel.setAlignment(Align.center);
        if (simpleLabelStyle.fontWidthMode == WorldLabel.FontWidthMode.monospace) {
            myLabel.setWidth(simpleLabelStyle.maxFontWidth);
        }
        addActor(myLabel);

        setWidth(myLabel.getWidth());
        setHeight(myLabel.getHeight());
        setOrigintoCenter();

        if (world != null) {
            Helper simpleWorldHelper = createHelper(world);
            setActorWorldHelper((WorldHelper<Helper, Actor>) simpleWorldHelper);
        }

    }

    private MyLabel label;

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        label.setColor(color);
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        super.setColor(r, g, b, a);
        label.setColor(r,g,b,a);
    }

    @Override
    public void addActor(Actor actor) {
        super.addActor(actor);
        label = (MyLabel)actor;
    }

    @Override
    public void setSize(float width, float height) {
        if(getHeight() != height || getWidth() != width) {

            label.setFontScale(label.getFontScaleX() * width / getWidth(), label.getFontScaleY()*  height / getHeight());
            label.setSize(width, height);
            super.setSize(width, height);
        }
    }

    public float getPrefWidth(){
        return label.getPrefWidth();
    }

    public float getPrefHeight(){
        return label.getPrefHeight();
    }

    public void setFontScale(float fontScale){
        label.setFontScale(fontScale);
    }

    public float getFontScaleX(){
        return label.getFontScaleX();
    }

    public float getFontScaleY(){
        return label.getFontScaleX();
    }

    public void setFontScaleX(float fontScale){
        label.setFontScaleX(fontScale);
    }

    public void setFontScaleY(float fontScale){
        label.setFontScaleY(fontScale);
    }

    public char getChar(){
        return label.getText().charAt(0);
    }


    public void setChar(char c){
        label.setText(c + "");
    }

    @Override
    public void copyFrom(Actor other) {
        super.copyFrom(other);
        if (other instanceof WorldChar){
            index = ((WorldChar) other).index;
        }
    }
}
