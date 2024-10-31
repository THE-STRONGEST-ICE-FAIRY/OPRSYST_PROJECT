package gui.scripts;

import gui.utilities.Button;
import gui.utilities.Cursor;
import gui.utilities.Object;
import roundRobin.Program;

import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Table {
    boolean visible;
    int width, height;
    Cursor cursor;
    HashMap<String, Object> objects;
    HashMap<String, Button> buttons;
    LinkedList<Object> display;
    MainMenu mainMenu;
    Gantt gantt;
    int timeQuantum;
    LinkedList<Program> programs;

    Table(int width, int height, Cursor cursor, Assets assets, MainMenu mainMenu) {
        visible = false;
        this.width = width;
        this.height = height;

        this.cursor = cursor;
        this.mainMenu = mainMenu;
        gantt = new Gantt(width, height, cursor, assets, this);
        timeQuantum = 3;

        assets.table(width, height);
        objects = assets.objects.get("TABLE");
        buttons = assets.buttons.get("TABLE");

        display = new LinkedList<>();
        display.addAll(objects.values());
        display.addAll(buttons.values());
        display.sort(Comparator.comparingDouble(Object::getZ));

        programs = new LinkedList<>();
        programs.add(new Program("Program 1", 0, 5));
        programs.add(new Program("Program 2", 2, 9));
        programs.add(new Program("Program 3", 6, 1));
        programs.add(new Program("Program 4", 12, 2));
        programs.add(new Program("Program 5", 1, 10));
    }

    public void draw(Graphics2D gg) {
        gantt.draw(gg);
        if (!visible) return;

        script();

        for (Object o : display) o.draw(gg);
    }

    public void script() {
        Button button = buttons.get("MAIN MENU BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("GANTT BUTTON");
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

        button = buttons.get("GANTT BUTTON");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");

            gantt.startRoundRobin();
            gantt.visible = true;
            visible = false;
        }

        cursor.clickCD = 5;
        cursor.click = false;
    }
}
