package gui.scripts;

import gui.utilities.Cursor;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel implements Runnable {
    Thread thread;
    int width, height;
    Cursor cursor;
    MainMenu mainMenu;

    Panel(int width, int height, Assets assets) {
        thread = new Thread(this);
        thread.start();

        this.width = width;
        this.height = height;

        cursor = new Cursor(this);

        mainMenu = new MainMenu(width, height, cursor, assets);
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

        mainMenu.draw(gg);
        cursor.draw(gg);

        gg.dispose();
    }
}