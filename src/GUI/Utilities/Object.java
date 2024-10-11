package GUI.Utilities;

import java.awt.*;

public class Object {
    protected Image image;
    protected int x, y, w, h;

    public Object() {
        this.image = null;
        this.x = 0;
        this.y = 0;
        this.w = 0;
        this.h = 0;
    }

    public Object(Image image, int x, int y, int w, int h) {
        this.image = image;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
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
}
