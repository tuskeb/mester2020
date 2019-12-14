package hu.csanyzeg.master.Demos.Box2dHelper;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.Random;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ShapeType;

public class BoxActor extends OneSpriteStaticActor {
    public static final String boxTexture = "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(boxTexture);
    }


    public BoxActor(MyGame game, World world, float x, float y, float rotation) {
        super(game, boxTexture);
        setSize(1,1);
        setPosition(x,y);
        setRotation(rotation);
        setActorWorldHelper(new Box2DWorldHelper(world, this, ShapeType.Rectangle, new MyFixtureDef(), BodyDef.BodyType.DynamicBody));
        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                getActorWorldHelper().getActor().setPosition(getX(),9);
            }
        });

    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (flashTimer > 0) {
            flashTimer -= delta * 3;
            if (flashTimer<0){
                setColor(1,1,1,1);
            }else{
                setColor(1-flashTimer / 2,1-flashTimer / 2,1-flashTimer / 2,1);
            }
        }else{
            flashTimer = 0;
        }
    }

    private float flashTimer = 0;
    public void setFlash(){
        flashTimer = 1;
    }
}
