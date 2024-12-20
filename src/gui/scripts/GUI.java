package gui.scripts;

import javax.swing.*;

public class GUI extends JFrame {
    Panel panel;

    GUI(Assets assets) {
        setLayout(null);
        setResizable(false);
        setTitle("FCFS and RR Process Scheduling System");
        setSize(1000 + 16, 700 + 39);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new Panel(getWidth(), getHeight(), assets, this);
        panel.setBounds(0, 0, getWidth(), getHeight());
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Wello Horld!\n\n");

        Assets assets = new Assets();
        new GUI(assets);
    }
}
