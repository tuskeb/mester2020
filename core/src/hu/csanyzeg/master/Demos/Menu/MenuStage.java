package hu.csanyzeg.master.Demos.Menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import hu.csanyzeg.master.Demos.Box2dHelper.Box2dHelperScreen;
import hu.csanyzeg.master.Demos.Box2dJoin.Box2dJoinScreen;
import hu.csanyzeg.master.Demos.Firework.FireworkScreen;
import hu.csanyzeg.master.Demos.FlappyBird.FlappyScreen;
import hu.csanyzeg.master.Demos.LoadingStage.DemoLoadingStage;
import hu.csanyzeg.master.Demos.LoadingStage.DemoPreLoadingStage;
import hu.csanyzeg.master.Demos.SimpleClock.SWScreen;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingListener;
import hu.csanyzeg.master.MyBaseClasses.Assets.LoadingStage;
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

/**
 * Created by tuskeb on 2016. 09. 30..
 * Windows XP Theme by hdani1337 on 2019 Christmas
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
    public static String simpleClockTexture = "demomenu/simpleClock.png";

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
        assetList.addTexture(simpleClockTexture);
        assetList.addFont(trebuchet,trebuchet, 16, Color.WHITE);
    }

    //-----ASZTAL-----
    OneSpriteStaticActor bliss;//Háttérkép
    OneSpriteStaticActor kek;//Tálca
    OneSpriteStaticActor start;//Start menü gombja
    OneSpriteStaticActor clock;//Óra háttere
    OneSpriteStaticActor startMenu;//Üres Start menü
    MyLabel clockLabel;//Óra
    //-----ASZTAL VÉGE-----

    //-----START MENÜ IKONOK ÉS SZÖVEGEK-----
    OneSpriteStaticActor linkActor;//Box2D Join ikonja
    OneSpriteStaticActor fireworkActor;//Firework Demo ikonja
    OneSpriteStaticActor boxActor;//Box2D Helper ikonja
    OneSpriteAnimatedActor flappyActor;//Flappy Demo ikonja
    OneSpriteStaticActor powerOff;//Kikapcsológomb
    OneSpriteStaticActor simpleActor;//SimpleWorld ikonja
    MyLabel linkActorLabel;//Box2D Join szövege
    MyLabel fireworkActorLabel;//Firework Demo szövege
    MyLabel boxActorLabel;//Box2D Helper szövege
    MyLabel flappyActorLabel;//Flappy Demo szövege
    MyLabel simpleActorLabel;//SimpleWorld szövege
    OneSpriteStaticActor shutdown;//A Windows leállítása...
    OneSpriteStaticActor shutdownWall;//A kikapcsolóképernyő háttere
    //-----START MENÜ IKONOK ÉS SZÖVEGEK VÉGE-----

    private boolean show = true;//Szerepeljen e a Start menü és a parancsikonok a képernyőn

    private ArrayList<MyActor> buttons = new ArrayList<>();//Start menü ikonjainak listája
    private ArrayList<MyLabel> labels = new ArrayList<>();//Start menü szövegeinek listája

    public MenuStage(final MyGame game) {
        super(new ResponseViewport(720), game);
        addBackButtonListener(new BackButtonListener() {
            @Override
            public void backKeyDown() {
                closeExplorer();//Ha megnyomjuk a vissza gombot, akkor ,,leállítjuk a gépet"
            }
        });
        makeDesktop();//Az asztal definiálása
        makeButtons();//A Start menü gombjainak (actorok és mellettük lévő labelök) definiálása
        showExplorer();//Az asztal megnyitása
        BootStage.firstBoot = false;//Többször ne hozza be az Üdvözöljük képernyőt
        start.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(show)
                {//Ha a Start menü rajt a stagen, akkor levesszük őt, az ikonokat és a szövegeket is
                    startMenu.remove();
                    for (MyActor myActor : buttons) myActor.remove();
                    for (MyLabel myLabel : labels) myLabel.remove();
                    show = false;
                }
                else
                {//Ha a Start menü nincs rajt a stagen, akkor visszarakjuk őt, az ikonokat és a szövegeket is
                    addActor(startMenu);
                    for (MyActor myActor : buttons) addActor(myActor);
                    for (MyLabel myLabel : labels) addActor(myLabel);
                    show = true;
                }
            }
        });

        /**
         * Ezeket nem adom hozzá a menühöz, mert nem találtam hozzájuk illő ikont, illetve még semmi jelentős nincs bennük
         *
        addActor(new MenuButton(game, "Szakkör Demo"){
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
        game.getMyAssetManager().getSound(shutdownSound).play();//Windows XP Shutdown hangeffekt

        addTimer(new TickTimer(3f, false, new TickTimerListener() {
            //A hangeffekt lejátszása után 3 másodperc késéssel fut le az onTick() metódus

            @Override
            public void onTick(Timer sender, float correction) {
                BootStage.firstBoot = true;//Visszaállítom a firstBootot, hogy bejöjjön az Üdvözöljük képernyő
                game.setScreenBackByStackPop();//A program kilépési pontja
            }

        }));
    }

    private void makeDesktop()
    {
        clockLabel = new MyLabel(game, "12:00",getLabelStyle()) {
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
        addActor(bliss);//A hátteret azonnal hozzáadjuk a stagehez

        if(getViewport().getWorldWidth() > bliss.getWidth()) bliss.setWidth(getViewport().getWorldWidth());
        if(getViewport().getWorldHeight() > bliss.getHeight()) bliss.setHeight(getViewport().getWorldHeight());
        kek.setWidth(getViewport().getWorldWidth());
        clock.setWidth(clock.getWidth()*0.5f);
        clock.setX(kek.getX() + kek.getWidth() - clock.getWidth());
        clockLabel.setAlignment(0);
        clockLabel.setX(clock.getX() + clock.getWidth()/2 - clockLabel.getWidth()/2);
        clockLabel.setY(clock.getY() + clock.getHeight()/2 - clockLabel.getHeight()/2);
        startMenu.setPosition(0,kek.getY()+kek.getHeight());
        startMenu.setSize(startMenu.getWidth()*1.3f, startMenu.getHeight()*1.3f);
    }

    private void showExplorer()
    {
        //Fél másodperc elteltével jöhet a tálca és az óra
        addTimer(new TickTimer(0.5f, false, new TickTimerListener() {

            @Override
            public void onTick(Timer sender, float correction) {
                addActor(kek);
                addActor(start);
                addActor(clock);
                addActor(clockLabel);
            }

        }));

        //Még fél másodperc múlva jön a Start menü és az ikonok
        addTimer(new TickTimer(1, false, new TickTimerListener() {


            @Override
            public void onTick(Timer sender, float correction) {
                addActor(startMenu);
                for (MyActor myActor : buttons) addActor(myActor);
                for (MyLabel myLabel : labels) addActor(myLabel);
            }

        }));

    }

    private void closeExplorer()
    {
        //Először a Start menüt távolítjuk el
        addTimer(new TickTimer(0.5f, false, new TickTimerListener() {

            @Override
            public void onTick(Timer sender, float correction) {
                startMenu.remove();
                for (MyActor myActor : buttons) myActor.remove();
                for (MyLabel myLabel : labels) myLabel.remove();
            }
        }));

        //Utána eltűnik minden, csak a háttérkép marad
        addTimer(new TickTimer(1f, false, new TickTimerListener() {

            @Override
            public void onTick(Timer sender, float correction) {
                kek.remove();
                start.remove();
                clock.remove();
                clockLabel.remove();
            }

        }));

        //Leállítás
        addTimer(new TickTimer(1.5f, false, new TickTimerListener() {

            @Override
            public void onTick(Timer sender, float correction) {
                shutdown();
            }

        }));
    }

    ClickListener linkListener;//Box2D Join listener
    ClickListener fireworkListener;//Firework Demo listener
    ClickListener boxListener;//Box2D Helper listener
    ClickListener flappyListener;//Flappy Demo listener
    ClickListener clockListener;//SimpleWorld listener

    private void makeButtons()
    {
        //-----BOX2D JOIN-----
        linkListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenWithPreloadAssets(Box2dJoinScreen.class, new DemoPreLoadingStage(game));
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
            linkActorLabel = new MyLabel(game, "B2D Join Demo", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, linkActor.getY() - linkActor.getHeight()/2 + 8);
                    setFontScale(1.5f);
                    setColor(Color.BLACK);
                    addListener(linkListener);
                    labels.add(this);
                }
            };
        //----------


        //-----FIREWORK DEMO-----
        fireworkListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new FireworkScreen(game));
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

            fireworkActorLabel = new MyLabel(game, "Firework Demo", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, fireworkActor.getY() + 8);
                    setColor(Color.BLACK);
                    setFontScale(1.5f);
                    addListener(fireworkListener);
                    labels.add(this);
                }
            };
        //----------


        //-----BOX2D HELPER-----
        boxListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenWithPreloadAssets(Box2dHelperScreen.class, new DemoPreLoadingStage(game));
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

            boxActorLabel = new MyLabel(game, "Box2D Helper", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, boxActor.getY() + 10);
                    setColor(Color.BLACK);
                    setFontScale(1.5f);
                    addListener(boxListener);
                    labels.add(this);
                }
            };
        //----------


        //-----FLAPPY DEMO-----
        flappyListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenWithPreloadAssets(FlappyScreen.class, new DemoPreLoadingStage(game));
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

            flappyActorLabel = new MyLabel(game, "Flappy Demo", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, flappyActor.getY()+1);
                    setColor(Color.BLACK);
                    setFontScale(1.5f);
                    addListener(flappyListener);
                    labels.add(this);
                }
            };
        //----------


        //-----SIMPLE WORLD-----
        clockListener = new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreen(new SWScreen(game));
            }
        };

        simpleActor = new OneSpriteStaticActor(game, simpleClockTexture){
            @Override
            public void init() {
                super.init();
                setPosition(linkActor.getX(),flappyActor.getY() + flappyActor.getHeight() + 15);
                setSize(32*1.3f,(32/getWidth()*getHeight())*1.3f);
                addListener(clockListener);
                buttons.add(this);
            }
        };

            simpleActorLabel = new MyLabel(game, "Simple Clock", getLabelStyle()) {
                @Override
                public void init() {
                    setPosition(55, simpleActor.getY() + 10);
                    setColor(Color.BLACK);
                    setFontScale(1.5f);
                    addListener(clockListener);
                    labels.add(this);
                }
            };
        //----------


        //-----KIKAPCSOLÓGOMB-----
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
        //----------
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

    private Date date = new Date();//Dátum új példánya
    private Calendar calendar = GregorianCalendar.getInstance();//Gergely naptár új példánya

    @Override
    public void act(float delta) {
        super.act(delta);
        setClock();//Az óra beállítása
    }

    private void setClock()
    {
        String time = "";
        calendar.setTime(date);

        if(calendar.get(Calendar.HOUR_OF_DAY) < 10) time += "0";//Ha 10 óránál kevesebb az idő, akkor egy 0-t rakunk az óra elé
        time += calendar.get(Calendar.HOUR_OF_DAY) + ":";//Hozzáadjuk az időhöz az órát és egy kettőspontot
        if(calendar.get(Calendar.MINUTE) < 10) time += "0";//Ha 10 percnél kevesebb perc van, akkor egy 0-t rakunk a perc elé
        time += calendar.get(Calendar.MINUTE);//Hozzáadjuk az időhöz a percet

        clockLabel.setText(time);//Az óra label szövege a time értéke lesz

        //-----KÖZÉPRE HELYEZZÜK AZ ÓRÁT-----
        clockLabel.setAlignment(0);
        clockLabel.setX(clock.getX() + clock.getWidth()/2 - clockLabel.getWidth()/2);
        clockLabel.setY(clock.getY() + clock.getHeight()/2 - clockLabel.getHeight()/2);
        //-----KÖZÉPRE HELYEZÉS VÉGE-----
    }

    @Override
    protected void resized() {

    }

    @Override
    public void dispose() {
        super.dispose();

    }
}
