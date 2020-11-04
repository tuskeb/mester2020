package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;

import java.text.DecimalFormat;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.SimpleUI.SimpleLabel;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;
import hu.csanyzeg.master.MyBaseClasses.Timers.MultiTickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.MultiTickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.OneTickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

public class SpaceStage extends SimpleWorldStage {


    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(EnemyActor.class, assetList);
        AssetList.collectAssetDescriptor(EnemyBulletActor.class, assetList);
        AssetList.collectAssetDescriptor(StarshipBulletActor.class, assetList);
        AssetList.collectAssetDescriptor(ExplosionActor.class, assetList);
        AssetList.collectAssetDescriptor(StarShipActor.class, assetList);
        AssetList.collectAssetDescriptor(LevelLabelStyle.class, assetList);
        AssetList.collectAssetDescriptor(PointLabelStyle.class, assetList);
        assetList.addMusic("spaceinvaders/tetris.mp3");
        assetList.addSound("spaceinvaders/levelcomplete.mp3");

    }


    SimpleLabel pointSimpleLabel;
    SimpleLabel lifeSimpleLabel;
    StarShipActor starShipActor;

    Music music;

    int level = 1;
    int life = 3;
    protected int point = 10;


    public SpaceStage(MyGame game) {
        super(new ExtendViewport(1600, 960), game);

        SimpleLabel simpleLabel;

        addStarshipActor();

        simpleLabel = new SimpleLabel(game, world, "Space Invaders", new SmallLabelStyle());
        addActor(simpleLabel);
        simpleLabel.setPositionCenter(300);

        addTimer(new TickTimer(2, false, new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                newLevel();
                sender.remove();
            }
        }));

        music = game.getMyAssetManager().getMusic("spaceinvaders/tetris.mp3");
        music.setLooping(true);
        music.play();

        addActor(pointSimpleLabel = new SimpleLabel(game, world, "------", new PointLabelStyle()));
        pointSimpleLabel.setPosition(0,getViewport().getWorldHeight()-pointSimpleLabel.getHeight());

        lifeSimpleLabel = new SimpleLabel(game, world, "--", new PointLabelStyle());
        addActor(lifeSimpleLabel);
        lifeSimpleLabel.setPosition(getViewport().getWorldWidth() - lifeSimpleLabel.getWidth(), getViewport().getWorldHeight() - lifeSimpleLabel.getHeight());

        OneSpriteStaticActor lifeShipOneSpriteStaticActor = new OneSpriteStaticActor(game, StarShipActor.asset);
        addActor(lifeShipOneSpriteStaticActor);
        lifeShipOneSpriteStaticActor.setHeightWhithAspectRatio(lifeSimpleLabel.getHeight() / 2);
        lifeShipOneSpriteStaticActor.setPosition(lifeSimpleLabel.getX() - lifeShipOneSpriteStaticActor.getWidth(), lifeSimpleLabel.getY() + lifeSimpleLabel.getHeight() / 4);

        setCameraResetToLeftBottomOfScreen();

    }


    private void newLevel(){
        SimpleLabel simpleLabel;
        simpleLabel = new SimpleLabel(game, world, "Level "  + level, new LevelLabelStyle());
        addActor(simpleLabel);
        simpleLabel.setPositionCenter(600);

        setPoint(point);

        setLife(life);

        addTimer(new OneTickTimer(3, new OneTickTimerListener(){
            @Override
            public void onTick(OneTickTimer sender, float correction) {
                super.onTick(sender, correction);
                for (int k = 0; k < 1 + level; k++) {
                    for (int i = 0; i < 8; i++) {
                        addActor(new EnemyActor(game, world, i * 160, 800 - k * 100, 100 / level, 1f/level + 0.5f ));
                    }
                }

                addTimer(new TickTimer(1, true, new TickTimerListener(){
                    @Override
                    public void onTick(Timer sender, float correction) {
                        super.onTick(sender, correction);
                        int count = 0;
                        for(Actor actor : getActors()){
                            if (actor instanceof EnemyActor){
                                count++;
                            }
                        }
                        if (count == 0) {
                            sender.remove();
                            music.stop();
                            SimpleLabel s;
                            addActor(s = new SimpleLabel(game, world, "Complete", new LevelLabelStyle()));
                            s.setPositionCenter(600);
                            game.getMyAssetManager().getSound("spaceinvaders/levelcomplete.mp3").play();
                            addTimer(new MultiTickTimer(0.5f / level, level * 2, new MultiTickTimerListener(){
                                @Override
                                public void onTick(MultiTickTimer sender, float correction, int count) {
                                    super.onTick(sender, correction, count);
                                    addPoint(10);
                                }
                            }));

                            addTimer(new OneTickTimer(5,  new OneTickTimerListener(){
                                @Override
                                public void onTick(OneTickTimer sender, float correction) {
                                    super.onTick(sender, correction);
                                    music.setPosition(0);
                                    music.play();
                                    level++;
                                    newLevel();
                                }
                            }));

                        }
                    }
                }));
            }
        }));

    }

    protected void addStarshipActor(){
        starShipActor = new StarShipActor(game, world, 800,1);
        addActor(starShipActor);
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                starShipActor.moveTo(x);
                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }

    protected void gameOver(){
        SimpleLabel simpleLabel;
        simpleLabel = new SimpleLabel(game, world, "Game", new LevelLabelStyle());
        addActor(simpleLabel);
        simpleLabel.setPositionCenter(600);
        simpleLabel = new SimpleLabel(game, world, "Over", new LevelLabelStyle());
        addActor(simpleLabel);
        simpleLabel.setPositionCenter(300);
        music.stop();
        game.getMyAssetManager().getSound("spaceinvaders/levelcomplete.mp3").play();
    }



    public void starshipDeath(){
        setLife(life-1);
        if (life > 0) {
            addStarshipActor();
        }else{
            gameOver();
        }
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int p){
        pointSimpleLabel.setText(String.format("%6s", new DecimalFormat("000000").format(p)));
        point = p;
    }

    public void addPoint(int p){
        setPoint(point + p);
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        lifeSimpleLabel.setText("x" + (life % 10));
        this.life = life;
    }

    @Override
    public void dispose() {
        super.dispose();
        music.stop();
    }

    public int getLevel() {
        return level;
    }
}
