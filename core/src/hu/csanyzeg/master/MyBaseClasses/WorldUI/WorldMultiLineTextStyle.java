package hu.csanyzeg.master.MyBaseClasses.WorldUI;

public class WorldMultiLineTextStyle extends WorldLabelStyle {
    public float lineHeight = -1;
    public float lineSpacing = 10;
    public float width = -1;
    //public boolean wordWrap = false;
    public WorldHorizontalAlign horizontalAlign = WorldHorizontalAlign.center;
    public String backgroundHash = null;
    public WorldMultiLineTextListener simpleMultiLineTextListener = null;

    public WorldMultiLineTextStyle(String fontHash, float fontSize) {
        super(fontHash, fontSize);
    }
}
