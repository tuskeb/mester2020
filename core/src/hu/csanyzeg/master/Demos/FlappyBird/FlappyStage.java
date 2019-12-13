package hu.csanyzeg.master.Demos.FlappyBird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

import hu.csanyzeg.master.Demos.Menu.MenuButton;
import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

import static hu.csanyzeg.master.MyBaseClasses.Scene2D.MyActor.overlaps;

public class FlappyStage extends MyStage {
    private static String vcrFont = "demoflappy/vcr_font.ttf";
    private static String wing = "demoflappy/sfx_wing.mp3";
    private static String coin = "demoflappy/sfx_coin.mp3";
    private static String hit = "demoflappy/sfx_hit.mp3";
    private static String swoosh = "demoflappy/sfx_swoosh.mp3";
    private static String die = "demoflappy/sfx_die.mp3";
    private static String blue = "blue.png";

    public static AssetList assetList = new AssetList();
    static {
        assetList.addFont(vcrFont, vcrFont, 40, Color.WHITE, AssetList.CHARS).protect = true;
        assetList.addSound(wing);
        assetList.addSound(coin);
        assetList.addSound(hit);
        assetList.addSound(swoosh);
        assetList.addSound(die);
        assetList.addTexture(blue);
        AssetList.collectAssetDescriptor(CityActor.class, assetList);
        AssetList.collectAssetDescriptor(MenuButton.class, assetList);
        AssetList.collectAssetDescriptor(BirdActor.class, assetList);
        AssetList.collectAssetDescriptor(PipeActor.class, assetList);
        AssetList.collectAssetDescriptor(GroundActor.class, assetList);
    }

    private BirdActor birdActor;
    private CityActor cityActor;
    private PipeActor felcso;
    private PipeActor lecso;
    private MenuButton menuButton;
    private GroundActor groundActor;
    private MyLabel scoreLabel;
    private MyLabel gameOverLabel;
    private OneSpriteStaticActor fade;

    private int score;

    public FlappyStage(final MyGame game) {
        super(new FitViewport(320,490), game);
        assignment();
        setSizesAndPositions();
        addListeners();
        addActors();
    }

    private void assignment()
    {
        fade = new OneSpriteStaticActor(game,blue);
        fade.setTouchable(null);
        alpha = 1;
        fade.setColor(0,0,0,alpha);
        game.getMyAssetManager().getSound(swoosh).play();
        isGameOver = false;
        random = new Random();
        birdActor = new BirdActor(game);
        cityActor = new CityActor(game);
        felcso = new PipeActor(game);
        lecso = new PipeActor(game);
        felcso = new PipeActor(game);
        lecso = new PipeActor(game);
        groundActor = new GroundActor(game);
        groundActor.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight()*0.2f);
        menuButton = new MenuButton(game,"Vissza a menübe");
        score = 0;
        addedBack = false;
        scoreLabel = new MyLabel("0", getLabelStyle()) {
            @Override
            public void init() {

            }
        };
        gameOverLabel = new MyLabel("Vége a játéknak!", getLabelStyle()) {
            @Override
            public void init() {

            }
        };
    }

    private Label.LabelStyle getLabelStyle()
    {
        Label.LabelStyle style;
        style = new com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle();
        style.font = game.getMyAssetManager().getFont(vcrFont);
        style.fontColor = Color.WHITE;
        return style;
    }

    private void setSizesAndPositions()
    {
        menuButton.setPosition(getViewport().getWorldWidth()/2-menuButton.getWidth()/2,getViewport().getWorldHeight()/2-menuButton.getHeight()/2);
        felcso.setX(600);
        lecso.setX(600);
        cityActor.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
        lecso.setY((float) (Math.random() * 100 + 300));
        felcso.setY(lecso.getY()-felcso.getHeight()-180);
        felcso.setRotation(180);
        birdActor.setPosition(30,getViewport().getWorldHeight()*0.7f);
        scoreLabel.setAlignment(0);
        gameOverLabel.setAlignment(0);
        gameOverLabel.setFontScale(0.7f);
        scoreLabel.setPosition(getViewport().getWorldWidth()/2-scoreLabel.getWidth()/2,getViewport().getWorldHeight()*0.9f);
        gameOverLabel.setPosition(getViewport().getWorldWidth()/2-gameOverLabel.getWidth()/2,getViewport().getWorldHeight()/2+gameOverLabel.getHeight()/2);
        fade.setSize(getViewport().getWorldWidth(),getViewport().getWorldHeight());
    }

    private void addActors()
    {
        addActor(cityActor);
        addActor(felcso);
        addActor(lecso);
        addActor(groundActor);
        addActor(birdActor);
        addActor(scoreLabel);
        addActor(fade);
    }

    private void addListeners()
    {
        cityActor.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                birdActor.setY(birdActor.getY()+50);
                birdActor.setRotation(25);
                game.getMyAssetManager().getSound(wing).play();
            }
        });
        menuButton.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                game.setScreenBackByStackPop();
            }
        });
    }

    @Override
    public void init() {

    }

    private Random random;

    @Override
    public void act(float delta) {
        super.act(delta);
        setFade();
        gameOver();
        madarFoldon();
        csovekKimentek();
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

    private void csovekKimentek()
    {
        //Ha a csövek kimentek a képernyőből, kapnak új random pozíciót, illetve növelem a pontszámot
        if (felcso.getX() < -felcso.getWidth() && !isGameOver) {
            lecso.setY(random.nextFloat() * 100 + 300);
            felcso.setY(lecso.getY() - felcso.getHeight() - 130);
            score++;
            game.getMyAssetManager().getSound(coin).play();
            scoreLabel.setText(score);
        }
    }

    private boolean addedBack;

    private void madarFoldon()
    {
        //Ha a madár leesett a földre, akkor semmi nem mozog
        if(birdActor.getY() <= groundActor.getY() + groundActor.getHeight() - 10) {
            felcso.setAct(false);
            lecso.setAct(false);
            birdActor.setAct(false);
            if(birdActor.getRotation() > 0) birdActor.setRotation(birdActor.getRotation()-20);
            if(!addedBack) {
                addActor(gameOverLabel);
                addActor(menuButton);
                game.getMyAssetManager().getSound(swoosh).play();
                addedBack = true;
            }
        }
        else if (isGameOver) birdActor.setRotation((birdActor.getRotation()+10)%360);//Ha még nem esett le a földre, akkor folyamatosan forogjon
    }

    private boolean isGameOver;

    private void gameOver()
    {
        if((overlaps(birdActor,lecso) || overlaps(birdActor,felcso) || overlaps(birdActor,groundActor)) && !isGameOver)
        {//Hogyha ütközik valamelyik csővel vagy talajjal, akkor veszít a játékos
            isGameOver = true;
            cityActor.setTouchable(null);
            game.getMyAssetManager().getSound(hit).play();
            game.getMyAssetManager().getSound(die).play();
        }
    }
}
