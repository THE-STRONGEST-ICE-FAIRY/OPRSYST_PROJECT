package gui.scripts;

import gui.utilities.Cursor;
import gui.utilities.Button;
import gui.utilities.Object;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class MainMenu {
    boolean visible;
    int width, height;
    Cursor cursor;
    HashMap<String, Object> objects;
    HashMap<String, Button> buttons;
    LinkedList<Object> display;

    MainMenu(int width, int height, Cursor cursor, Assets assets) {
        visible = true;
        this.width = width;
        this.height = height;

        this.cursor = cursor;

        assets.mainMenu(width, height);
        objects = assets.objects.get("MAIN MENU");
        buttons = assets.buttons.get("MAIN MENU");

        display = new LinkedList<>();
        display.addAll(objects.values());
        display.addAll(buttons.values());
        display.sort(Comparator.comparingDouble(Object::getZ));
    }

    public void draw(Graphics2D gg) {
        if (!visible) return;

        script();

        for (Object o : display) o.draw(gg);
    }

    public void script() {
        Button button = buttons.get("BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        if (cursor.click) click();
    }

    public void click() {
        Button button = buttons.get("BUTTON");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");
        }

        cursor.clickCD = 5;
        cursor.click = false;
    }
}
