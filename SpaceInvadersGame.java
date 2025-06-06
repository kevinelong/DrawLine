import java.awt.*;
import javax.swing.*;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyListener; // For handling keyboard events
import java.awt.event.KeyEvent; // For KeyEvent constants

public class SpaceInvadersGame implements KeyListener { // Implement KeyListener

    private JFrame frame;
    private Canvas canvas;
    private BufferStrategy bufferStrategy;
    private boolean running = true; // Flag to control the game loop
    private long lastTime; // For frame rate control

    // Game state variables (placeholder)
    private int playerX = 300;
    private int playerY = 500;
    private boolean moveLeft = false;
    private boolean moveRight = false;
    private int playerSpeed = 5;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SpaceInvadersGame().run();
            }
        });
    }

    public void run() {
        frame = new JFrame("Space Invaders");
        canvas = new Canvas(); // Use a Canvas for drawing

        // Set up canvas and frame (size, layout, add canvas to frame)
        canvas.setPreferredSize(new Dimension(800, 600)); // Set preferred size
        frame.getContentPane().add(canvas); // Add canvas to the frame's content pane
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        frame.pack(); // Size the frame to fit the canvas

        // Get GraphicsDevice for full-screen mode
        GraphicsEnvironment gfxEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gfxDev = gfxEnv.getDefaultScreenDevice();

        // Check if full-screen mode is supported
        if (gfxDev.isFullScreenSupported()) {
//            frame.setUndecorated(true); // Remove window decorations for full-screen
            gfxDev.setFullScreenWindow(frame); // Enter full-screen mode
        } else {
            // Handle cases where full-screen is not supported
            System.err.println("Full-screen mode not supported on this device.");
            frame.setLocationRelativeTo(null); // Center the window if not in full-screen
        }

        // Add KeyListener for input (or use Key Bindings)
        canvas.addKeyListener(this);

        canvas.createBufferStrategy(2); // Use a BufferStrategy for rendering
        bufferStrategy = canvas.getBufferStrategy();

        lastTime = System.currentTimeMillis(); // Initialize lastTime

        // Start your game loop (in a separate thread)
        new Thread(() -> {
            while (running) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = currentTime - lastTime;
                lastTime = currentTime;

                // Update game state (placeholder)
                updateGame(elapsedTime); // Pass elapsed time to update logic

                // Draw to the buffer strategy
                renderGame();

                // Show the next buffer
                bufferStrategy.show();

                // Simple frame rate control (adjust as needed)
                try {
                    Thread.sleep(16); // Aim for roughly 60 FPS (1000ms / 60 frames)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Dispose of resources when the game loop ends
            frame.dispose();
        }).start();

        frame.setVisible(true);
    }

    // Placeholder update method
    private void updateGame(long elapsedTime) {
        // Implement game logic here:
        // - Move player based on key presses
        // - Move aliens
        // - Handle bullet movement
        // - Check for collisions

        // Example: Move the player based on input flags
        if (moveLeft) {
            playerX -= playerSpeed;
        }
        if (moveRight) {
            playerX += playerSpeed;
        }

        // Add bounds checking for the player
        if (playerX < 0) {
            playerX = 0;
        } else if (playerX > canvas.getWidth() - 50) { // Assuming player width is 50
            playerX = canvas.getWidth() - 50;
        }
    }

    // Placeholder render method
    private void renderGame() {
        Graphics2D g2d = (Graphics2D) bufferStrategy.getDrawGraphics(); // Get graphics context

        // Clear the canvas
        g2d.setColor(Color.BLACK); // Background color
        g2d.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // Draw game elements (placeholder):
        // - Draw player
        // - Draw aliens
        // - Draw bullets
        // - Draw score, etc.

        // Example: Draw the player
        g2d.setColor(Color.GREEN); // Player color
        g2d.fillRect(playerX, playerY, 50, 50); // Player rectangle (placeholder size)

        g2d.dispose(); // Dispose of the graphics context
    }

    // Implement KeyListener methods
    @Override
    public void keyTyped(KeyEvent e) {
        // Not used in this basic example
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            moveLeft = true;
        } else if (key == KeyEvent.VK_RIGHT) {
            moveRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            moveLeft = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            moveRight = false;
        } else if (key == KeyEvent.VK_ESCAPE) { // Add an escape key to exit
            running = false;
        }
    }
}
