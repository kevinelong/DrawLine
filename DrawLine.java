import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

class CustomCanvas extends Canvas {
    int step = 10;
    int x1 = 10;
    int y1 = 10;
    int x2 = 10;
    int y2 = 130;
    int i = 0;

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawLine(x1, y1, x2, y2);
    }
    public void updateStep(){
        if(i < 12) {
            y1 += step;
            x2 += step;
            paint(this.getGraphics());
        }
        i++;
    }
}
class CustomTask extends TimerTask{
    CustomCanvas canvas;
    CustomTask(CustomCanvas canvas){
        super();
        this.canvas = canvas;
    }
    public void run() {
        canvas.updateStep();
    }
}
public class DrawLine implements KeyListener  {
    static CustomCanvas canvas;
    DrawLine(){
        Frame frame = new Frame("Draw Line Example");
        frame.setSize(300, 200);
        frame.setBackground(Color.BLACK);
        canvas = new CustomCanvas();
//        var timer = new Timer();
//        timer.scheduleAtFixedRate(new CustomTask(canvas), 1000, 1000);

        frame.add(canvas);
        canvas.addKeyListener(this);
        frame.addKeyListener(this);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
        DrawLine app = new DrawLine();
    }


    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("keyTyped" + e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("keyPressed" + e);
        canvas.updateStep();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("keyReleased" + e);
    }

}