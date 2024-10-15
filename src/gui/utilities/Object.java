package gui.utilities;

import java.awt.*;

public class Object {
    protected Image image;
    protected int x, y, w, h;
    int z;

    public Object() {
        this.image = null;
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.h = 0;
        this.z = 0;
    }

    public Object(Image image, int x, int y, int w, int h, int z) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.z = z;
    }

    public void draw(Graphics2D gg) {
        gg.drawImage(image, x, y, w, h, null);
    }

    public void gotoXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void resize(int w, int h) {
        this.w = w;
        this.h = h;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getZ() {
        return z;
    }

    public boolean hovering(int x, int y) {
        return x < this.x + w &&
                x > this.x &&
                y < this.y + h &&
                y > this.y;
    }
}
