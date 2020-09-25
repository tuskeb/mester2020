package hu.csanyzeg.master.NewGame;

import hu.csanyzeg.master.MyBaseClasses.Assets.AssetList;
import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimer;
import hu.csanyzeg.master.MyBaseClasses.Timers.TickTimerListener;
import hu.csanyzeg.master.MyBaseClasses.Timers.Timer;

public class SnakeHead extends OneSpriteStaticActor {
    public static String asset =  "box2dhelper/box.png";

    public static AssetList assetList = new AssetList();

    static {
        assetList.addTexture(asset);
    }




    public SnakeHead(MyGame game) {
        super(game, asset);
        setSize(10,10);
        TickTimer tickTimer = new TickTimer(1, true, new TickTimerListener(){
            @Override
            public void onTick(Timer sender, float correction) {
                super.onTick(sender, correction);
                setX(getX() + getWidth());
            }
        });
        addTimer(tickTimer);
    }

}
