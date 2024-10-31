package gui.scripts;

import gui.utilities.Button;
import gui.utilities.Cursor;
import gui.utilities.Object;

import javax.swing.*;
import java.awt.*;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;

public class Gantt {
    boolean visible;
    int width, height;
    Cursor cursor;
    HashMap<String, Object> objects;
    HashMap<String, Button> buttons;
    HashMap<String, LinkedList<Object>> objectGroups;
    LinkedList<Object> display;
    Table table;

    Gantt(int width, int height, Cursor cursor, Assets assets, Table table) {
        visible = false;
        this.width = width;
        this.height = height;

        this.cursor = cursor;
        this.table = table;

        assets.gantt(width, height);
        objects = assets.objects.get("GANTT");
        buttons = assets.buttons.get("GANTT");
        objectGroups = assets.objectGroups.get("GANTT");

        display = new LinkedList<>();
        display.addAll(objects.values());
        display.addAll(buttons.values());
        display.sort(Comparator.comparingDouble(Object::getZ));
    }

    public void draw(Graphics2D gg) {
        if (!visible) return;

        script();

        for (Object o : display) o.draw(gg);
//        for (Object o : objectGroups.get("RECTANGLES")) o.draw(gg);
    }

    public void script() {
        Button button = buttons.get("TABLE BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        if (cursor.click) click();
    }

    public void click() {
        Button button = buttons.get("TABLE BUTTON");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");

            table.visible = true;
            visible = false;
        }

        cursor.clickCD = 5;
        cursor.click = false;
    }

    roundRobin.Gantt gantt;
    public void startRoundRobin() {
        gantt = new roundRobin.Gantt(table.programs, table.timeQuantum);

        LinkedList<Object> rectangles = new LinkedList<>();
        for (int i = 0; i < gantt.time.size(); i++) {
            if (gantt.time.get(i).getProgram() != null) {
                rectangles.add(new Object(
                        new ImageIcon("src/gui/assets/rectangles/rect (" + ((programIndex(gantt.time.get(i).getProgram().getName()) % 10) + 1) + ").png").getImage(),
                        i * 30 + 5 * i, height/2, 30, 100, 1)
                );
            }
        }

        display.addAll(rectangles);
    }

    private int programIndex(String s) {
        for (int i = 0; i < table.programs.size(); i++) if (Objects.equals(table.programs.get(i).getName(), s)) return i;
        return -1;
    }
}
