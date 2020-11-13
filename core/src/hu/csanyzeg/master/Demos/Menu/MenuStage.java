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
import hu.csanyzeg.master.Demos.SpaceInvaders.SpaceScreen;
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
import static hu.csanyzeg.master.Demos.Menu.Menu.invadersTexture;

/**
 * Created by tuskeb on 2016. 09. 30..
 * Windows XP Theme by hdani1337 on 2019 Christmas
 */
//TODO KÓDOT TAKARÍTANI ÉS NORMÁLISAN MEGCSINÁLNI MERT EZ UNDORÍTÓ
public class MenuStage extends MyStage {
    public static String blissTexture = "demomenu/bliss.jpg";
    public static String kekTexture = "demomenu/kek.jpg";
    public static String startTexture = "demomenu/start.jpg";
    public static String clockTexture = "demomenu/clock.jpg";
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
        assetList.addTexture(boxTexture);
        assetList.addTextureAtlas(birdTexture);
        assetList.addTexture(shutdownTexture);
        assetList.addSound(shutdownSound);
        assetList.addTexture(shutdownWallpaperTexture);
        assetList.addTexture(invadersTexture);
        assetList.addFont(trebuchet,trebuchet, 16, Color.WHITE);
    }

    //-----ASZTAL-----
    OneSpriteStaticActor bliss;//Háttérkép
    OneSpriteStaticActor kek;//Tálca
    OneSpriteStaticActor start;//Start menü gombja
    OneSpriteStaticActor clock;//Óra háttere
    Menu menu;//Start menü
    MyLabel clockLabel;//Óra
    OneSpriteStaticActor shutdown;//A Windows leállítása...
    OneSpriteStaticActor shutdownWall;//A kikapcsolóképernyő háttere
    //-----ASZTAL VÉGE-----

    private boolean show = true;//Szerepeljen e a Start menü és a parancsikonok a képernyőn

    public MenuStage(final MyGame game) {
        //super(new ResponseViewport(720), game);
        super(new ResponseViewport(720), game);
        addBackButtonListener(new BackButtonListener() {
            @Override
            public void backKeyDown() {
                closeExplorer();//Ha megnyomjuk a vissza gombot, akkor ,,leállítjuk a gépet"
            }
        });
        makeDesktop();//Az asztal definiálása
        showExplorer();//Az asztal megnyitása
        BootStage.firstBoot = false;//Többször ne hozza be az Üdvözöljük képernyőt
        start.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(show)
                {//Ha a Start menü rajt a stagen, akkor levesszük
                    menu.remove();
                    show = false;
                }
                else
                {//Ha a Start menü nincs rajt a stagen, akkor visszarakjuk
                    addActor(menu);
                    show = true;
                }
            }
        });
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

        menu = new Menu(game);
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
        menu.setPosition(0,kek.getY()+kek.getHeight());
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
                addActor(menu);
                for (MenuItemType type : MenuItemType.values())
                    menu.addItem(type);
            }
        }));
    }

    public void closeExplorer()
    {
        //Először a Start menüt távolítjuk el
        addTimer(new TickTimer(0.5f, false, new TickTimerListener() {
            @Override
            public void onTick(Timer sender, float correction) {
                menu.remove();
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
    public void dispose() {
        super.dispose();

    }
}
