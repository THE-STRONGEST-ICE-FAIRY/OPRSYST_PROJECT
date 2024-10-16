package gui.scripts;

import gui.utilities.Cursor;
import gui.utilities.Button;
import gui.utilities.Object;
import gui.utilities.Text;

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
    boolean visible;
    int width, height;
    Cursor cursor;
    HashMap<String, Object> objects;
    HashMap<String, Button> buttons;
    LinkedList<Object> display;
    RoundRobin roundRobin;
    FirstComeFirstServe firstComeFirstServe;
    About about;

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

    public void setPages(RoundRobin roundRobin, FirstComeFirstServe firstComeFirstServe, About about) {
        this.roundRobin = roundRobin;
        this.firstComeFirstServe = firstComeFirstServe;
        this.about = about;
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

        button = buttons.get("TEXT BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("ABOUT BUTTON");
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

        button = buttons.get("TEXT BUTTON");
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

            if (optionPane.getValue() != null && (int) optionPane.getValue() == 0) {
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

        cursor.clickCD = 5;
        cursor.click = false;
    }
}
