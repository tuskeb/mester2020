package hu.csanyzeg.master.MyBaseClasses.WorldUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.WorldHelper.WorldHelper;


//TODO: Setsize a betuket is meretezze at, betumeret beallitasi lehetoseg menet kozben
public abstract class WorldLabel<Helper, World> extends MyGroup {
    public enum ColorMode{
        byGroup, byChar
    }

    public enum FontWidthMode{
        monospace, variable
    }

    protected int index = -1;

    protected World world;

    protected WorldLabelStyle simpleLabelStyle;

    public abstract Helper createHelper(World world, Actor actor);
    public abstract WorldChar<Helper, World> createChar(MyGame game, World world, WorldLabelStyle simpleLabelStyle, char c);

    public WorldLabel(MyGame game, World world, CharSequence text, WorldLabelStyle simpleLabelStyle) {
        super(game);
        this.simpleLabelStyle = simpleLabelStyle;
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = game.getMyAssetManager().getFont(simpleLabelStyle.fontHash);
        Color color = simpleLabelStyle.fontColor;
        style.fontColor = color;
        this.world = world;


        if (simpleLabelStyle.fontWidthMode == FontWidthMode.monospace && simpleLabelStyle.maxFontWidth == -1){
            WorldChar myLabel = createChar(game,null, simpleLabelStyle, 'W');
            simpleLabelStyle.maxFontWidth = myLabel.getPrefWidth();
        }

        float position = 0f;
        int i = 0;
        for (char c : text.toString().toCharArray()) {
            WorldChar simpleChar = createChar(game, world, simpleLabelStyle, c);
            simpleChar.index = i;
            simpleChar.setX(position);
            addActor(simpleChar);
            if (simpleLabelStyle.fontWidthMode == FontWidthMode.monospace){
                position += simpleLabelStyle.maxFontWidth + simpleLabelStyle.fontSpacing;
            }else {
                position += simpleChar.getPrefWidth() + simpleLabelStyle.fontSpacing;
            }
            if (world != null && simpleLabelStyle.simpleLabelListener != null) {
                simpleLabelStyle.simpleLabelListener.onCharAdd(this, simpleChar, i);
            }
            i++;
        }

        setWidth(position);
        setHeight(simpleLabelStyle.fontSize);
        setOrigintoCenter();

        if (world != null){
            setActorWorldHelper((WorldHelper<?, Actor>) (createHelper(world, this)));
        }

    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (getActorWorldHelper() != null && simpleLabelStyle.simpleLabelListener != null && isVisible()){
            simpleLabelStyle.simpleLabelListener.onShow(this, getSimpleChars());
        }

    }

    public Array<WorldChar> getSimpleChars(){
        Array<WorldChar> simpleChars = new Array<>(getChildren().size);
        for(Actor actor : getChildren()){
            if (actor instanceof WorldChar && ((WorldChar)actor).index>=0){
                simpleChars.add((WorldChar)actor);
            }
        }
        return simpleChars;
    }

    public WorldLabelListener getSimpleUIListener() {
        return simpleLabelStyle.simpleLabelListener;
    }

    public void setSimpleUIListener(WorldLabelListener simpleUIListener) {
        this.simpleLabelStyle.simpleLabelListener = simpleUIListener;
    }

    public void removeSimpleUIListener() {
        this.simpleLabelStyle.simpleLabelListener = null;
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        if (simpleLabelStyle.colorMode == ColorMode.byGroup) {
            for (WorldChar helper : getSimpleChars()) {
                helper.setColor(color);
            }
        }
    }

    @Override
    public void setColor(float r, float g, float b, float a) {
        super.setColor(r, g, b, a);
        for(WorldChar helper : getSimpleChars()){
            helper.setColor(r, g, b, a);
        }
    }

    public ColorMode getColorMode() {
        return simpleLabelStyle.colorMode;
    }

    public void setColorMode(ColorMode colorMode) {
        this.simpleLabelStyle.colorMode = colorMode;
    }

    @Override
    public boolean remove() {
        for(WorldChar helper : getSimpleChars()){
            helper.remove();
        }
        return super.remove();
    }

    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (simpleLabelStyle.simpleLabelListener !=null) {
            if (visible) {
                simpleLabelStyle.simpleLabelListener.onShow(this, getSimpleChars());
            } else {
                simpleLabelStyle.simpleLabelListener.onHide(this, getSimpleChars());
            }
        }
    }

    public void hide(){
        if (simpleLabelStyle.simpleLabelListener !=null) {
            if (!isVisible()) {
                simpleLabelStyle.simpleLabelListener.onHide(this, getSimpleChars());
            }
        }
    }


    public FontWidthMode getFontWidthMode() {
        return simpleLabelStyle.fontWidthMode;
    }


    public void setText(String text){
        int i = 0;
        for(char c : text.toCharArray()){
            setCharAt(i, c);
            i++;
        }
    }

    public char getCharAt(int index){
        return getSimpleCharAt(index).getChar();
    }


    public WorldChar getSimpleCharAt(int index){
        for(WorldChar simpleChar : getSimpleChars()){
            if (simpleChar.index == index){
                return simpleChar;
            }
        }
        return null;
    }

    public void setCharAt(int index, char c){
        setCharAt(index,c,true);
    }

    public void setCharAt(int index, char c, boolean doListener){
        WorldChar old = getSimpleCharAt(index);
        //if (old.getChar() != c || simpleLabelStyle.fontWidthMode != FontWidthMode.monospace) {
            WorldChar nww = createChar(game, world, simpleLabelStyle, c);
            WorldChar before = getSimpleCharAt(index - 1);
            nww.index = old.index;
            if (before == null) {
                nww.setX(0);
            } else {
                nww.setX(before.getX() + before.getWidth() + simpleLabelStyle.fontSpacing);
            }
            addActor(nww);
            old.index = -1;

            if (!doListener || getSimpleUIListener() == null || getSimpleUIListener().onCharChange(this, old, nww)) {
                old.remove();
            }
        //}

    }

    public World getWorld() {
        return world;
    }

    public WorldLabelStyle getSimpleLabelStyle() {
        return simpleLabelStyle;
    }
}
