package gui.scripts;

import gui.utilities.*;
import gui.utilities.Button;
import gui.utilities.Cursor;
import gui.utilities.Object;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class MainMenu {
    JFrame frame;
    boolean visible;
    int width, height;
    Cursor cursor;
    HashMap<String, Object> objects;
    HashMap<String, Button> buttons;
    LinkedList<Object> display;
    Table table;
    About about;

    MainMenu(int width, int height, Cursor cursor, Assets assets, JFrame frame) {
        this.frame = frame;
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

    public void setPages(Table table, About about) {
        this.table = table;
        this.about = about;
    }

    public void draw(Graphics2D gg) {
        if (!visible) return;

        script();

        for (Object o : display) o.draw(gg);
    }

    public void script() {
        Button button = buttons.get("TEXT BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("ABOUT BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("EXIT BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("TABLE BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("FOLDER FCFS");
        Button button2 = buttons.get("TEXT FCFS");
        button.setHovering(button2.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button2.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("FOLDER RR");
        button2 = buttons.get("TEXT RR");
        button.setHovering(button2.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button2.hovering(cursor.getX(), cursor.getY()));

        if (cursor.click) click();
    }

    public void click() {
        Button button = buttons.get("TEXT BUTTON");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");

            cursor.clickCD = 5;
            cursor.click = false;

            JTextArea textArea = new JTextArea(1, 30);
            JOptionPane optionPane = new JOptionPane(textArea, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
            JDialog dialog = optionPane.createDialog("Your Dumb Writable Pane");

            dialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowOpened(WindowEvent e) {
                    textArea.requestFocusInWindow(); // Request focus once the window is opened
                }
            });
            textArea.addKeyListener(new KeyAdapter() {
                public void keyPressed(KeyEvent e) {
                    if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                        optionPane.setValue(JOptionPane.OK_OPTION); // Simulate pressing OK
                        dialog.dispose(); // Close the dialog
                    }
                }
            });
            dialog.setVisible(true);

            if (optionPane.getValue() != null && (int) optionPane.getValue() == 0 && CheckStringForInt.canConvertToInt(textArea.getText())) {
                Text t = (Text) objects.get("TEXT");
                t.setText(textArea.getText());
            }
        }

        button = buttons.get("ABOUT BUTTON");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");

            about.visible = true;
            visible = false;
        }

        button = buttons.get("EXIT BUTTON");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");

            cursor.clickCD = 5;
            cursor.click = false;

            int choice = JOptionPane.showConfirmDialog(
                    frame,
                    "Are you sure you want to exit?",
                    "",
                    JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0); // Exit the application
            }
        }

        button = buttons.get("TEXT FCFS");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");

            table.updateTableFCFS();
            table.visible = true;
            table.fcfs = true;
            visible = false;
        }

        button = buttons.get("TEXT RR");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");

            table.updateTableRR();
            table.visible = true;
            table.fcfs = false;
            visible = false;
        }

        cursor.clickCD = 5;
        cursor.click = false;
    }
}
