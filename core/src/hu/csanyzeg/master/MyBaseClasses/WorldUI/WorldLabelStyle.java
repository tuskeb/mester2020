package hu.csanyzeg.master.MyBaseClasses.WorldUI;

import com.badlogic.gdx.graphics.Color;

public class WorldLabelStyle {
    public String fontHash = null;
    public Color fontColor = Color.WHITE;
    public float fontSize;
    public WorldLabel.FontWidthMode fontWidthMode = WorldLabel.FontWidthMode.variable;
    public float fontSpacing = 2;
    public WorldLabelListener simpleLabelListener = null;
    public WorldLabel.ColorMode colorMode = WorldLabel.ColorMode.byGroup;

    public float maxFontWidth = -1;

    public WorldLabelStyle(String fontHash, float fontSize) {
        this.fontHash = fontHash;
        this.fontSize = fontSize;
    }
}
