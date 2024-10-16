package gui.scripts;

import gui.utilities.Button;
import gui.utilities.Cursor;
import gui.utilities.Object;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class About {
    boolean visible;
    int width, height;
    Cursor cursor;
    HashMap<String, Object> objects;
    HashMap<String, Button> buttons;
    LinkedList<Object> display;
    MainMenu mainMenu;

    About(int width, int height, Cursor cursor, Assets assets, MainMenu mainMenu) {
        visible = false;
        this.width = width;
        this.height = height;

        this.cursor = cursor;
        this.mainMenu = mainMenu;

        assets.about(width, height);
        objects = assets.objects.get("ABOUT");
        buttons = assets.buttons.get("ABOUT");

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
        Button button = buttons.get("MAIN MENU BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        if (cursor.click) click();
    }

    public void click() {
        Button button = buttons.get("MAIN MENU BUTTON");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");

            mainMenu.visible = true;
            visible = false;
        }

        cursor.clickCD = 5;
        cursor.click = false;
    }
}
