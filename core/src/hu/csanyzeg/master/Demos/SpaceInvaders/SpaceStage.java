package hu.csanyzeg.master.Demos.SpaceInvaders;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleBody;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleContact;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorld;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldContactListener;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleWorldStage;

public class SpaceStage extends SimpleWorldStage {

    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(EnemyActor.class, assetList);
        AssetList.collectAssetDescriptor(EnemyBulletActor.class, assetList);
        AssetList.collectAssetDescriptor(StarshipBulletActor.class, assetList);
        AssetList.collectAssetDescriptor(ExplosionActor.class, assetList);
    }

    public SpaceStage(MyGame game) {
        super(new ExtendViewport(160, 96), game);
        for (int k = 0; k < 3; k++) {
            for (int i = 0; i < 8; i++) {
                addActor(new EnemyActor(game, world, i * 16, k * 10 + 64));
            }
        }
        StarShipActor starShipActor = new StarShipActor(game, world, 80,1);
        addActor(starShipActor);
        addListener(new ClickListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                starShipActor.moveTo(x);

                return super.touchDown(event, x, y, pointer, button);
            }
        });
    }
}
