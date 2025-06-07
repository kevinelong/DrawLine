import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sprite{
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
