package hu.csanyzeg.master.Demos.Firework;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Random;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.Box2DWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.MyFixtureDef;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Box2dWorld.ShapeType;

public class FireworkActor extends OneSpriteStaticActor {
    public static final String fireworkTexture = "demofirework/whiteBallHD.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(fireworkTexture);
    }

    private Vector2 force = new Vector2(0,0);
    private Random random;
    private boolean master;
    private boolean isSensor;

    public FireworkActor(MyGame game, World world, boolean master) {
        super(game, fireworkTexture);
        this.master = master;
        random = new Random();
        alpha = 1;
        if(!master) {
            setSize(0.1f, 0.1f);
            isSensor = false;
        }
        else {
            setSize(0.25f, 0.25f);
            isSensor = true;
        }
        setActorWorldHelper(new Box2DWorldHelper(world, this, ShapeType.Circle, new MyFixtureDef(0.2f, 0.7f, 5f, isSensor), BodyDef.BodyType.DynamicBody));
        setColor(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
        setAlpha(1);
    }

    float alpha;

    private void animation()
    {
        if(!master) {
            if (alpha > 0.005) alpha -= 0.005;
            else alpha = 0;
            setAlpha(alpha);
        }
        else setColor(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        ((Body)getActorWorldHelper().getBody()).applyForceToCenter(force,true);
        if((getX() < -getWidth() || getY() < -getHeight() || getX() > getStage().getViewport().getWorldWidth()+getWidth() || getY() > getStage().getViewport().getWorldWidth()+getHeight()) && !master) remove();
        animation();
    }

    public void setForce(Vector2 i) {
        this.force = i;
    }
}
