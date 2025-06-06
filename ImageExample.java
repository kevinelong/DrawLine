import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class Hero{
    protected int x;
    protected int y;
    protected String imagePath = "hero.png";
    protected BufferedImage image;

    Hero(int width, int height){
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = (width / 2) - (image.getWidth()/2);
        this.y = height - (2 * image.getHeight());
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


public class ImageExample extends Frame {
    int width = 640;
    int height = 480;
    Hero hero;
    public ImageExample() {
        hero = new Hero(width, height);
        setBackground(Color.BLACK);
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
    }

    public static void main(String[] args) {
        new ImageExample();
    }
}
