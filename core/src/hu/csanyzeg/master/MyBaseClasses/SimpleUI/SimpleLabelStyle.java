package hu.csanyzeg.master.MyBaseClasses.SimpleUI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class SimpleLabelStyle {
    public String fontHash = "alegreyaregular.otf";
    public Color fontColor = Color.WHITE;
    public float fontSize = 40f;
    public SimpleLabel.FontWidthMode fontWidthMode = SimpleLabel.FontWidthMode.variable;
    public float fontSpacing = 2;
    public SimpleLabelListener simpleUIListener = new SimpleLabelAction1();
    public SimpleLabel.ColorMode colorMode = SimpleLabel.ColorMode.byGroup;

    public float maxFontWidth = -1;
}
