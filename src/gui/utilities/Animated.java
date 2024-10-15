package gui.utilities;

import java.awt.*;
import java.util.LinkedList;

public class Animated extends Object {
    LinkedList<Image> images;
    int current;
    int fpsDelay, max;
    public boolean play, check;

    public Animated(LinkedList<Image> images, int fpsDelay, int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fpsDelay = 0;
        max = fpsDelay;
        current = 0;

        this.images = images;
        image = images.get(current);
    }

    public void next() {
        image = images.get(++current % images.size());
    }

    public void prev() {image = images.get(--current % images.size());}

    public void loop() {
        if (fpsDelay++ == max) {
            current = ++current % images.size();
            fpsDelay = 0;
        }
        image = images.get(current);
    }

    public void play() {
        current = 0;
        play = true;
    }

    public void stop() {
        current = 0;
        play = false;
    }

    public void once() {
        if (!play) {
            check = false;
            return;
        }

        if (fpsDelay++ == max) {
            current++;
            current = current % images.size();
            fpsDelay = 0;
        }
        image = images.get(current);

        if (current == 0 && check) play = false;
        else if (current == 0) check = true;
    }
}
