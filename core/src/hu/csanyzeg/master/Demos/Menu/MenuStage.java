package hu.csanyzeg.master.Demos.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import hu.csanyzeg.master.Demos.Actor.ActorScreen;
import hu.csanyzeg.master.Demos.Box2dHelper.Box2dHelperScreen;
import hu.csanyzeg.master.Demos.Box2dJoin.Box2dJoinScreen;
import hu.csanyzeg.master.Demos.Firework.FireworkScreen;
import hu.csanyzeg.master.Demos.FlappyBird.FlappyScreen;
import hu.csanyzeg.master.Demos.Szakkor.SzakkorScreen;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteAnimatedActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

import static hu.csanyzeg.master.Demos.Box2dHelper.BoxActor.boxTexture;
import static hu.csanyzeg.master.Demos.Box2dJoin.ChainLinkActor.linkTexture;
import static hu.csanyzeg.master.Demos.FlappyBird.BirdActor.birdTexture;
import static hu.csanyzeg.master.Demos.FlappyBird.FlappyStage.flappyFont;
import static hu.csanyzeg.master.Demos.Menu.MenuButton.fontHash;

/**
 * Created by tuskeb on 2016. 09. 30..
 */
public class MenuStage extends MyStage {
    public static String blissTexture = "demomenu/bliss.jpg";
    public static String kekTexture = "demomenu/kek.jpg";
    public static String startTexture = "demomenu/start.jpg";
    public static String clockTexture = "demomenu/clock.jpg";
    public static String fireworkTexture = "demomenu/firework.png";
    public static String startMenuTexture = "demomenu/startmenu.png";
    public static String powerOffTexture = "demomenu/poweroff.png";
    public static String trebuchet = "demomenu/trebuc.ttf";
    public static String shutdownTexture = "demomenu/shutdown.jpg";
    public static String shutdownSound = "demomenu/shutdown.mp3";
    public static String shutdownWallpaperTexture = "demomenu/shutdownWallpaper.jpg";

    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(MenuButton.class, assetList);
        assetList.addTexture(blissTexture);
        assetList.addTexture(kekTexture);
        assetList.addTexture(startTexture);
        assetList.addTexture(clockTexture);
        assetList.addTexture(linkTexture);
        assetList.addTexture(fireworkTexture);
        assetList.addTexture(boxTexture);
        assetList.addTextureAtlas(birdTexture);
        assetList.addTexture(startMenuTexture);
        assetList.addTexture(powerOffTexture);
        assetList.addTexture(shutdownTexture);
        assetList.addSound(shutdownSound);
        assetList.addTexture(shutdownWallpaperTexture);
        assetList.addFont(trebuchet,trebuchet, 16, Color.WHITE);
    }

    OneSpriteStaticActor bliss;
    OneSpriteStaticActor kek;
    OneSpriteStaticActor start;
    OneSpriteStaticActor clock;
    OneSpriteStaticActor startMenu;

    OneSpriteStaticActor linkActor;
    OneSpriteStaticActor fireworkActor;
    OneSpriteStaticActor boxActor;
    OneSpriteAnimatedActor flappyActor;
    OneSpriteStaticActor powerOff;

    OneSpriteStaticActor shutdown;
    OneSpriteStaticActor shutdownWall;

    MyLabel linkActorLabel;
    MyLabel fireworkActorLabel;
    MyLabel boxActorLabel;
    MyLabel flappyActorLabel;

    MyLabel clockLabel;

    boolean show = true;

    public MenuStage(final MyGame game) {
        super(new ResponseViewport(720), game);
        addBackButtonScreenBackByStackPopListener();
        makeDesktop();
        makeButtons();
        BootStage.firstBoot = false;
        start.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(show)
                {
                    linkActor.remove();
                    fireworkActor.remove();
                    boxActor.remove();
                    flappyActor.remove();
                    powerOff.remove();
                    startMenu.remove();
                    linkActorLabel.remove();
                    fireworkActorLabel.remove();
                    boxActorLabel.remove();
                    flappyActorLabel.remove();
                    show = false;
                }
                else
                {
                    addActor(startMenu);
                    addActor(linkActor);
                    addActor(fireworkActor);
                    addActor(boxActor);
                    addActor(flappyActor);
                    addActor(powerOff);
                    addActor(linkActorLabel);
                    addActor(fireworkActorLabel);
                    addActor(boxActorLabel);
                    addActor(flappyActorLabel);
                    show = true;
                }
            }
        });

        /*addActor(new MenuButton(game, "Szakkör Demo"){
            @Override
            public void init() {
                super.init();
                setPosition(0,200);
                setSize(200,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new SzakkorScreen(game));
                    }
                });
            }
        });


        addActor(new MenuButton(game, "Actor Demo"){
            @Override
            public void init() {
                super.init();
                setPosition(0,100);
                setSize(200,50);
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        game.setScreen(new ActorScreen(game));
                    }
                });
            }
        });*/
    }

    private void shutdown()
    {
        shutdown = new OneSpriteStaticActor(game, shutdownTexture);
        shutdownWall = new OneSpriteStaticActor(game, shutdownWallpaperTexture);

        shutdownWall.setSize(getViewport().getWorldWidth(), getViewport().getWorldHeight());
        shutdown.setPosition(getViewport().getWorldWidth()/2-shutdown.getWidth()/2,getViewport().getWorldHeight()/2-shutdown.getHeight()/2);

        addActor(shutdownWall);
        addActor(shutdown);
        game.getMyAssetManager().getSound(shutdownSound).play();

        addTimer(new TickTimer(3f, false, new TickTimerListener() {
            @Override
            public void onRepeat(TickTimer sender) {

            }

            @Override
            public void onTick(Timer sender, float correction) {
                game.setScreenBackByStackPop();
            }

            @Override
            public void onStop(Timer sender) {

            }

            @Override
            public void onStart(Timer sender) {

            }
        }));
    }

    private void makeDesktop()
    {
        clockLabel = new MyLabel("12:00",getLabelStyle()) {
            @Override
            public void init() {
                setColor(Color.WHITE);
            }
        };

        startMenu = new OneSpriteStaticActor(game, startMenuTexture);
        bliss = new OneSpriteStaticActor(game, blissTexture);
        kek = new OneSpriteStaticActor(game, kekTexture);
        start = new OneSpriteStaticActor(game, startTexture);
        clock = new OneSpriteStaticActor(game, clockTexture);
        addActor(bliss);
        addActor(kek);
        addActor(start);
        addActor(clock);
        addActor(clockLabel);
        addActor(startMenu);

        kek.setWidth(getViewport().getWorldWidth());
        clock.setWidth(clock.getWidth()*0.5f);
        clock.setX(kek.getX() + kek.getWidth() - clock.getWidth());
        clockLabel.setAlignment(0);
        clockLabel.setX(clock.getX() + clock.getWidth()/2 - clockLabel.getWidth()/2);
        clockLabel.setY(clock.getY() + clock.getHeight()/2 - clockLabel.getHeight()/2);
        startMenu.setPosition(0,kek.getY()+kek.getHeight());
    }

    ClickListener linkListener;
    ClickListener fireworkListener;
    ClickListener boxListener;
    ClickListener flappyListener;

    private void makeButtons()
    {
        linkListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new Box2dJoinScreen(game));
            }
        };

        fireworkListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new FireworkScreen(game));
            }
        };

        boxListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new Box2dHelperScreen(game));
            }
        };

        flappyListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new FlappyScreen(game));
            }
        };

        linkActor = new OneSpriteStaticActor(game, linkTexture){
            @Override
            public void init() {
                super.init();
                setPosition(5,startMenu.getY() + 60);
                setSize(32,(32/getWidth()*getHeight()));
                addListener(linkListener);
            }
        };
            linkActorLabel = new MyLabel("B2D Join Demo", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(44, linkActor.getY() - linkActor.getHeight()/2 + 4);
                    setColor(Color.BLACK);
                    addListener(linkListener);
                }
            };


        fireworkActor = new OneSpriteStaticActor(game, fireworkTexture){
            @Override
            public void init() {
                super.init();
                setPosition(linkActor.getX(),linkActor.getY() + linkActor.getHeight() + 15);
                setSize(32,(32/getWidth()*getHeight()));
                addListener(fireworkListener);
            }
        };

            fireworkActorLabel = new MyLabel("Firework Demo", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(44, fireworkActor.getY() + 4);
                    setColor(Color.BLACK);
                    addListener(fireworkListener);
                }
            };

        boxActor = new OneSpriteStaticActor(game, boxTexture){
            @Override
            public void init() {
                super.init();
                setPosition(linkActor.getX(),fireworkActor.getY() + fireworkActor.getHeight() + 10);
                setSize(32,(32/getWidth()*getHeight()));
                addListener(boxListener);
            }
        };

            boxActorLabel = new MyLabel("Box2D Helper", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(44, boxActor.getY() + 4);
                    setColor(Color.BLACK);
                    addListener(boxListener);
                }
            };

        flappyActor = new OneSpriteAnimatedActor(game, birdTexture){
            @Override
            public void init() {
                super.init();
                setPosition(linkActor.getX(),boxActor.getY() + boxActor.getHeight() + 10);
                setFps(6);
                addListener(flappyListener);
            }
        };

            flappyActorLabel = new MyLabel("Flappy Demo", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(44, flappyActor.getY());
                    setColor(Color.BLACK);
                    addListener(flappyListener);
                }
            };

        powerOff = new OneSpriteStaticActor(game, powerOffTexture){
            @Override
            public void init() {
                super.init();
                setPosition(startMenu.getX() + startMenu.getWidth() - getWidth(), startMenu.getY());
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        shutdown();
                    }
                });
            }
        };

        addActor(linkActor);
        addActor(fireworkActor);
        addActor(boxActor);
        addActor(flappyActor);
        addActor(powerOff);

        addActor(linkActorLabel);
        addActor(fireworkActorLabel);
        addActor(boxActorLabel);
        addActor(flappyActorLabel);
    }

    private Label.LabelStyle getLabelStyle()
    {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = game.getMyAssetManager().getFont(trebuchet);
        style.fontColor = Color.WHITE;
        return style;
    }

    public void init()
    {


    }

    private Date date = new Date();
    private Calendar calendar = GregorianCalendar.getInstance();

    @Override
    public void act(float delta) {
        super.act(delta);
        setClock();

    }

    private void setClock()
    {
        calendar.setTime(date);
        clockLabel.setText(calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        clockLabel.setAlignment(0);
        clockLabel.setX(clock.getX() + clock.getWidth()/2 - clockLabel.getWidth()/2);
        clockLabel.setY(clock.getY() + clock.getHeight()/2 - clockLabel.getHeight()/2);
    }

    @Override
    protected void resized() {

    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
