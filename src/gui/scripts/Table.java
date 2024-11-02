package gui.scripts;

import gui.utilities.*;
import gui.utilities.Button;
import gui.utilities.Cursor;
import gui.utilities.Object;
import roundRobin.Program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
    boolean fcfs; // rr if false
    HashMap<String, Object> objectsFCFS;
    HashMap<String, Button> buttonsFCFS;
    LinkedList<Object> displayFCFS;
    LinkedList<Object> displayFCFSTemp;
    LinkedList<Button> textBoxesArrivalFCFS;
    LinkedList<Text> textArrivalFCFS;
    LinkedList<Button> textBoxesBurstFCFS;
    LinkedList<Text> textBurstFCFS;
    LinkedList<Button> deleteButtonsFCFS;
    HashMap<String, Object> objectsRR;
    HashMap<String, Button> buttonsRR;
    LinkedList<Object> displayRR;
    LinkedList<Object> displayRRTemp;
    LinkedList<Button> textBoxesArrivalRR;
    LinkedList<Text> textArrivalRR;
    LinkedList<Button> textBoxesBurstRR;
    LinkedList<Text> textBurstRR;
    LinkedList<Button> deleteButtonsRR;
    int page, pageMax;
    int deleteCounter;

    Table(int width, int height, Cursor cursor, Assets assets, MainMenu mainMenu) {
        visible = false;
        this.width = width;
        this.height = height;

        this.cursor = cursor;
        this.mainMenu = mainMenu;
        gantt = new Gantt(width, height, cursor, assets, this);
        timeQuantum = 3;
        deleteCounter = 0;

        assets.table(width, height);
        objects = assets.objects.get("TABLE");
        buttons = assets.buttons.get("TABLE");
        objectsFCFS = assets.objects.get("TABLE FCFS");
        buttonsFCFS = assets.buttons.get("TABLE FCFS");
        objectsRR = assets.objects.get("TABLE RR");
        buttonsRR = assets.buttons.get("TABLE RR");

        display = new LinkedList<>();
        display.addAll(objects.values());
        display.addAll(buttons.values());
        display.sort(Comparator.comparingDouble(Object::getZ));
        displayFCFSTemp = new LinkedList<>();
        displayFCFS = new LinkedList<>();
        displayFCFS.addAll(objectsFCFS.values());
        displayFCFS.addAll(buttonsFCFS.values());
        displayFCFS.sort(Comparator.comparingDouble(Object::getZ));
        displayRRTemp = new LinkedList<>();
        displayRR = new LinkedList<>();
        displayRR.addAll(objectsRR.values());
        displayRR.addAll(buttonsRR.values());
        displayRR.sort(Comparator.comparingDouble(Object::getZ));

        textBoxesArrivalFCFS = new LinkedList<>();
        textArrivalFCFS = new LinkedList<>();
        textBoxesBurstFCFS = new LinkedList<>();
        textBurstFCFS = new LinkedList<>();
        deleteButtonsFCFS = new LinkedList<>();

        textBoxesArrivalRR = new LinkedList<>();
        textArrivalRR = new LinkedList<>();
        textBoxesBurstRR = new LinkedList<>();
        textBurstRR = new LinkedList<>();
        deleteButtonsRR = new LinkedList<>();

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
        if (fcfs) {
            for (Object o : textBoxesArrivalFCFS) o.draw(gg);
            for (Object o : textArrivalFCFS) o.draw(gg);
            for (Object o : textBoxesBurstFCFS) o.draw(gg);
            for (Object o : textBurstFCFS) o.draw(gg);
            for (Object o : deleteButtonsFCFS) o.draw(gg);
            for (Object o : displayFCFSTemp) o.draw(gg);
            for (Object o : displayFCFS) o.draw(gg);
        }
        else {
            for (Object o : textBoxesArrivalRR) o.draw(gg);
            for (Object o : textArrivalRR) o.draw(gg);
            for (Object o : textBoxesBurstRR) o.draw(gg);
            for (Object o : textBurstRR) o.draw(gg);
            for (Object o : deleteButtonsRR) o.draw(gg);
            for (Object o : displayRR) o.draw(gg);
            for (Object o : displayRRTemp) o.draw(gg);
        }
    }

    public void script() {
        Button button = buttons.get("MAIN MENU BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("GANTT BUTTON");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("FOLDER BACK");
        Button button2 = buttonsFCFS.get("TEXT BACK");
        button.setHovering(button2.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button2.hovering(cursor.getX(), cursor.getY()));

        button = buttonsFCFS.get("PLAY");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttonsRR.get("PLAY");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("PLUS");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("FIRST");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("PREV");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("NEXT");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        button = buttons.get("LAST");
        button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
        button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));

        for (int i = 0; i < deleteButtonsFCFS.size(); i++) {
            button = deleteButtonsFCFS.get(i);
            button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
            button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));
        }

        for (int i = 0; i < textBoxesArrivalFCFS.size(); i++) {
            button = textBoxesArrivalFCFS.get(i);
            button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
            button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));
        }

        for (int i = 0; i < textBoxesBurstFCFS.size(); i++) {
            button = textBoxesBurstFCFS.get(i);
            button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
            button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));
        }

        for (int i = 0; i < deleteButtonsRR.size(); i++) {
            button = deleteButtonsRR.get(i);
            button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
            button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));
        }

        for (int i = 0; i < textBoxesArrivalRR.size(); i++) {
            button = textBoxesArrivalRR.get(i);
            button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
            button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));
        }

        for (int i = 0; i < textBoxesBurstRR.size(); i++) {
            button = textBoxesBurstRR.get(i);
            button.setHovering(button.hovering(cursor.getX(), cursor.getY()));
            button.setImageOnOff(cursor.press && button.hovering(cursor.getX(), cursor.getY()));
        }

        button = buttonsRR.get("QUANTUM TEXTBOX");
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

            gantt.start();
            gantt.visible = true;
            visible = false;
        }

        button = buttonsFCFS.get("TEXT BACK");
        if (button.hovering(cursor.getX(), cursor.getY())) {
            button.setOnOff();
            System.out.println("CLICK");

            fcfs = !fcfs;

            if (fcfs) updateTableFCFS();
            else updateTableRR();
        }

        if (fcfs) {
            for (int i = 0; i < deleteButtonsFCFS.size(); i++) {
                button = deleteButtonsFCFS.get(i);
                if (button.hovering(cursor.getX(), cursor.getY())) {
                    button.setOnOff();
                    System.out.println("CLICK");

                    cursor.clickCD = 5;
                    cursor.click = false;

                    int choice = JOptionPane.showConfirmDialog(
                            mainMenu.frame,
                            "Delete Process?",
                            "",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        programs.remove(i);

                        updateTableFCFS();

                        deleteCounter++;
                    }

                    break;
                }
            }

            for (int i = 0; i < textBoxesArrivalFCFS.size(); i++) {
                button = textBoxesArrivalFCFS.get(i);

                if (button.hovering(cursor.getX(), cursor.getY())) {
                    cursor.clickCD = 5;
                    cursor.click = false;

                    JTextArea textArea = new JTextArea(1, 30);
                    JOptionPane optionPane = new JOptionPane(textArea, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    JDialog dialog = optionPane.createDialog("Input a positive integer");

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

                    if (optionPane.getValue() != null && (int) optionPane.getValue() == 0 && CheckStringForInt.canConvertToInt(textArea.getText()) && Integer.parseInt(textArea.getText().trim()) >= 0) {
                        programs.get(i).setTimeIn(Integer.parseInt(textArea.getText().trim()));
                        updateTableFCFS();
                    }
                }
            }

            for (int i = 0; i < textBoxesBurstFCFS.size(); i++) {
                button = textBoxesBurstFCFS.get(i);

                if (button.hovering(cursor.getX(), cursor.getY())) {
                    cursor.clickCD = 5;
                    cursor.click = false;

                    JTextArea textArea = new JTextArea(1, 30);
                    JOptionPane optionPane = new JOptionPane(textArea, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    JDialog dialog = optionPane.createDialog("Input a positive integer");

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

                    if (optionPane.getValue() != null && (int) optionPane.getValue() == 0 && CheckStringForInt.canConvertToInt(textArea.getText()) && Integer.parseInt(textArea.getText().trim()) >= 0) {
                        programs.get(i).setDuration(Integer.parseInt(textArea.getText().trim()));
                        updateTableFCFS();
                    }
                }
            }
        }
        else {
            for (int i = 0; i < deleteButtonsRR.size(); i++) {
                button = deleteButtonsRR.get(i);
                if (button.hovering(cursor.getX(), cursor.getY())) {
                    button.setOnOff();
                    System.out.println("CLICK");

                    cursor.clickCD = 5;
                    cursor.click = false;

                    int choice = JOptionPane.showConfirmDialog(
                            mainMenu.frame,
                            "Delete Process?",
                            "",
                            JOptionPane.YES_NO_OPTION
                    );

                    if (choice == JOptionPane.YES_OPTION) {
                        programs.remove(i);

                        updateTableRR();

                        deleteCounter++;
                    }

                    break;
                }
            }

            for (int i = 0; i < textBoxesArrivalRR.size(); i++) {
                button = textBoxesArrivalRR.get(i);

                if (button.hovering(cursor.getX(), cursor.getY())) {
                    cursor.clickCD = 5;
                    cursor.click = false;

                    JTextArea textArea = new JTextArea(1, 30);
                    JOptionPane optionPane = new JOptionPane(textArea, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    JDialog dialog = optionPane.createDialog("Input a positive integer");

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

                    if (optionPane.getValue() != null && (int) optionPane.getValue() == 0 && CheckStringForInt.canConvertToInt(textArea.getText()) && Integer.parseInt(textArea.getText().trim()) >= 0) {
                        programs.get(i).setTimeIn(Integer.parseInt(textArea.getText().trim()));
                        updateTableRR();
                    }
                }
            }

            for (int i = 0; i < textBoxesBurstRR.size(); i++) {
                button = textBoxesBurstRR.get(i);

                if (button.hovering(cursor.getX(), cursor.getY())) {
                    cursor.clickCD = 5;
                    cursor.click = false;

                    JTextArea textArea = new JTextArea(1, 30);
                    JOptionPane optionPane = new JOptionPane(textArea, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                    JDialog dialog = optionPane.createDialog("Input a positive integer");

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

                    if (optionPane.getValue() != null && (int) optionPane.getValue() == 0 && CheckStringForInt.canConvertToInt(textArea.getText()) && Integer.parseInt(textArea.getText().trim()) >= 0) {
                        programs.get(i).setDuration(Integer.parseInt(textArea.getText().trim()));
                        updateTableRR();
                    }
                }
            }

            button = buttonsRR.get("QUANTUM TEXTBOX");
            if (button.hovering(cursor.getX(), cursor.getY())) {
                cursor.clickCD = 5;
                cursor.click = false;

                JTextArea textArea = new JTextArea(1, 30);
                JOptionPane optionPane = new JOptionPane(textArea, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
                JDialog dialog = optionPane.createDialog("Input a positive integer");

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

                if (optionPane.getValue() != null && (int) optionPane.getValue() == 0 && CheckStringForInt.canConvertToInt(textArea.getText()) && Integer.parseInt(textArea.getText().trim()) >= 0) {
                    timeQuantum = Integer.parseInt(textArea.getText().trim());
                    updateTableRR();
                }
            }
        }

        button = buttons.get("PLUS");
        if (button.hovering(cursor.getX(), cursor.getY()) && programs.size() < 5) {
            button.setOnOff();
            System.out.println("CLICK");

            programs.add(new Program("Program " + programs.size() + deleteCounter, 0, 1));

            if (fcfs) updateTableFCFS();
            else updateTableRR();
        }

        if (fcfs) {
            button = buttonsFCFS.get("PLAY");
            if (button.hovering(cursor.getX(), cursor.getY())) {
                button.setOnOff();
                System.out.println("CLICK FCFS");

                gantt.buttons.get("TITLE").setOnOff(false);
                gantt.start();
                gantt.visible = true;
                visible = false;
            }
        }
        else {
            button = buttonsRR.get("PLAY");
            if (button.hovering(cursor.getX(), cursor.getY())) {
                button.setOnOff();
                System.out.println("CLICK RR");

                gantt.buttons.get("TITLE").setOnOff(true);
                gantt.start();
                gantt.visible = true;
                visible = false;
            }
        }

        cursor.clickCD = 5;
        cursor.click = false;
    }

    void updateTableFCFS() {
        LinkedList<Object> objects = new LinkedList<>();
        LinkedList<Text> texts = new LinkedList<>();
        textBoxesArrivalFCFS.clear();
        textArrivalFCFS.clear();
        textBoxesBurstFCFS.clear();
        textBurstFCFS.clear();
        deleteButtonsFCFS.clear();
        for (int i = 0; i < programs.size(); i++) {
            texts.add(new Text(
                    117, 348 + (57 * i), 2,
                    (i + 1) + "", 24, Color.WHITE
            ));
            textBoxesArrivalFCFS.add(new Button(
                    new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                    new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                    263, 319 + 57 * i, 203, 37, 1)
            );
            textBoxesArrivalFCFS.getLast().setHoverImage(new ImageIcon("src/gui/assets/highdark.png").getImage(), true);
            textArrivalFCFS.add(new Text(
                    280, 319 + 27 + (57 * i), 2,
                    programs.get(i).getTimeIn() + "", 24, Color.BLACK
            ));
            textBoxesBurstFCFS.add(new Button(
                    new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                    new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                    503, 319 + 57 * i, 203, 37, 1)
            );
            textBoxesBurstFCFS.getLast().setHoverImage(new ImageIcon("src/gui/assets/highdark.png").getImage(), true);
            textBurstFCFS.add(new Text(
                    520, 319 + 27 + (57 * i), 2,
                    programs.get(i).getDuration() + "", 24, Color.BLACK
            ));
            if (programs.size() > 1) {
                deleteButtonsFCFS.add(new Button(
                        new ImageIcon("src/gui/assets/trasgh.png").getImage(),
                        new ImageIcon("src/gui/assets/trasgh.png").getImage(),
                        749, 325 + 57 * i, 25, 25, 1)
                );
                deleteButtonsFCFS.getLast().setHoverImage(new ImageIcon("src/gui/assets/trashWW.png").getImage(), true);
            }
        }
        this.buttons.get("PLUS").gotoXY(113, 330 + (57 * programs.size()));
        displayFCFSTemp.clear();
        displayFCFSTemp.addAll(texts);

        buttons.get("PLUS").visible = programs.size() < 5;
    }

    void updateTableRR() {
        LinkedList<Object> objects = new LinkedList<>();
        LinkedList<Text> texts = new LinkedList<>();
        textBoxesArrivalRR.clear();
        textArrivalRR.clear();
        textBoxesBurstRR.clear();
        textBurstRR.clear();
        deleteButtonsRR.clear();
        for (int i = 0; i < programs.size(); i++) {
            texts.add(new Text(
                    117, 348 + 27 + (57 * i), 2,
                    (i + 1) + "", 24, Color.WHITE
            ));
            textBoxesArrivalRR.add(new Button(
                    new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                    new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                    263, 319 + 27 + 57 * i, 203, 37, 1)
            );
            textBoxesArrivalRR.getLast().setHoverImage(new ImageIcon("src/gui/assets/highdark.png").getImage(), true);
            textArrivalRR.add(new Text(
                    280, 319 + 27 + 27 + (57 * i), 2,
                    programs.get(i).getTimeIn() + "", 24, Color.BLACK
            ));
            textBoxesBurstRR.add(new Button(
                    new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                    new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                    503, 319 + 27 + 57 * i, 203, 37, 1)
            );
            textBoxesBurstRR.getLast().setHoverImage(new ImageIcon("src/gui/assets/highdark.png").getImage(), true);
            textBurstRR.add(new Text(
                    520, 319 + 27 + 27 + (57 * i), 2,
                    programs.get(i).getDuration() + "", 24, Color.BLACK
            ));
            if (programs.size() > 1) {
                deleteButtonsRR.add(new Button(
                        new ImageIcon("src/gui/assets/trasgh.png").getImage(),
                        new ImageIcon("src/gui/assets/trasgh.png").getImage(),
                        749, 325 + 27 + 57 * i, 25, 25, 1)
                );
                deleteButtonsRR.getLast().setHoverImage(new ImageIcon("src/gui/assets/trashWW.png").getImage(), true);
            }
        }
        this.buttons.get("PLUS").gotoXY(113, 330 + 27 + (57 * programs.size()));
        displayRRTemp.clear();
        displayRRTemp.add(new Text(
                753, 255, 3,
                timeQuantum + "", 24, Color.BLACK
        ));
        displayRRTemp.addAll(texts);

        buttons.get("PLUS").visible = programs.size() < 5;
    }
}
