package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabelAction1;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabel;

import static hu.csanyzeg.master.Demos.FlappyBird.FlappyStage.flappyFont;

public class SpaceControlStage extends SimpleWorldStage {

    public static AssetList assetList = new AssetList();
    static {
        //AssetList.collectAssetDescriptor(EnemyActor.class, assetList);
    }


    public SpaceControlStage(MyGame game) {
        super(new ExtendViewport(1600, 900), game);


        SimpleLabel simpleLabel;


        simpleLabel = new SimpleLabel(game, world, "The quick brown fox jumps over the lazy dog.", flappyFont, 1,1,1,1,40, new SimpleLabelAction1());
      /*  simpleLabel.setRotation(20);
        simpleLabel.setPosition(100,100);
        simpleLabel.setActorWorldHelper(new SimpleWorldHelper(world, simpleLabel, ShapeType.Null, SimpleBodyType.Ghost));
        ((SimpleWorldHelper)simpleLabel.getActorWorldHelper()).getBody().rotateToFixTime(0,1, Direction.ClockWise);

       */
        addActor(simpleLabel);
        simpleLabel.setPositionCenter(400);


        addActor(simpleLabel = new SimpleLabel(game, world, "Start", flappyFont, 1,1,1,1,400, new SimpleLabelAction1()));
        simpleLabel.setPositionCenter();



    }
}
