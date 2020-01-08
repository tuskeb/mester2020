package hu.csanyzeg.master.Demos.Demo2;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.OriginRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyContactListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleContact;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.IntervalTimerListener;

public class DemoActor extends OneSpriteStaticActor {
    public static final String textureHash = "badlogic.jpg";

    public static AssetList assetList;
    static {
        assetList = new AssetList();
        assetList.addTexture(textureHash);
    }
    SimpleWorldHelper sw;


    public DemoActor(MyGame game, SimpleWorld world, float x, float y) {
        super(game, textureHash);

        setPosition(x,y);
        setActorWorldHelper(sw = new SimpleWorldHelper(world, this, ShapeType.Circle, SimpleBodyType.Sensor));



        addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                ((SimpleWorldHelper)getActorWorldHelper()).body.moveToFixTime(getStage().getViewport().getWorldWidth() - getWidth(), getStage().getViewport().getWorldHeight() - getHeight(), 2, PositionRule.LeftBottom);
            }
        });

        //sw.body.setAngularVelocity(36);
        //sw.body.colorToFixTime(new Color(1,1,1,0), 3);
        //sw.body.originTo(3,100,100, OriginRule.FixPosition);

        sw.body.addCollisionRectangleShape("asd",0,0,20,20,30);
        sw.addContactListener(new SimpleBodyContactListener(){
            @Override
            public void beginContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                super.beginContact(contact, myHelper, otherHelper);
                Gdx.app.log("Log"," BeginContact");

            }

            @Override
            public void endContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                super.endContact(contact, myHelper, otherHelper);

                Gdx.app.log("Log"," EndContact");
                remove();
            }
        });

        sw.body.setSimpleBodyBehaviorListener(new SimpleBodyBehaviorListener(){

        });

        /*
        addTimer(new IntervalTimer(0,1f, 3f, new IntervalTimerListener(){
            @Override
            public void onTick(IntervalTimer sender, float correction) {
                super.onTick(sender, correction);
                DemoActor.this.setX(DemoActor.this.getX() + correction*10);
            }

            @Override
            public void onRepeat(IntervalTimer sender) {
                super.onRepeat(sender);
                DemoActor.this.setY(DemoActor.this.getY() + 10);
            }
        }));

         */
    }


    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        sw.body.sizeTo(1, getStage().getViewport().getWorldWidth(), getStage().getViewport().getWorldHeight(), PositionRule.LeftBottom);

    }
}
