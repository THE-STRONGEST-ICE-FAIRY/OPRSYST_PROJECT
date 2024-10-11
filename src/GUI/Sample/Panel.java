package GUI.Sample;

import GUI.Utilities.Cursor;
import GUI.Utilities.Object;
import GUI.Utilities.Button;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable {
    int width, height;
    Thread thread;
    Cursor cursor;
    Object fumo;
    Button fumoButton;

    Panel(int width, int height) {
        this.width = width;
        this.height = height;

        thread = new Thread(this);
        thread.start();

        cursor = new Cursor(this);

        fumo = new Object(new ImageIcon("src/GUI/Assets/fumo.jpeg").getImage(), 200, 200, 100, 100);
        fumoButton = new Button(new ImageIcon("src/GUI/Assets/fumo.jpeg").getImage(), new ImageIcon("src/GUI/Assets/fumo2.jpeg").getImage(), 500, 500, 100, 100);
    }

    public void run() {
        double drawInterval = (double) 1000000000 / 60;
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (thread != null) {
            repaint();

            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000;
                if (remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gg = (Graphics2D) g;

        fumo.draw(gg);
        fumoButton.draw(gg);
        cursor.draw(gg);

        gg.dispose();
    }
}
