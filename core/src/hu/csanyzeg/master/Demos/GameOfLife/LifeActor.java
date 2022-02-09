package hu.csanyzeg.master.Demos.GameOfLife;

import com.badlogic.gdx.math.RandomXS128;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabel;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.Direction;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.PositionRule;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyBehaviorListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyContactListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBodyType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleContact;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldHelper;

public class LifeActor extends MyGroup {
    boolean isMale;
    public OneSpriteStaticActor bg;
    public OneSpriteStaticActor fg;
    public OneSpriteStaticActor focusActor;
    public SimpleLabel label;
    public static RandomXS128 random = new RandomXS128();
    public float growSpeed;
    public boolean randompos;

    // A játék célja, hogy a különböző életkorú egyedeket irányítsunk a pályán. A különböző életkorban változik a viselkedésük, bizonyos életkorban
    // tudnak szaporodni, de ártani is a populációnak.
    // A páláyán lévő akadályba ütközve eltűnhetnek az egyedeink.
    // Az egyedek egymást is bánthatják, és mások jelenlétében nem szaporodnak. Azaz néha el kell különíteni őket.
    // A pontszám a maximális populáció alapján lesz meghatározva.
    // Vége a játéknak, ha nincs olyan egyed, vagy nem lehetséges, amely képes szaporodni.
    public enum StateType {
        child,
        // Kis méretű, lassan halad.
        // Akkor jön létre, ha két különböző nemű ADULT találkozik.
        // Minél nagyobbak az ADULT elemek, annál több jelenik meg helyettük a találkozáskor.
        // Az ADULT elemek eltűnnek amikor kiválik belőlük a CHILD elem.
        // Ha ADULT elem találkozik velük, eltűnnek.
        // Mind egyszínű
        // A játék célja: Vigyázni kell rá, mert a nagyok megeszik, és az akadálytól is bajba kerülhet.
        teeneger,
        // Gyors,
        // Védelmet élvez az ADULT elemek elől.
        // Megjelennek rajt a nemek szerinti színek.
        // A játék célja: Vigyázni kell rá, mert hamar bajbakerülhet a pályán lévő akadálytól. A nagyok már nem eszik meg.
        adult,
        // Közepes sebességű.
        // Különböző nemű ADULT elemmel érintkezve több CHILD elemet hoz létre. Minél nagyobb, annál több elem jön létre.
        // Ha bármilyen elemmel érintkezik, az blokkolja a másik nemmel való érintkezésből keletkező CHILD elemek létrejöttét.
        // Ha találkozik CHILD elemekkel, azok vele érintkezve eltűnnek.
        // Játék célja: Elkülöníteni két különböző neműt, és úgy érintkeztetni. Az akadálytól bajba kerülhet.
        retired,
        // Lassú
        // Szaporodásra nem képes.
        // Nem hat rá a pályán lévő akadály.
        // A nemi különbség jelzése eltűnik.
        // A leglassabban haladó elem.
        // Amennyiben érintkezik egy ADULT elemmel, az akkor nem képes CHILD elemet létrehozni amíg érintkeznek.
        // Ha találkozik CHILD elemekkel, azok vele érintkezve eltűnnek.
        // Ha eléri a FINALSIZE méretet, eltűnik.
        // A játék célja: Nem szabad ezt az állapotot elérni. Ha van ilyen, el kell különíteni. Az akadály sem árt neki.
        death
        //Amikor le kell venni a térképről, mert már túl nagy
    }

    protected StateType prevState = StateType.child;

    public static final int CHILD = 16;
    public static final int TEENEGER = 64;
    public static final int ADULT = 128;
    public static final int RETIRED = 256;
    public static final int FINALSIZE = 512;

    public static final String CHILD_BG = "";
    public static final String CHILD_FG = "";

    public static final String TEENEGER_BG_F = "";
    public static final String TEENEGER_FG_F = "";
    public static final String TEENEGER_BG_M = "";
    public static final String TEENEGER_FG_M = "";

    public static final String ADULT_BG_F = "";
    public static final String ADULT_FG_F = "";
    public static final String ADULT_BG_M = "";
    public static final String ADULT_FG_M = "";

    public static final String RETIRED_BG = "";
    public static final String RETIRED_FG = "";

    public static final String FOCUS = "";

    private boolean force = false;

    public LifeActor(MyGame game, SimpleWorld world, int maxsize, boolean randompos) {
        super(game);
        float s;
        isMale = random.nextBoolean();
        this.randompos=randompos;

        addActor(bg = new OneSpriteStaticActor(game, "yellow.png") {

        });
        //bg.sprite.setTexture(game.getMyAssetManager().getTexture("badlogic.jpg"));
        addActor(fg = new OneSpriteStaticActor(game, "green.png") {
            @Override
            public void act(float delta) {
                super.act(delta);
                switch (getState()){
                    case child:
                        setAlpha((float)Math.abs(Math.sin(elapsedTime * 1)));
                        break;
                    case teeneger:
                        setAlpha((float)Math.abs(Math.sin(elapsedTime * 3)));
                        break;
                    case adult:
                        setAlpha((float)Math.abs(Math.sin(elapsedTime * 5)));
                        break;
                    case retired:
                        setAlpha((float)Math.abs(Math.sin(elapsedTime * 2)));
                        break;
                }
            }
        });

        addActor(focusActor = new OneSpriteStaticActor(game, "blue.png") {

        });
        focusActor.setVisible(false);

        growSpeed = (random.nextFloat() / 2 + 0.5f) * 0.2f;
        setSize(s = random.nextInt(maxsize - CHILD + 1) + CHILD, s);
        setOrigintoCenter();

        //addActor(label = new SimpleLabel(game, world, "ASD", new WorldLabelStyle("demoflappy/flappyfont.ttf",120)));

        setActorWorldHelper(new SimpleWorldHelper(world, this, ShapeType.Circle, SimpleBodyType.Sensor));

        SimpleBody sb = ((SimpleWorldHelper) getActorWorldHelper()).getBody();
        sb.setUserData(this);
        sb.setSimpleBodyBehaviorListener(new SimpleBodyBehaviorListener() {
            @Override
            public void onStopMove(SimpleBody sender) {
                super.onStopMove(sender);
                ((LifeActor)sender.getUserData()).moveToRandomPos();
            }
        });

        ((SimpleWorldHelper) getActorWorldHelper()).addContactListener(new SimpleBodyContactListener() {
            @Override
            public void beginContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                super.beginContact(contact, myHelper, otherHelper);
                if (!((LifeActor)myHelper.actor).force){
                    ((LifeActor)myHelper.actor).moveToRandomPos();
                }
                if (!((LifeActor)otherHelper.actor).force) {
                    ((LifeActor) otherHelper.actor).moveToRandomPos();
                }
            }

            @Override
            public void endContact(SimpleContact contact, SimpleWorldHelper myHelper, SimpleWorldHelper otherHelper) {
                super.endContact(contact, myHelper, otherHelper);
            }
        });
    }

    public boolean getFocus(){
        return this.focusActor.isVisible();
    }

    public void setFocus(boolean focus) {
        this.focusActor.setVisible(focus);
    }

    public void moveToRandomPos(){
        moveto(
                random.nextInt((int) (getStage().getViewport().getWorldWidth() - getWidth())),
                random.nextInt((int) (getStage().getViewport().getWorldHeight() - getHeight())), false);
    }

    public void setRandomPos(){

        setPos(
                random.nextInt((int) (getStage().getViewport().getWorldWidth() - getWidth())),
                random.nextInt((int) (getStage().getViewport().getWorldHeight() - getHeight()))
        );
    }

    public void moveto(float x, float y, boolean force) {
        SimpleBody sb = ((SimpleWorldHelper) getActorWorldHelper()).getBody();
        sb.moveToFixSpeed(x,y,getSpeed(),PositionRule.Center);
        sb.rotateToFixSpeed(sb.getLinearVelocity().angle(), 100, Direction.Shorter);
        this.force = force;
    }

    public void setPos(float x, float y){
        SimpleBody sb = ((SimpleWorldHelper) getActorWorldHelper()).getBody();
        sb.setPositionSafe(x,y);
    }

    public StateType getState(){
        if (getWidth() > FINALSIZE) return StateType.death;
        if (getWidth() > RETIRED) return StateType.retired;
        if (getWidth() > ADULT) return StateType.adult;
        if (getWidth() > TEENEGER) return StateType.teeneger;
        return StateType.child;
    }

    public float getSpeed() {
        switch (getState()) {
            case child:
                return random.nextInt(10) + 10;
            case teeneger:
                return random.nextInt(20) + 80;
            case adult:
                return random.nextInt(20) + 30;
            case retired:
                return random.nextInt(10) + 5;
        }
        return 0;
    }

    @Override
    protected void sizeChanged() {
      super.sizeChanged();
      for (Actor a: getChildren()) {
          a.setSize(getWidth(), getHeight());
      }
    }

    @Override
    protected void setStage(Stage stage) {
        super.setStage(stage);
        SimpleBody sb = ((SimpleWorldHelper) getActorWorldHelper()).getBody();
        if (randompos){
            setRandomPos();
        }
        moveToRandomPos();
        sb.sizeToFixSpeed(FINALSIZE, FINALSIZE, growSpeed, PositionRule.Center);
    }

    protected void death(){
        getActorWorldHelper().remove();
    }

}
