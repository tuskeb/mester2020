package hu.csanyzeg.master.MyBaseClasses.WorldUI;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.WorldHelper.WorldHelper;


//TODO: A világban valami gáz van, ha simán a worldhelper nélkül változik a méret
//TODO: SetSize módosítsa a benne lévő tartalom méretét, és ne akadjon össze a világgal.
public abstract class WorldButton<Helper, World> extends MyGroup {

    protected World world;

    protected WorldButtonStyle simpleButtonStyle;

    protected OneSpriteStaticActor backgroundActor = null;
    protected OneSpriteStaticActor backgroundClickActor = null;
    protected WorldLabel labelActor;

    public abstract Helper createHelper(World world, Actor actor);
    public abstract WorldLabel<Helper, World> createLabel(MyGame game, World world, CharSequence text, WorldLabelStyle simpleButtonStyle);

    public WorldButton(MyGame game, World world, CharSequence text, WorldButtonStyle simpleButtonStyle) {
        super(game);

        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (backgroundClickActor != null){
                    backgroundActor.setVisible(false);
                    backgroundClickActor.setVisible(true);
                }
                if (simpleButtonStyle.clickSoundHash != null){
                    game.getMyAssetManager().getSound(simpleButtonStyle.clickSoundHash).play();
                }
                if (simpleButtonStyle.simpleButtonListener!= null) {
                    simpleButtonStyle.simpleButtonListener.onClick(WorldButton.this, labelActor, backgroundActor, backgroundClickActor);
                }

            }
        });

        super.setWidth(simpleButtonStyle.width);
        super.setHeight(simpleButtonStyle.height);

        setActorWorldHelper((WorldHelper<?, Actor>) createHelper(world, this));

        this.simpleButtonStyle = simpleButtonStyle;
        this.world = world;

        if (simpleButtonStyle.backgroundHash != null) {
            backgroundActor = new OneSpriteStaticActor(game, simpleButtonStyle.backgroundHash);
            backgroundActor.setActorWorldHelper((WorldHelper<?, Actor>)createHelper(world, backgroundActor));
            backgroundActor.getActorWorldHelper().addToWorld();
            backgroundActor.setSize(simpleButtonStyle.width, simpleButtonStyle.height);
            addActor(backgroundActor);
        }


        if (simpleButtonStyle.backgroundClickedHash != null) {
            backgroundClickActor = new OneSpriteStaticActor(game, simpleButtonStyle.backgroundClickedHash);
            backgroundClickActor.setActorWorldHelper((WorldHelper<?, Actor>)createHelper(world, backgroundActor));
            backgroundClickActor.setSize(simpleButtonStyle.width, simpleButtonStyle.height);
            addActor(backgroundClickActor);
            backgroundClickActor.setVisible(false);
        }

        labelActor = createLabel(game, world, text, simpleButtonStyle);
        addActor(labelActor);
        switch (simpleButtonStyle.verticalAlign){
            case top:
                labelActor.setY(simpleButtonStyle.height - labelActor.getHeight());
                break;
            case bottom:
                labelActor.setY(0);
                break;
            case middle:
                labelActor.setY(simpleButtonStyle.height / 2 - labelActor.getHeight() / 2);
                break;
        }
        switch (simpleButtonStyle.horizontalAlign){
            case left:
                labelActor.setX(0);
                break;
            case right:
                labelActor.setX(simpleButtonStyle.width - labelActor.getWidth());
                break;
            case center:
                labelActor.setX(simpleButtonStyle.width / 2 - labelActor.getWidth() / 2);
                break;
        }
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        if (getActorWorldHelper() != null && simpleButtonStyle != null && simpleButtonStyle.simpleButtonListener != null && isVisible()){
            simpleButtonStyle.simpleButtonListener.onShow(this, labelActor, backgroundActor, backgroundClickActor);
        }
    }


    public WorldButtonListener getSimpleUIListener() {
        return simpleButtonStyle.simpleButtonListener;
    }

    public void setSimpleButtonListener(WorldButtonListener simpleUIListener) {
        this.simpleButtonStyle.simpleButtonListener = simpleUIListener;
    }


    @Override
    public void setSize(float width, float height) {
        /*((SimpleBody)labelActor.getActorWorldHelper().getBody()).setSize(labelActor.getWidth() * width / getWidth(), labelActor.getHeight() * height / getHeight());
        if (backgroundActor != null) ((SimpleBody)backgroundActor.getActorWorldHelper().getBody()).setSize(backgroundActor.getWidth() * width / getWidth(), backgroundActor.getHeight() * height / getHeight());
        if (backgroundClickActor != null) ((SimpleBody)backgroundClickActor.getActorWorldHelper().getBody()).setSize(backgroundClickActor.getWidth() * width / getWidth(), backgroundClickActor.getHeight() * height / getHeight());*/
        super.setSize(width, height);
    }

    @Override
    public void setWidth(float width) {
/*        ((SimpleBody)labelActor.getActorWorldHelper().getBody()).setWidth(labelActor.getWidth() * width / getWidth());
        if (backgroundActor != null) ((SimpleBody)backgroundActor.getActorWorldHelper().getBody()).setWidth(backgroundActor.getWidth() * width / getWidth());
        if (backgroundClickActor != null) ((SimpleBody)backgroundClickActor.getActorWorldHelper().getBody()).setWidth(backgroundClickActor.getWidth() * width / getWidth());*/
        super.setWidth(width);
    }

    @Override
    public void setHeight(float height) {
/*        ((SimpleBody)labelActor.getActorWorldHelper().getBody()).setHeight(labelActor.getHeight() * height / getHeight());
        if (backgroundActor != null) ((SimpleBody)backgroundActor.getActorWorldHelper().getBody()).setHeight(backgroundActor.getHeight() * height / getHeight());
        if (backgroundClickActor != null) ((SimpleBody)backgroundClickActor.getActorWorldHelper().getBody()).setHeight(backgroundClickActor.getHeight() * height / getHeight());*/
        super.setHeight(height);
    }
}
