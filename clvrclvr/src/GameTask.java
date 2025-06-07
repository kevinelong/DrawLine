import java.util.TimerTask;

public class GameTask extends TimerTask {
    Sprite sprite;
    Main game;
    GameTask(Sprite sprite, Main game){
        this.game = game;
        this.sprite = sprite;
    }
    @Override
    public void run() {
        sprite.update(100);
        game.paint(game.getGraphics());
    }
}
