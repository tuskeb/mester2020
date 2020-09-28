package hu.csanyzeg.master.Video;

import com.badlogic.gdx.utils.viewport.ExtendViewport;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyStage;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.ShapeType;
import hu.csanyzeg.master.MyBaseClasses.SimpleWorld.SimpleOverlapsUtil;

public class VideoStage extends MyStage {

    public static AssetList assetList = new AssetList();
    static {
        AssetList.collectAssetDescriptor(VideoActor.class, assetList);
        AssetList.collectAssetDescriptor(BoyActor.class, assetList);
    }

    private BoyActor boyActor;
    private VideoActor videoActor;

    public VideoStage(MyGame game) {
        super(new ExtendViewport(16, 9), game);
        setCameraResetToLeftBottomOfScreen();

        boyActor = new BoyActor(game);
        addActor(boyActor);

        addActor(videoActor = new VideoActor(game));
        videoActor.setX(5);
        videoActor.setRotation(40);

        //legoActor.setPositionCenterOfActorToCenterOfViewport();
        for(int i = 0; i<10; i++){

            LegoActor legoActor;
            legoActor = new LegoActor(game);
            addActor(legoActor);
            legoActor.setPositionCenter(i * 0.234375f);
        }
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (SimpleOverlapsUtil.overlaps(boyActor, ShapeType.Circle, videoActor, ShapeType.Circle)){
            boyActor.stopWalking();
        }
    }
}
