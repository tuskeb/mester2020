package hu.csanyzeg.master.Demos.Menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import java.util.ArrayList;

import hu.csanyzeg.master.MyBaseClasses.Game.MyGame;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.MyGroup;
import hu.csanyzeg.master.MyBaseClasses.Scene2D.OneSpriteStaticActor;

public class Menu extends MyGroup {
    public static String fireworkTexture = "demomenu/firework.png";
    public static String startMenuTopTexture = "demomenu/startmenu_top.png";
    public static String startMenuMidTexture = "demomenu/startmenu_mid.png";
    public static String startMenuBotTexture = "demomenu/startmenu_bottom.png";
    public static String powerOffTexture = "demomenu/poweroff.png";
    public static String trebuchet = "demomenu/trebuc.ttf";
    public static String simpleClockTexture = "demomenu/simpleClock.png";
    public static String invadersTexture = "spaceinvaders/enemy1.png";

    private OneSpriteStaticActor menuTop;
    private OneSpriteStaticActor menuMid;
    private OneSpriteStaticActor menuBot;

    private OneSpriteStaticActor shutdownButton;

    private ArrayList<MenuItem> items;

    public Menu(MyGame game) {
        super(game);
        basicStuff();
        setPositions();
        addActors();
    }

    private void basicStuff(){
        menuTop = new OneSpriteStaticActor(game,startMenuTopTexture);
        menuMid = new OneSpriteStaticActor(game,startMenuMidTexture);
        menuBot = new OneSpriteStaticActor(game,startMenuBotTexture);

        shutdownButton = new OneSpriteStaticActor(game,powerOffTexture);
        shutdownButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(getStage() != null && getStage() instanceof MenuStage)
                    ((MenuStage)getStage()).closeExplorer();
            }
        });

        items = new ArrayList<>();
    }

    private void setPositions(){
        float szorzo = 1.03f;

        menuBot.setSize(menuBot.getWidth()*szorzo, menuBot.getHeight()*szorzo);
        menuMid.setSize(menuMid.getWidth()*szorzo, menuMid.getHeight()*szorzo);
        menuTop.setSize(menuTop.getWidth()*szorzo, menuTop.getHeight()*szorzo);
        shutdownButton.setSize(shutdownButton.getWidth()*szorzo, shutdownButton.getHeight()*szorzo);

        menuBot.setPosition(0,0);
        shutdownButton.setPosition(menuBot.getX()+menuBot.getWidth()-shutdownButton.getWidth(),menuBot.getY());
        menuMid.setPosition(menuBot.getX(),menuBot.getY()+menuBot.getHeight());
        menuTop.setPosition(menuMid.getX(),menuMid.getY()+menuMid.getHeight());
    }

    private void addActors(){
        addActor(menuBot);
        addActor(menuMid);
        addActor(menuTop);

        addActor(shutdownButton);
    }

    public void addItem(MenuItemType itemType){
        items.add(new MenuItem(game,itemType));

        float height = 0;
        for (MenuItem item : items)
            height += item.getHeight()+18;

        items.get(0).setY(menuMid.getY()-15);

        if(items.size()>0)
            for (int i = 1; i < items.size(); i++)
                items.get(i).setY(items.get(i-1).getY()+25);

        menuMid.setHeight(height);
        addActor(items.get(items.size()-1));
        setPositions();
    }
}
