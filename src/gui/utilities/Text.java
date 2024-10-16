package gui.utilities;

import java.awt.*;

public class Text extends Object {
    String text;
    int size;
    Color color;

    public Text(int x, int y, int z, String text, int size, Color color) {
        super(x, y, z);

        this.text = text;
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics2D gg) {
        gg.setColor(color);
        gg.setFont(new Font("Arial", Font.PLAIN, size));
        gg.drawString(text, x, y);
        gg.setColor(Color.BLACK);
    }

    public void setText(String text) {
        this.text = text;
    }
}
