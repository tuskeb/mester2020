package hu.csanyzeg.master.Demos.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import hu.csanyzeg.master.Demos.Actor.ActorScreen;
import hu.csanyzeg.master.Demos.Box2dHelper.Box2dHelperScreen;
import hu.csanyzeg.master.Demos.Box2dJoin.Box2dJoinScreen;
import hu.csanyzeg.master.Demos.Firework.FireworkScreen;
import hu.csanyzeg.master.Demos.FlappyBird.FlappyScreen;
import hu.csanyzeg.master.Demos.SimpleWorld.SWScreen;
import hu.csanyzeg.master.Demos.Szakkor.SzakkorScreen;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor;
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
    public static String cursorTexture = "demomenu/cursor.png";

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
        assetList.addTexture(cursorTexture);
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
    OneSpriteStaticActor simpleActor;

    OneSpriteStaticActor shutdown;
    OneSpriteStaticActor shutdownWall;

    MyLabel linkActorLabel;
    MyLabel fireworkActorLabel;
    MyLabel boxActorLabel;
    MyLabel flappyActorLabel;
    MyLabel simpleActorLabel;

    MyLabel clockLabel;

    boolean show = true;

    private ArrayList<MyActor> buttons = new ArrayList<>();
    private ArrayList<MyLabel> labels = new ArrayList<>();

    public MenuStage(final MyGame game) {
        super(new ResponseViewport(720), game);
        addBackButtonListener(new BackButtonListener() {
            @Override
            public void backKeyDown() {
                closeExplorer();
            }
        });
        makeDesktop();
        makeButtons();
        showExplorer();
        BootStage.firstBoot = false;
        start.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(show)
                {
                    startMenu.remove();
                    for (MyActor myActor : buttons) myActor.remove();
                    for (MyLabel myLabel : labels) myLabel.remove();
                    show = false;
                }
                else
                {
                    addActor(startMenu);
                    for (MyActor myActor : buttons) addActor(myActor);
                    for (MyLabel myLabel : labels) addActor(myLabel);
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
                BootStage.firstBoot = true;
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

        kek.setWidth(getViewport().getWorldWidth());
        clock.setWidth(clock.getWidth()*0.5f);
        clock.setX(kek.getX() + kek.getWidth() - clock.getWidth());
        clockLabel.setAlignment(0);
        clockLabel.setX(clock.getX() + clock.getWidth()/2 - clockLabel.getWidth()/2);
        clockLabel.setY(clock.getY() + clock.getHeight()/2 - clockLabel.getHeight()/2);
        startMenu.setPosition(0,kek.getY()+kek.getHeight());
        startMenu.setSize(startMenu.getWidth()*1.3f, startMenu.getHeight()*1.3f);
    }

    ClickListener linkListener;
    ClickListener fireworkListener;
    ClickListener boxListener;
    ClickListener flappyListener;
    ClickListener simpleListener;

    private void showExplorer()
    {

        addTimer(new TickTimer(0.5f, false, new TickTimerListener() {
            @Override
            public void onRepeat(TickTimer sender) {

            }

            @Override
            public void onTick(Timer sender, float correction) {
                addActor(kek);
                addActor(start);
                addActor(clock);
                addActor(clockLabel);
            }

            @Override
            public void onStop(Timer sender) {

            }

            @Override
            public void onStart(Timer sender) {

            }
        }));

        addTimer(new TickTimer(1, false, new TickTimerListener() {
            @Override
            public void onRepeat(TickTimer sender) {
            }

            @Override
            public void onTick(Timer sender, float correction) {
                addActor(startMenu);
                for (MyActor myActor : buttons) addActor(myActor);
                for (MyLabel myLabel : labels) addActor(myLabel);
            }

            @Override
            public void onStop(Timer sender) {

            }

            @Override
            public void onStart(Timer sender) {

            }
        }));

    }

    private void closeExplorer()
    {
        addTimer(new TickTimer(0.5f, false, new TickTimerListener() {
            @Override
            public void onRepeat(TickTimer sender) {
            }

            @Override
            public void onTick(Timer sender, float correction) {
                startMenu.remove();
                for (MyActor myActor : buttons) myActor.remove();
                for (MyLabel myLabel : labels) myLabel.remove();
            }

            @Override
            public void onStop(Timer sender) {

            }

            @Override
            public void onStart(Timer sender) {

            }
        }));

        addTimer(new TickTimer(1f, false, new TickTimerListener() {
            @Override
            public void onRepeat(TickTimer sender) {

            }

            @Override
            public void onTick(Timer sender, float correction) {
                kek.remove();
                start.remove();
                clock.remove();
                clockLabel.remove();
            }

            @Override
            public void onStop(Timer sender) {

            }

            @Override
            public void onStart(Timer sender) {

            }
        }));

        addTimer(new TickTimer(1.5f, false, new TickTimerListener() {
            @Override
            public void onRepeat(TickTimer sender) {

            }

            @Override
            public void onTick(Timer sender, float correction) {
                shutdown();
            }

            @Override
            public void onStop(Timer sender) {

            }

            @Override
            public void onStart(Timer sender) {

            }
        }));
    }

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

        simpleListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new SWScreen(game));
            }
        };

        linkActor = new OneSpriteStaticActor(game, linkTexture){
            @Override
            public void init() {
                super.init();
                setPosition(7,startMenu.getY() + 80);
                setSize(32*1.3f,(32/getWidth()*getHeight())*1.3f);
                addListener(linkListener);
                buttons.add(this);
            }
        };
            linkActorLabel = new MyLabel("B2D Join Demo", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, linkActor.getY() - linkActor.getHeight()/2 + 8);
                    setFontScale(1.5f);
                    setColor(Color.BLACK);
                    addListener(linkListener);
                    labels.add(this);
                }
            };


        fireworkActor = new OneSpriteStaticActor(game, fireworkTexture){
            @Override
            public void init() {
                super.init();
                setPosition(linkActor.getX(),linkActor.getY() + linkActor.getHeight() + 25);
                setSize(32*1.3f,(32/getWidth()*getHeight())*1.3f);
                addListener(fireworkListener);
                buttons.add(this);
            }
        };

            fireworkActorLabel = new MyLabel("Firework Demo", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, fireworkActor.getY() + 8);
                    setColor(Color.BLACK);
                    setFontScale(1.5f);
                    addListener(fireworkListener);
                    labels.add(this);
                }
            };

        boxActor = new OneSpriteStaticActor(game, boxTexture){
            @Override
            public void init() {
                super.init();
                setPosition(linkActor.getX(),fireworkActor.getY() + fireworkActor.getHeight() + 15);
                setSize(32*1.3f,(32/getWidth()*getHeight())*1.3f);
                addListener(boxListener);
                buttons.add(this);
            }
        };

            boxActorLabel = new MyLabel("Box2D Helper", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, boxActor.getY() + 10);
                    setColor(Color.BLACK);
                    setFontScale(1.5f);
                    addListener(boxListener);
                    labels.add(this);
                }
            };

        flappyActor = new OneSpriteAnimatedActor(game, birdTexture){
            @Override
            public void init() {
                super.init();
                setPosition(linkActor.getX(),boxActor.getY() + boxActor.getHeight() + 20);
                setFps(6);
                addListener(flappyListener);
                buttons.add(this);
            }
        };

            flappyActorLabel = new MyLabel("Flappy Demo", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, flappyActor.getY()+1);
                    setColor(Color.BLACK);
                    setFontScale(1.5f);
                    addListener(flappyListener);
                    labels.add(this);
                }
            };

        simpleActor = new OneSpriteStaticActor(game, boxTexture){
            @Override
            public void init() {
                super.init();
                setPosition(linkActor.getX(),flappyActor.getY() + flappyActor.getHeight() + 15);
                setSize(32*1.3f,(32/getWidth()*getHeight())*1.3f);
                addListener(simpleListener);
                buttons.add(this);
            }
        };

            simpleActorLabel = new MyLabel("Simple World", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, simpleActor.getY() + 10);
                    setColor(Color.BLACK);
                    setFontScale(1.5f);
                    addListener(simpleListener);
                    labels.add(this);
                }
            };

        powerOff = new OneSpriteStaticActor(game, powerOffTexture){
            @Override
            public void init() {
                super.init();
                setSize(getWidth()*1.3f,getHeight()*1.3f);
                setPosition(startMenu.getX() + startMenu.getWidth() - getWidth(), startMenu.getY());
                addListener(new ClickListener(){
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        closeExplorer();
                    }
                });
                buttons.add(this);
            }
        };
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
        String time = "";
        calendar.setTime(date);

        time += calendar.get(Calendar.HOUR_OF_DAY) + ":";
        if(calendar.get(Calendar.MINUTE) < 10) time += "0";
        time += calendar.get(Calendar.MINUTE);

        clockLabel.setText(time);
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
