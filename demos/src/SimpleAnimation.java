import java.awt.*;
import javax.swing.*;

public class SimpleAnimation extends JPanel {
    private int x = 0; // x-coordinate of the shape

    public SimpleAnimation() {
        Timer timer = new Timer(50, e -> { // Timer to update the animation
            x++; // Move the shape to the right
            if (x > getWidth()) { // Reset if it goes off-screen
                x = 0;
            }
            repaint(); // Request a repaint to show the updated position
        });
        timer.start(); // Start the timer
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        g.fillRect(x, 50, 50, 50); // Draw a red rectangle at the current x position
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simple Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SimpleAnimation());
        frame.setSize(400, 200);
        frame.setVisible(true);
    }
}
