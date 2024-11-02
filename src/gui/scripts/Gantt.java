package gui.scripts;

import gui.utilities.Button;
import gui.utilities.Cursor;
import gui.utilities.Object;
import gui.utilities.Text;

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
    LinkedList<Object> displayGantt;
    Table table;
    int xGantt, yGantt;

    Gantt(int width, int height, Cursor cursor, Assets assets, Table table) {
        visible = false;
        this.width = width;
        this.height = height;

        this.cursor = cursor;
        this.table = table;

        xGantt = 93;
        yGantt = 177;

        assets.gantt(width, height);
        objects = assets.objects.get("GANTT");
        buttons = assets.buttons.get("GANTT");
        objectGroups = assets.objectGroups.get("GANTT");

        display = new LinkedList<>();
        display.addAll(objects.values());
        display.addAll(buttons.values());
        display.sort(Comparator.comparingDouble(Object::getZ));

        displayGantt = new LinkedList<>();
    }

    public void draw(Graphics2D gg) {
        if (!visible) return;

        script();

        for (Object o : display) o.draw(gg);
//        for (Object o : objectGroups.get("RECTANGLES")) o.draw(gg);
        for (Object o : displayGantt) o.draw(gg);
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
    firstComeFirstServe.Gantt gantt2;
    public void start() {
        gantt = new roundRobin.Gantt(table.programs, table.timeQuantum);
        gantt2 = new firstComeFirstServe.Gantt(table.programs);

        LinkedList<Object> rectangles = new LinkedList<>();
        LinkedList<Text> texts = new LinkedList<>();

        if (!buttons.get("TITLE").on) {
            for (int i = 0; i < gantt2.time.size(); i++) {
                if (gantt2.time.get(i).getProgram() != null) {
                    rectangles.add(new Object(
                            new ImageIcon("src/gui/assets/rectangles/rect (" + ((programIndex(gantt2.time.get(i).getProgram().getName()) % 10) + 1) + ").png").getImage(),
                            xGantt + i * 30 + 2 * i, yGantt, 30, 100, 1)
                    );

                    if (i == 0) texts.add(new Text(
                            xGantt, yGantt - 4, 1,
                            "P" + (programIndex(gantt2.time.get(i).getProgram().getName()) + 1), 12, Color.WHITE
                    ));
                    else {
                        if (gantt2.time.get(i - 1).getProgram() != null && !Objects.equals(gantt2.time.get(i).getProgram().getName(), gantt2.time.get(i - 1).getProgram().getName()))
                            texts.add(new Text(
                                xGantt + i * 30 + 2 * i, yGantt - 4, 1,
                                "P" + (programIndex(gantt2.time.get(i).getProgram().getName()) + 1), 12, Color.WHITE
                        ));
                        else if (gantt2.time.get(i - 1).getProgram() == null) texts.add(new Text(
                                xGantt + i * 30 + 2 * i, yGantt - 4, 1,
                                "P" + (programIndex(gantt2.time.get(i).getProgram().getName()) + 1), 12, Color.WHITE
                        ));
                    }
                }
                texts.add(new Text(
                        (xGantt + i * 30 + 2 * i) - 10, yGantt + 114, 1,
                        i + "", 12, Color.WHITE
                ));
            }
//            texts.add(new Text(
//                    (xGantt + (gantt2.time.size()) * 30 + 2 * (gantt2.time.size())) - 10, yGantt + 114, 1,
//                    (gantt2.time.size()) + "", 12, Color.WHITE
//            ));

            int timeQueuedTotal = 0;
            int timeElapsedTotal = 0;
            for (int i = 0; i < gantt2.programListCopy.size(); i++) {
                texts.add(new Text(
                        130, 430 + (i * 40), 1,
                        (i + 1) + "", 18, Color.WHITE
                ));
                int a = 140, b = a;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        gantt2.programListCopy.get(i).getTimeIn() + "", 18, Color.WHITE
                ));
                a += b;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        gantt2.programListCopy.get(i).getDuration() + "", 18, Color.WHITE
                ));
                a += b;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        gantt2.programListCopy.get(i).getTimeOut() + "", 18, Color.WHITE
                ));
                a += b;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        (gantt2.programListCopy.get(i).getTimeOut() - gantt2.programListCopy.get(i).getTimeIn()) + "", 18, Color.WHITE
                ));
                timeElapsedTotal += gantt2.programListCopy.get(i).getTimeOut() - gantt2.programListCopy.get(i).getTimeIn();
                a += b;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        gantt2.programListCopy.get(i).getTimeQueued() + "", 18, Color.WHITE
                ));
                timeQueuedTotal += gantt2.programListCopy.get(i).getTimeQueued();
            }
            texts.add(new Text(
                    690, 430 + (gantt2.programListCopy.size() * 40), 1,
                    String.format("%.2f", ((double) timeElapsedTotal / gantt2.programListCopy.size())), 18, Color.WHITE
            ));
            texts.add(new Text(
                    830, 430 + (gantt2.programListCopy.size() * 40), 1,
                    String.format("%.2f", ((double) timeQueuedTotal / gantt2.programListCopy.size())), 18, Color.WHITE
            ));
            rectangles.add(new Object(
                    new ImageIcon("src/gui/assets/AVERAGE.png").getImage(),
                    493, 450 + ((gantt2.programListCopy.size() - 1) * 40), 130, 30, 1
            ));
        }
        else {
            for (int i = 0; i < gantt.time.size(); i++) {
                if (gantt.time.get(i).getProgram() != null) {
                    rectangles.add(new Object(
                            new ImageIcon("src/gui/assets/rectangles/rect (" + ((programIndex(gantt.time.get(i).getProgram().getName()) % 10) + 1) + ").png").getImage(),
                            xGantt + i * 30 + 2 * i, yGantt, 30, 100, 1)
                    );

                    if (i == 0) texts.add(new Text(
                            xGantt, yGantt - 4, 1,
                            "P" + (programIndex(gantt.time.get(i).getProgram().getName()) + 1), 12, Color.WHITE
                    ));
                    else if (i > 1 && gantt.time.get(i - 1).getProgram() != null && !Objects.equals(gantt.time.get(i).getProgram().getName(), gantt.time.get(i - 1).getProgram().getName())) {
                        texts.add(new Text(
                                xGantt + i * 30 + 2 * i, yGantt - 4, 1,
                                "P" + (programIndex(gantt.time.get(i).getProgram().getName()) + 1), 12, Color.WHITE
                        ));
                    }
                }
                texts.add(new Text(
                        (xGantt + i * 30 + 2 * i) - 10, yGantt + 114, 1,
                        i + "", 12, Color.WHITE
                ));
            }
//            texts.add(new Text(
//                    (xGantt + (gantt.time.size() - 1) * 30 + 2 * (gantt.time.size() - 1)) - 10, yGantt + 112, 1,
//                    (gantt.time.size() - 1) + "", 12, Color.WHITE
//            ));

            int timeQueuedTotal = 0;
            int timeElapsedTotal = 0;
            for (int i = 0; i < gantt.programListCopy.size(); i++) {
                texts.add(new Text(
                        130, 430 + (i * 40), 1,
                        (i + 1) + "", 18, Color.WHITE
                ));
                int a = 140, b = a;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        gantt.programListCopy.get(i).getTimeIn() + "", 18, Color.WHITE
                ));
                a += b;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        gantt.programListCopy.get(i).getDuration() + "", 18, Color.WHITE
                ));
                a += b;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        gantt.programListCopy.get(i).getTimeOut() + "", 18, Color.WHITE
                ));
                a += b;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        (gantt.programListCopy.get(i).getTimeOut() - gantt.programListCopy.get(i).getTimeIn()) + "", 18, Color.WHITE
                ));
                timeElapsedTotal += gantt.programListCopy.get(i).getTimeOut() - gantt.programListCopy.get(i).getTimeIn();
                a += b;
                texts.add(new Text(
                        130 + a, 430 + (i * 40), 1,
                        gantt.programListCopy.get(i).getTimeQueued() + "", 18, Color.WHITE
                ));
                timeQueuedTotal += gantt.programListCopy.get(i).getTimeQueued();
            }
            texts.add(new Text(
                    690, 430 + (gantt.programListCopy.size() * 40), 1,
                    String.format("%.2f", ((double) timeElapsedTotal / gantt.programListCopy.size())), 18, Color.WHITE
            ));
            texts.add(new Text(
                    830, 430 + (gantt.programListCopy.size() * 40), 1,
                    String.format("%.2f", ((double) timeQueuedTotal / gantt.programListCopy.size())), 18, Color.WHITE
            ));
            rectangles.add(new Object(
                    new ImageIcon("src/gui/assets/AVERAGE.png").getImage(),
                    493, 450 + ((gantt.programListCopy.size() - 1) * 40), 130, 30, 1
            ));
        }

        displayGantt.clear();
        displayGantt.addAll(rectangles);
        displayGantt.addAll(texts);
    }

    private int programIndex(String s) {
        for (int i = 0; i < table.programs.size(); i++) if (Objects.equals(table.programs.get(i).getName(), s)) return i;
        return -1;
    }
}
