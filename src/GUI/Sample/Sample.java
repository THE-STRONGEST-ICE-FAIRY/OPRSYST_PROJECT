package GUI.Sample;

import javax.swing.*;

public class Sample extends JFrame {
    Panel panel;

    Sample() {
        setLayout(null);
        setResizable(false);
        setTitle("GUI Sample");
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        panel = new Panel(getWidth(), getHeight());
        panel.setBounds(0, 0, getWidth(), getHeight());
        add(panel);

        setVisible(true);
    }

    public static void main(String[] args) {
        System.out.println("Wello Horld!\n\n");

        new Sample();
    }
}
