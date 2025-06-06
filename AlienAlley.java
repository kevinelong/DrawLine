import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import javax.imageio.ImageIO;

class Sprite{
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String imagePath;
    protected BufferedImage image;

    Sprite(int width, int height, String imagePath){
        this.imagePath = imagePath;
        this.width = width;
        this.height = height;
        load();
    }
    protected void load(){
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = (width / 2) - (image.getWidth()/2);
        this.y = 2 * image.getHeight();
    }
    int getX(){
        return x;
    }

    int getY(){
        return y;
    }

    BufferedImage getImage(){
        return image;
    }

    void left(){
        this.x--;
    }

    void right(){
        this.x++;
    }

    void up(){
        this.y--;
    }

    void down(){
        this.y++;
    }
    void update(int ms){
        down();
    }
}
class Hero extends Sprite{
    Hero(int width, int height, String imagePath) {
        super(width, height, imagePath);
        this.y = height - (2 * image.getHeight());
    }
}
class GameTask extends TimerTask{
    Sprite sprite;
    AlienAlley game;
    GameTask(Sprite sprite, AlienAlley game){
        this.game = game;
        this.sprite = sprite;
    }
    @Override
    public void run() {
        sprite.update(100);
        game.paint(game.getGraphics());
    }
}
public class AlienAlley extends Frame implements KeyListener {
    int width = 640;
    int height = 480;
    Hero hero;
    Sprite enemy;
    public AlienAlley() {
        hero = new Hero(width, height, "hero.png");
        enemy = new Sprite(width, height, "alien.png");
        setBackground(Color.BLACK);
        addKeyListener(this);
        setSize(width, height); // Set size to match image
        setVisible(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        var timer = new Timer();
        GameTask task = new GameTask(enemy, this);
        timer.scheduleAtFixedRate(task, 10, 10);

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
    }

    public static void main(String[] args) {
        new AlienAlley();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped " + e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed " + e);
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            hero.left();
        }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            hero.right();
        }
        paint(this.getGraphics());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased " + e);
    }
}
