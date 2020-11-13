package hu.csanyzeg.master.Demos.FlappyBird;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

import hu.csanyzeg.master.Demos.LoadingStage.DemoLoadingStage;
import hu.csanyzeg.master.Demos.LoadingStage.DemoPreLoadingStage;
import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.PrettyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.ResponseViewport;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleOverlapsUtil;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;


public class FlappyStage extends PrettyStage {
    public static String vcrFont = "demoflappy/vcr_font.ttf";
    public static String flappyFont = "demoflappy/flappyfont.ttf";
    private static String wing = "demoflappy/sfx_wing.mp3";
    private static String coin = "demoflappy/sfx_coin.mp3";
    private static String hit = "demoflappy/sfx_hit.mp3";
    private static String swoosh = "demoflappy/sfx_swoosh.mp3";
    private static String die = "demoflappy/sfx_die.mp3";
    private static String blue = "blue.png";
    private static String menu = "demoflappy/menuButton.png";
    private static String play = "demoflappy/play.png";

    public static Preferences flappySave;

    public static AssetList assetList = new AssetList();
    static {
        assetList.addFont(vcrFont, vcrFont, 40, Color.WHITE, AssetList.CHARS).protect = true;
        assetList.addFont(flappyFont, flappyFont, 40, Color.WHITE, AssetList.CHARS).protect = true;
        assetList.addSound(wing);
        assetList.addSound(coin);
        assetList.addSound(hit);
        assetList.addSound(swoosh);
        assetList.addSound(die);
        assetList.addTexture(blue);
        assetList.addTexture(menu);
        assetList.addTexture(play);
        AssetList.collectAssetDescriptor(CityActor.class, assetList);
        AssetList.collectAssetDescriptor(MenuButton.class, assetList);
        AssetList.collectAssetDescriptor(BirdActor.class, assetList);
        AssetList.collectAssetDescriptor(PipeActor.class, assetList);
        AssetList.collectAssetDescriptor(GroundActor.class, assetList);
        AssetList.collectAssetDescriptor(GameOverActor.class, assetList);
        AssetList.collectAssetDescriptor(ScoreTable.class, assetList);
    }

    private BirdActor birdActor;
    private CityActor cityActor;
    private PipeActor felcso;
    private PipeActor lecso;
    private GroundActor groundActor;
    private OneSpriteStaticActor fade;
    private OneSpriteStaticActor white;
    private MyLabel scoreLabel;

    private int score;

    public FlappyStage(final MyGame game) {
        super(new FitViewport(320,490), game);
        addBackButtonScreenBackByStackPopListenerWithPreloadedAssets(new DemoPreLoadingStage(game));
    }

    @Override
    public void assignment()
    {
        flappySave = Gdx.app.getPreferences("flappySave");
        if(!flappySave.contains("highscore"))
        {
            flappySave.putInteger("highscore",0);
            flappySave.flush();
        }
        fade = new OneSpriteStaticActor(game,blue);
        white = new OneSpriteStaticActor(game, DemoLoadingStage.bgHash);
        fade.setTouchable(null);
        white.setTouchable(null);
        alpha = 1;
        alphaWhite = 0;
        alphaWhiteIncrease = true;
        fade.setColor(0,0,0,alpha);
        game.getMyAssetManager().getSound(swoosh).play();
        isGameOver = false;
        random = new Random();
        birdActor = new BirdActor(game);
        cityActor = new CityActor(game,getViewport());
        felcso = new PipeActor(game);
        lecso = new PipeActor(game);
        felcso = new PipeActor(game);
        lecso = new PipeActor(game);
        groundActor = new GroundActor(game);
        groundActor.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight()*0.2f);
        score = 0;
        addedBack = false;

        scoreLabel = new MyLabel(game, "0", getLabelStyle()) {
            @Override
            public void init() {

            }
        };
    }

    @Override
    public void setSizes() {
        fade.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
        white.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
    }

    @Override
    public void setPositions() {
        felcso.setX(600);
        lecso.setX(600);
        lecso.setY((float) (Math.random() * 100 + 300));
        felcso.setY(lecso.getY()-felcso.getHeight()-180);
        felcso.setRotation(180);
        birdActor.setPosition(30,getViewport().getWorldHeight()*0.7f);
        white.setAlpha(0);
        scoreLabel.setAlignment(0);
        scoreLabel.setPosition(getViewport().getWorldWidth()/2-scoreLabel.getWidth()/2,getViewport().getWorldHeight()*0.9f);
    }

    private Label.LabelStyle getLabelStyle()
    {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = game.getMyAssetManager().getFont(flappyFont);
        style.fontColor = Color.WHITE;
        return style;
    }

    @Override
    public void addActors()
    {
        addActor(cityActor);
        addActor(felcso);
        addActor(lecso);
        addActor(groundActor);
        addActor(birdActor);
        addActor(fade);
        addActor(white);
        addActor(scoreLabel);
    }

    @Override
    public void addListeners()
    {
        cityActor.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(!isGameOver) {
                    birdActor.setY(birdActor.getY() + 50);
                    birdActor.setRotation(25);
                    game.getMyAssetManager().getSound(wing).play();
                }
            }
        });
    }

    @Override
    public void setZIndexes() {

    }


    private Random random;

    @Override
    public void act(float delta) {
        super.act(delta);
        setFade();
        gameOver();
        madarFoldon();
        csovekKimentek();
        setWhiteFade();
    }

    private float alpha;

    private void setFade()
    {
        if(alpha > 0.025) {
            alpha -= 0.025;
            fade.setAlpha(alpha);
        }
        else fade.remove();
    }

    private float alphaWhite;
    private boolean alphaWhiteIncrease;

    private void setWhiteFade()
    {
        if(isGameOver)
        {
            white.setAlpha(alphaWhite);
            if(alphaWhite > 0.5) alphaWhiteIncrease = false;
            if(alphaWhiteIncrease) alphaWhite += 0.05;
            else if(!alphaWhiteIncrease && alphaWhite > 0.05) alphaWhite -= 0.05;
            else alphaWhite = 0;
        }
    }

    private void csovekKimentek()
    {
        //Ha a csövek kimentek a képernyőből, kapnak új random pozíciót, illetve növelem a pontszámot
        if (felcso.getX() < -felcso.getWidth() && !isGameOver) {
            lecso.setY(random.nextFloat() * 100 + 300);
            felcso.setY(lecso.getY() - felcso.getHeight() - 130);
            score++;
            game.getMyAssetManager().getSound(coin).play();
            scoreLabel.setText(score);
            scoreLabel.setPosition(getViewport().getWorldWidth()/2-scoreLabel.getWidth()/2,getViewport().getWorldHeight()*0.9f);
        }
    }

    private boolean addedBack;

    private void madarFoldon()
    {
        //Ha a madár leesett a földre, akkor semmi nem mozog
        if(birdActor.getY() <= groundActor.getY() + groundActor.getHeight() - 10) {
            birdActor.setAct(false);
            if(birdActor.getRotation() > 0) birdActor.setRotation(birdActor.getRotation()-20);
            if(!addedBack) {
                addTimers();
                game.getMyAssetManager().getSound(swoosh).play();
                addedBack = true;
            }
        }
        else if (isGameOver) {
            birdActor.setRotation((birdActor.getRotation() + 10) % 360);//Ha még nem esett le a földre, akkor folyamatosan forogjon
            birdActor.setY(birdActor.getY()-5);
        }
    }

    private void addTimers()
    {
        addTimer(new TickTimer(0.5f, false, new TickTimerListener() {
            @Override
            public void onTick(Timer sender, float correction) {
                addActor(new GameOverActor(game,getViewport()));
                addTimer(new TickTimer(0.5f, false, new TickTimerListener() {

                    @Override
                    public void onTick(Timer sender, float correction) {
                        if(flappySave.getInteger("highscore") < score)
                        {
                            flappySave.putInteger("highscore",score);
                            flappySave.flush();
                        }
                        addActor(new ScoreTable(game,getViewport(),score,flappySave.getInteger("highscore")));
                        addTimer(new TickTimer(0.5f, false, new TickTimerListener() {

                            @Override
                            public void onTick(Timer sender, float correction) {
                                addActor(new OneSpriteStaticActor(game,play)
                                {
                                    @Override
                                    public void init() {
                                        super.init();
                                        setSize(getWidth()*2,getHeight()*2);
                                        setPosition(getViewport().getWorldWidth()/2-getWidth()/2,getViewport().getWorldHeight()/2-getHeight()*1.5f);
                                        addListener(new ClickListener()
                                        {
                                            @Override
                                            public void clicked(InputEvent event, float x, float y) {
                                                super.clicked(event, x, y);
                                                game.setScreen(new FlappyScreen(game),false);
                                            }
                                        });
                                    }
                                });
                                addActor(new OneSpriteStaticActor(game,menu)
                                {
                                    @Override
                                    public void init() {
                                        super.init();
                                        setSize(getWidth()*2,getHeight()*2);
                                        setPosition(getViewport().getWorldWidth()/2-getWidth()/2,getViewport().getWorldHeight()/2-getHeight()*4);
                                        addListener(new ClickListener()
                                        {
                                            @Override
                                            public void clicked(InputEvent event, float x, float y) {
                                                super.clicked(event, x, y);
                                                game.setScreenBackByStackPopWithPreloadAssets(new DemoPreLoadingStage(game));
                                            }
                                        });
                                    }
                                });
                            }
                        }));
                    }
                }));
            }
        }));
    }

    private boolean isGameOver;

    private void gameOver()
    {
        if((SimpleOverlapsUtil.overlaps(birdActor,lecso) || SimpleOverlapsUtil.overlaps(birdActor,felcso) || SimpleOverlapsUtil.overlaps(birdActor,groundActor)) && !isGameOver)
        {//Hogyha ütközik valamelyik csővel vagy talajjal, akkor veszít a játékos
            isGameOver = true;
            felcso.setAct(false);
            lecso.setAct(false);
            cityActor.setTouchable(null);
            game.getMyAssetManager().getSound(hit).play();
            game.getMyAssetManager().getSound(die).play();
        }

    }
}
