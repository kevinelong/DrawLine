package clvrclvr.src;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Main extends Frame implements KeyListener {
    int width = 640;
    int height = 480;
    Hero hero;
    Sprite enemy;
    public Main() {
        hero = new Hero(width, height, "hero.png");
        enemy = new Enemy(width, height, "alien.png");
        var spriteList = new ArrayList<Sprite>();
        spriteList.add(hero);
        spriteList.add(enemy);
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
        GameTask task = new GameTask(spriteList, this);
        timer.scheduleAtFixedRate(task, 0, 35);
    }

    @Override
    public void paint(Graphics g) {
        // Clear the screen to black
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);

        g.drawImage(hero.getImage(), hero.getX(), hero.getY(), this);
        g.drawImage(enemy.getImage(), enemy.getX(), enemy.getY(), this);
    }

    public static void main(String[] args) {
        new Main();
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
