import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ImageExample extends Frame {

    private BufferedImage image;

    public ImageExample(String imagePath) {
        try {
            // Load the image using ImageIO
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set frame properties
//        setTitle("AWT Image Example");
        setSize(image.getWidth()*3, image.getHeight()*3); // Set size to match image
        setVisible(true);

        // Add a window listener to close the frame
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        // Draw the image
        g.drawImage(image, 10, 40, this);
    }

    public static void main(String[] args) {
        // Replace "path/to/your/image.jpg" with the actual path to your image file
        new ImageExample("hero.png");
    }
}
