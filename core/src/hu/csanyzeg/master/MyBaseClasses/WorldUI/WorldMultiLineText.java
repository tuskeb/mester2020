package hu.csanyzeg.master.MyBaseClasses.WorldUI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.WorldHelper.WorldHelper;

public abstract class WorldMultiLineText<Helper, World> extends MyGroup {
    protected World world;

    protected WorldMultiLineTextStyle simpleMultiLineTextStyle;

    protected Array<WorldLabel> simpleLabels = new Array<>();

    protected OneSpriteStaticActor backgroundActor = null;

    public abstract Helper createHelper(World world, Actor actor);
    public abstract WorldLabel<Helper, World> createLabel(MyGame game, World world, CharSequence text, WorldLabelStyle simpleButtonStyle);


    public Array<WorldLabel> getSimpleLabels(){
        Array<WorldLabel> simpleLabels = new Array<>(getChildren().size);
        for(Actor actor : getChildren()){
            if (actor instanceof WorldLabel && ((WorldLabel)actor).index>=0){
                simpleLabels.add((WorldLabel) actor);
            }
        }
        return simpleLabels;
    }

    public WorldMultiLineText(MyGame game, World world, String text, WorldMultiLineTextStyle simpleMultiLineTextStyle) {
        super(game);

        //super.setWidth(simpleMultiLineTextStyle.width);
        System.out.println(this);
        setWidth(100);
        setHeight(100);
        setOrigintoCenter();
        System.out.println(this);
        setSize(200,100);


        this.simpleMultiLineTextStyle = simpleMultiLineTextStyle;
        this.world = world;


        if (simpleMultiLineTextStyle.backgroundHash != null) {
            backgroundActor = new OneSpriteStaticActor(game, simpleMultiLineTextStyle.backgroundHash);
            backgroundActor.setActorWorldHelper((WorldHelper<?, Actor>) createHelper(world, backgroundActor));
            backgroundActor.getActorWorldHelper().addToWorld();
//            backgroundActor.setSize(simpleMultiLineTextStyle.width, h);
            addActor(backgroundActor);
        }


        float maxHeight = 0;
        float maxWidth = 0;
        Array<WorldLabel> simpleLabels = new Array<>();
        int i = 0;
        for(String s : text.split("\n")){
            WorldLabel labelActor = createLabel(game, world, s, simpleMultiLineTextStyle);
            simpleLabels.add(labelActor);
            labelActor.index = i;
            if (labelActor.getWidth() > maxWidth){
                maxWidth = labelActor.getWidth();
            }
            if (labelActor.getHeight() > maxHeight){
                maxHeight = labelActor.getHeight();
            }
            i++;
        }



        for(i = 0; i <simpleLabels.size; i++){
            simpleLabels.get(i).setY((simpleLabels.size - i - 1) * (simpleMultiLineTextStyle.lineSpacing + (simpleMultiLineTextStyle.lineHeight == -1 ? maxHeight : simpleMultiLineTextStyle.lineHeight)) + simpleMultiLineTextStyle.lineSpacing / 2);
        }


        switch (simpleMultiLineTextStyle.horizontalAlign){
            case left:
                break;
            case right:
                for(i = 0; i <simpleLabels.size; i++){
                    simpleLabels.get(i).setX(maxWidth - simpleLabels.get(i).getWidth());
                }
                break;
            case center:
                for(i = 0; i <simpleLabels.size; i++){
                    simpleLabels.get(i).setX(maxWidth / 2 - simpleLabels.get(i).getWidth() / 2);
                }
                break;
        }



        setSize(maxWidth, simpleLabels.size * (simpleMultiLineTextStyle.lineSpacing + (simpleMultiLineTextStyle.lineHeight == -1 ? maxHeight : simpleMultiLineTextStyle.lineHeight)));

        setActorWorldHelper((WorldHelper<?, Actor>) createHelper(world, this));

        i = 0;
        for(WorldLabel s : simpleLabels){
            addActor(s);
            if (simpleMultiLineTextStyle.simpleMultiLineTextListener != null){
                simpleMultiLineTextStyle.simpleMultiLineTextListener.onRowAdd(this, s, backgroundActor, i);
                i++;
            }
        }

    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (getActorWorldHelper() != null && simpleMultiLineTextStyle != null && simpleMultiLineTextStyle.simpleMultiLineTextListener != null && isVisible()){
            simpleMultiLineTextStyle.simpleMultiLineTextListener.onShow(this, getSimpleLabels(), backgroundActor);
        }
    }


    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        if (simpleMultiLineTextStyle.simpleMultiLineTextListener !=null) {
            if (visible) {
                simpleMultiLineTextStyle.simpleMultiLineTextListener.onShow(this, getSimpleLabels(), backgroundActor);
            } else {
                simpleMultiLineTextStyle.simpleMultiLineTextListener.onHide(this, getSimpleLabels(), backgroundActor);
            }
        }
    }

    public void hide(){
        setVisible(false);
    }



}
