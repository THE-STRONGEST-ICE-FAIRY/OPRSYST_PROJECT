package gui.scripts;

import gui.utilities.Button;
import gui.utilities.Object;
import gui.utilities.Text;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Assets {
    public HashMap<String, HashMap<String, Object>> objects;
    public HashMap<String, HashMap<String, Button>> buttons;

    Assets() {
        objects = new HashMap<>();
        buttons = new HashMap<>();
    }

    public void mainMenu(int width, int height) {
        String x = "MAIN MENU";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());

        objects.get(x).put("BACKGROUND", new Object(
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                0, 0, width, height, 0
        ));

        buttons.get(x).put("BUTTON", new Button(
                new ImageIcon("src/gui/assets/fumo2.jpeg").getImage(),
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                100, 300, 100, 100, 1
        ));
        buttons.get(x).get("BUTTON").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage());
        buttons.get(x).get("BUTTON").setLever(true);

        objects.get(x).put("TEXT", new Text(
                0, 50, 1,
                "MAIN MENU", 50, Color.BLACK
        ));

        buttons.get(x).put("ABOUT BUTTON", new Button(
                new ImageIcon("src/gui/assets/fumo2.jpeg").getImage(),
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                600, 300, 100, 100, 1
        ));
        buttons.get(x).get("ABOUT BUTTON").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage());

        buttons.get(x).put("TEXT BUTTON", new Button(
                new ImageIcon("src/gui/assets/fumo2.jpeg").getImage(),
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                300, 300, 100, 100, 1
        ));
        buttons.get(x).get("TEXT BUTTON").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage());
    }

    public void about(int width, int height) {
        String x = "ABOUT";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());

        objects.get(x).put("BACKGROUND", new Object(
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                0, 0, width, height, 0
        ));

        objects.get(x).put("TEXT", new Text(
                0, 50, 1,
                "ABOUT", 50, Color.BLACK
        ));

        buttons.get(x).put("MAIN MENU BUTTON", new Button(
                new ImageIcon("src/gui/assets/fumo2.jpeg").getImage(),
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                600, 300, 100, 100, 1
        ));
        buttons.get(x).get("MAIN MENU BUTTON").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage());
    }

    public void roundRobin(int width, int height) {
        String x = "ROUND ROBIN";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());

        objects.get(x).put("BACKGROUND", new Object(
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                0, 0, width, height, 0
        ));
    }

    public void firstComeFirstServe(int width, int height) {
        String x = "FIRST COME FIRST SERVE";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());

        objects.get(x).put("BACKGROUND", new Object(
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                0, 0, width, height, 0
        ));
    }
}
