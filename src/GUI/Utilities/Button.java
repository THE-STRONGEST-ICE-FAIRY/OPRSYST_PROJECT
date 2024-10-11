package GUI.Utilities;

import java.awt.*;

public class Button extends Object{
    private boolean on;
    private final Image offImage;
    private final Image onImage;
    private Object hoverImage;

    public Button(Image offImage, Image onImage, int x, int y, int w, int h) {
        super(offImage, x, y, w, h);

        this.offImage = offImage;
        this.onImage = onImage;

        on = false;
    }

    public void setHoverImage(Image hoverImage) {
        this.hoverImage = new Object(hoverImage, x, y, w, h);
    }

    public void drawHover(Graphics2D gg) {
        hoverImage.draw(gg);
    }

    public void setOnOff() {
        on = !on;
        if (on) setImage(onImage);
        else setImage(offImage);
    }
}
