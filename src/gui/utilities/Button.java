package gui.utilities;

import java.awt.*;

public class Button extends Object {
    private boolean on;
    private boolean lever;
    private final Image offImage;
    private final Image onImage;
    private Object hoverImage;
    boolean hovering;

    public Button(Image offImage, Image onImage, int x, int y, int w, int h, int z) {
        super(offImage, x, y, w, h, z);

        this.offImage = offImage;
        this.onImage = onImage;

        on = false;
    }

    public Button(int x, int y, int z) {
        super(null, x, y, 0, 0, z);

        this.offImage = null;
        this.onImage = null;

        on = false;
    }

    public void draw(Graphics2D gg) {
        gg.drawImage(image, x, y, w, h, null);
        if (hoverImage != null && hovering) hoverImage.draw(gg);
    }

    public void setHoverImage(Image hoverImage) {
        this.hoverImage = new Object(hoverImage, x, y, w, h, z);
    }

    public void setOnOff() {
        on = !on;
        if (!lever) return;
        if (on) setImage(onImage);
        else setImage(offImage);
    }

    public void setImageOnOff(boolean on) {
        if (lever) return;
        if (on) setImage(onImage);
        else setImage(offImage);
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    public void setLever(boolean lever) {
        this.lever = lever;
    }
}
