package clvrclvr.src;

import java.util.ArrayList;
import java.util.TimerTask;

public class GameTask extends TimerTask {
    ArrayList<Sprite> spriteList;
    Main game;
    GameTask(ArrayList<Sprite> spriteList, Main game){
        this.game = game;
        this.spriteList = spriteList;
    }
    @Override
    public void run() {
        spriteList.forEach(s -> s.update(100));
        game.paint(game.getGraphics());
    }
}
