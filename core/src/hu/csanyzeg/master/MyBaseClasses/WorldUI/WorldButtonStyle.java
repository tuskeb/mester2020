package hu.csanyzeg.master.MyBaseClasses.WorldUI;

//TODO: assets mappatol fuggetleniteni
public class WorldButtonStyle extends WorldLabelStyle {
    public float width;
    public float height;

    //public float paddingLeft = 0f;
    //public float paddingRight = 0f;
    //public float paddingTop = 0f;
    //public float paddingBottom = 0f;

    public String backgroundHash;
    public String backgroundClickedHash = null;

    public String clickSoundHash = null;

    public WorldButtonListener simpleButtonListener = null;
    public WorldHorizontalAlign horizontalAlign = WorldHorizontalAlign.center;
    public WorldVerticalAlign verticalAlign = WorldVerticalAlign.middle;

    public WorldButtonStyle(String fontHash, float fontSize, float width, float height, String backgroundHash) {
        super(fontHash, fontSize);
        this.width = width;
        this.height = height;
        this.backgroundHash = backgroundHash;
    }
}
