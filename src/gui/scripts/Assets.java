package gui.scripts;

import gui.utilities.Button;
import gui.utilities.Object;

import javax.swing.*;
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
        {
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
        }
    }
}
