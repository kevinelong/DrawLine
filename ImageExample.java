import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
}
class Hero extends Sprite{
    Hero(int width, int height, String imagePath) {
        super(width, height, imagePath);
        this.y = height - (2 * image.getHeight());
    }
}

public class ImageExample extends Frame implements KeyListener {
    int width = 640;
    int height = 480;
    Hero hero;
    Sprite enemy;
    public ImageExample() {
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
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
    }

    public static void main(String[] args) {
        new ImageExample();
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
