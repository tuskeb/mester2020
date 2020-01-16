package hu.csanyzeg.master.Demos.FlappyBird;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.UI.MyLabel;

import static hu.csanyzeg.master.Demos.FlappyBird.FlappyStage.flappyFont;
import static hu.csanyzeg.master.Demos.FlappyBird.FlappyStage.vcrFont;

public class ScoreTable extends MyGroup {
    public static final String scoreTableTexture = "demoflappy/scoreTable.png";
    public static AssetList assetList = new AssetList();
    static {
        assetList.addTexture(scoreTableTexture);
    }

    private MyLabel scoreLabel;
    private MyLabel highScoreLabel;
    private int score;
    private int highscore;

    private Viewport viewport;
    public ScoreTable(MyGame game, Viewport viewport, int score, int highscore) {
        super(game);
        this.viewport = viewport;
        this.score = score;
        this.highscore = highscore;
        this.labelsSetted = false;
        setTable();
    }

    private OneSpriteStaticActor scoreTable;

    private void setTable()
    {
        scoreTable = new OneSpriteStaticActor(game, scoreTableTexture);
        scoreTable.setSize(scoreTable.getWidth()*1.75f,scoreTable.getHeight()*1.75f);
        scoreTable.setPosition(viewport.getWorldWidth()/2-scoreTable.getWidth()/2,-getHeight());
        addActor(scoreTable);
    }

    private boolean labelsSetted;

    private void setLabels()
    {
        if(!labelsSetted) {
            scoreLabel = new MyLabel(game, score + "", getLabelStyle()) {
                @Override
                public void init() {

                }
            };

            highScoreLabel = new MyLabel(game, highscore + "", getLabelStyle()) {
                @Override
                public void init() {

                }
            };

            scoreLabel.setAlignment(0);
            highScoreLabel.setAlignment(0);
            scoreLabel.setFontScale(0.6f);
            highScoreLabel.setFontScale(0.6f);

            highScoreLabel.setPosition(scoreTable.getX() + 15 + (highscore/10)*5, scoreTable.getY() + scoreTable.getHeight() * 0.3f);
            scoreLabel.setPosition(scoreTable.getX() + scoreTable.getWidth() - 45 - (highscore/10)*5, scoreTable.getY() + scoreTable.getHeight() * 0.3f);

            addActor(scoreLabel);
            addActor(highScoreLabel);
            labelsSetted = true;
        }
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
    public void act(float delta) {
        super.act(delta);
        if(scoreTable.getY() < viewport.getWorldHeight()*0.43) scoreTable.setY(scoreTable.getY()+20);
        else setLabels();
    }
}
