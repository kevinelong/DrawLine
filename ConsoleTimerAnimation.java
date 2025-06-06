import java.util.Timer;
import java.util.TimerTask;

public class ConsoleTimerAnimation {

    public static void main(String[] args) {
        Timer timer = new Timer();
        final String[] frames = {"|", "/", "-", "\\"};
        final int[] frameIndex = {0};

        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                clearConsole();
                System.out.print("Loading: " + frames[frameIndex[0]]);
                frameIndex[0] = (frameIndex[0] + 1) % frames.length;
            }
        };

        timer.scheduleAtFixedRate(task, 0, 200); // Run every 200 milliseconds

        // Stop the timer after some time
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.cancel();
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}