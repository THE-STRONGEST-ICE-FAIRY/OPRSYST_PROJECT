package gui.scripts;

import gui.utilities.Button;
import gui.utilities.Object;
import gui.utilities.Text;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;

public class Assets {
    public HashMap<String, HashMap<String, Object>> objects;
    public HashMap<String, HashMap<String, Button>> buttons;
    public HashMap<String, HashMap<String, LinkedList<Object>>> objectGroups;

    Assets() {
        objects = new HashMap<>();
        buttons = new HashMap<>();
        objectGroups = new HashMap<>();
    }

    public void mainMenu(int width, int height) {
        String x = "MAIN MENU";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());

        objects.get(x).put("BACKGROUND", new Object(
                new ImageIcon("src/gui/assets/background.png").getImage(),
                0, 0, 1052, height, 0
        ));

        objects.get(x).put("TEXT", new Text(
                0, -9999, 1,
                "MAIN MENU", 50, Color.BLACK
        ));

        objects.get(x).put("TITLE", new Object(
                new ImageIcon("src/gui/assets/PROCESS SCHEDULING SYSTEM.png.png").getImage(),
                522, 32, 433, 276, 1
        ));

        buttons.get(x).put("ABOUT BUTTON", new Button(
                new ImageIcon("src/gui/assets/A GROUP PROJECT IN OPRSYST MADE BY AQUINO, LERIA, ORNEDO, AND TRILLANES OF BSCPE 231.png.png").getImage(),
                new ImageIcon("src/gui/assets/A GROUP PROJECT IN OPRSYST MADE BY AQUINO, LERIA, ORNEDO, AND TRILLANES OF BSCPE 231.png.png").getImage(),
                21, 19, 379, 32, 1
        ));
        buttons.get(x).get("ABOUT BUTTON").setHoverImage(new ImageIcon("src/gui/assets/A GROUP PROJECT IN OPRSYST MADE BY AQUINO, LERIA, ORNEDO, AND TRILLANES OF BSCPE 231 hover.png.png").getImage(), true);

        buttons.get(x).put("TEXT BUTTON", new Button(
                new ImageIcon("src/gui/assets/fumo2.jpeg").getImage(),
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                300, -9999, 100, 100, 1
        ));
        buttons.get(x).get("TEXT BUTTON").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage(), false);

        buttons.get(x).put("EXIT BUTTON", new Button(
                new ImageIcon("src/gui/assets/quit-icon 1.png (1).png").getImage(),
                new ImageIcon("src/gui/assets/quit-icon 1.png (2).png").getImage(),
                908, 608, 67, 67, 1
        ));
        buttons.get(x).get("EXIT BUTTON").setHoverImage(new ImageIcon("src/gui/assets/quit-icon 1.png (2).png").getImage(), true);

        buttons.get(x).put("TABLE BUTTON", new Button(
                new ImageIcon("src/gui/assets/fumo2.jpeg").getImage(),
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                600, -9100, 100, 100, 1
        ));
        buttons.get(x).get("TABLE BUTTON").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage(), false);

        objects.get(x).put("APC LOGO", new Object(
                new ImageIcon("src/gui/assets/Frame 1.png").getImage(),
                14, 623, 162, 66, 3
        ));

        buttons.get(x).put("FOLDER FCFS", new Button(
                new ImageIcon("src/gui/assets/folder default.png").getImage(),
                new ImageIcon("src/gui/assets/folder default.png").getImage(),
                -124, 214, 819, 676, 1
        ));
        buttons.get(x).get("FOLDER FCFS").setHoverImage(new ImageIcon("src/gui/assets/folder select.png").getImage(), true);

        buttons.get(x).put("FOLDER RR", new Button(
                new ImageIcon("src/gui/assets/folder default.png").getImage(),
                new ImageIcon("src/gui/assets/folder default.png").getImage(),
                -124, 350, 819, 676, 2
        ));
        buttons.get(x).get("FOLDER RR").setHoverImage(new ImageIcon("src/gui/assets/folder select.png").getImage(), true);

        buttons.get(x).put("TEXT RR", new Button(
                new ImageIcon("src/gui/assets/ROUND ROBIN.png").getImage(),
                new ImageIcon("src/gui/assets/ROUND ROBIN.png").getImage(),
                422, 398, 198, 74, 2
        ));

        buttons.get(x).put("TEXT FCFS", new Button(
                new ImageIcon("src/gui/assets/FIRST COME FIRST SERVE.png").getImage(),
                new ImageIcon("src/gui/assets/FIRST COME FIRST SERVE.png").getImage(),
                412, 261, 198, 74, 2
        ));
    }

    public void about(int width, int height) {
        String x = "ABOUT";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());

        objects.get(x).put("BACKGROUND", new Object(
                new ImageIcon("src/gui/assets/woodenTable.jpg").getImage(),
                0, -9990, 1052, height, 0
        ));

        objects.get(x).put("TEXT", new Text(
                0, -9950, 1,
                "ABOUT", 50, Color.BLACK
        ));

        buttons.get(x).put("MAIN MENU BUTTON", new Button(
                new ImageIcon("src/gui/assets/Desktop - 5.png").getImage(),
                new ImageIcon("src/gui/assets/Desktop - 5.png").getImage(),
                0, 0, 1000, 700, 1
        ));
    }

    public void table(int width, int height) {
        String x = "TABLE";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());

        objects.get(x).put("BACKGROUND", new Object(
                new ImageIcon("src/gui/assets/background.png").getImage(),
                0, 0, 1052, height, 0
        ));

        objects.get(x).put("TEXT", new Text(
                0, -9950, 1,
                "TABLE", 50, Color.BLACK
        ));

        buttons.get(x).put("GANTT BUTTON", new Button(
                new ImageIcon("src/gui/assets/fumo2.jpeg").getImage(),
                new ImageIcon("src/gui/assets/fumo.jpeg").getImage(),
                600, -9100, 200, 100, 1
        ));
        buttons.get(x).get("GANTT BUTTON").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage(), false);

        buttons.get(x).put("MAIN MENU BUTTON", new Button(
                new ImageIcon("src/gui/assets/return-104 1 (3).png").getImage(),
                new ImageIcon("src/gui/assets/return-104 1 (3).png").getImage(),
                12, 12, 52, 52, 1
        ));
        buttons.get(x).get("MAIN MENU BUTTON").setHoverImage(new ImageIcon("src/gui/assets/return-104 1 (2).png").getImage(), true);

        objects.get(x).put("FOLDER FRONT", new Object(
                new ImageIcon("src/gui/assets/folder select.png").getImage(),
                -30, 6, 1068, 876, 2
        ));

        buttons.get(x).put("FOLDER BACK", new Button(
                new ImageIcon("src/gui/assets/folder default.png").getImage(),
                new ImageIcon("src/gui/assets/folder default.png").getImage(),
                -340    , -36, 1068, 876, 1
        ));
        buttons.get(x).get("FOLDER BACK").setHoverImage(new ImageIcon("src/gui/assets/folder select.png").getImage(), true);

        buttons.get(x).put("PLUS", new Button(
                new ImageIcon("src/gui/assets/+.png").getImage(),
                new ImageIcon("src/gui/assets/+.png").getImage(),
                117, -9999, 192, 29, 2
        ));
        buttons.get(x).get("PLUS").setHoverImage(new ImageIcon("src/gui/assets/+ ADD PROCESS.png").getImage(), true);

        buttons.get(x).put("FIRST", new Button(
                new ImageIcon("src/gui/assets/FIRST.png").getImage(),
                new ImageIcon("src/gui/assets/FIRST.png").getImage(),
                355, 622, 70, 18, 2
        ));
        buttons.get(x).get("FIRST").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage(), false);
        buttons.get(x).get("FIRST").visible = false;

        buttons.get(x).put("PREV", new Button(
                new ImageIcon("src/gui/assets/_.png").getImage(),
                new ImageIcon("src/gui/assets/_.png").getImage(),
                469, 622, 18, 18, 2
        ));
        buttons.get(x).get("PREV").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage(), false);
        buttons.get(x).get("PREV").visible = false;

        buttons.get(x).put("NEXT", new Button(
                new ImageIcon("src/gui/assets/_1.png").getImage(),
                new ImageIcon("src/gui/assets/_1.png").getImage(),
                589, 622, 18, 18, 2
        ));
        buttons.get(x).get("NEXT").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage(), false);
        buttons.get(x).get("NEXT").visible = false;

        buttons.get(x).put("LAST", new Button(
                new ImageIcon("src/gui/assets/LAST.png").getImage(),
                new ImageIcon("src/gui/assets/LAST.png").getImage(),
                649, 622, 70, 18, 2
        ));
        buttons.get(x).get("LAST").setHoverImage(new ImageIcon("src/gui/assets/white55.png").getImage(), false);
        buttons.get(x).get("LAST").visible = false;

        objects.get(x).put("PAGE", new Text(
                519, 637, 2,
                "1", 24, Color.WHITE
        ));
        objects.get(x).get("PAGE").visible = false;

        x = "TABLE FCFS";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());

        objects.get(x).put("TEXT FRONT", new Object(
                new ImageIcon("src/gui/assets/FIRST COME FIRST SERVE 2.png").getImage(),
                687, 71, 265, 100, 2
        ));

        buttons.get(x).put("TEXT BACK", new Button(
                new ImageIcon("src/gui/assets/ROUND ROBIN 2.png").getImage(),
                new ImageIcon("src/gui/assets/ROUND ROBIN 2.png").getImage(),
                369, 27, 265, 100, 2
        ));

        objects.get(x).put("HEADER", new Object(
                new ImageIcon("src/gui/assets/header.png").getImage(),
                71, 267, 588, 29, 2
        ));

        buttons.get(x).put("PLAY", new Button(
                new ImageIcon("src/gui/assets/run blur.png").getImage(),
                new ImageIcon("src/gui/assets/run blur.png").getImage(),
                832    , 526, 104, 104, 1
        ));
        buttons.get(x).get("PLAY").setHoverImage(new ImageIcon("src/gui/assets/run.png").getImage(), true);

        x = "TABLE RR";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());

        objects.get(x).put("TEXT FRONT", new Object(
                new ImageIcon("src/gui/assets/ROUND ROBIN 2.png").getImage(),
                687, 71, 265, 100, 2
        ));

        buttons.get(x).put("TEXT BACK", new Button(
                new ImageIcon("src/gui/assets/FIRST COME FIRST SERVE 2.png").getImage(),
                new ImageIcon("src/gui/assets/FIRST COME FIRST SERVE 2.png").getImage(),
                369, 27, 265, 100, 2
        ));

        objects.get(x).put("HEADER", new Object(
                new ImageIcon("src/gui/assets/header.png").getImage(),
                71, 294, 588, 29, 2
        ));

        buttons.get(x).put("PLAY", new Button(
                new ImageIcon("src/gui/assets/run blur.png").getImage(),
                new ImageIcon("src/gui/assets/run blur.png").getImage(),
                832, 551, 104, 104, 1
        ));
        buttons.get(x).get("PLAY").setHoverImage(new ImageIcon("src/gui/assets/run.png").getImage(), true);

        objects.get(x).put("QUANTUM", new Object(
                new ImageIcon("src/gui/assets/TIME QUANTUM.png").getImage(),
                522, 238, 191, 22, 2
        ));

        buttons.get(x).put("QUANTUM TEXTBOX", new Button(
                new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                new ImageIcon("src/gui/assets/Rectangle 54.png").getImage(),
                740, 228, 203, 37, 2
        ));
        buttons.get(x).get("QUANTUM TEXTBOX").setHoverImage(new ImageIcon("src/gui/assets/highdark.png").getImage(), true);
    }

    public void gantt(int width, int height) {
        String x = "GANTT";

        objects.put(x, new HashMap<>());
        buttons.put(x, new HashMap<>());
        objectGroups.put(x, new HashMap<>());

        objects.get(x).put("BACKGROUND", new Object(
                new ImageIcon("src/gui/assets/folder select.png").getImage(),
                -171, -200, 1220, 1001, 0
        ));

        objects.get(x).put("TEXT", new Text(
                0, -9950, 1,
                "GANTT", 50, Color.BLACK
        ));

        buttons.get(x).put("TABLE BUTTON", new Button(
                new ImageIcon("src/gui/assets/return-104 1 (3).png").getImage(),
                new ImageIcon("src/gui/assets/return-104 1 (3).png").getImage(),
                27, 39, 52, 52, 1
        ));
        buttons.get(x).get("TABLE BUTTON").setHoverImage(new ImageIcon("src/gui/assets/return-104 1 (2).png").getImage(), false);

        objectGroups.get(x).put("RECTANGLES", new LinkedList<>());
        for (int i = 1; i <= 10; i++) {
            objectGroups.get(x).get("RECTANGLES").add(
                    new Object(
                            new ImageIcon("src/gui/assets/rectangles/rect (" + i + ").png").getImage(),
                            (i - 1) * 50 + 10 * (i - 1), 0, 50, 100, 0)
            );
        }

        objects.get(x).put("HOLDER", new Object(
                new ImageIcon("src/gui/assets/holder gantt.png").getImage(),
                53, 118, 867, 542, 1
        ));

        buttons.get(x).put("TITLE", new Button(
                new ImageIcon("src/gui/assets/FIRST COME FIRST SERVE 3.png").getImage(),
                new ImageIcon("src/gui/assets/ROUND ROBIN 3.png").getImage(),
                494, 39, 454, 50, 1
        ));
        buttons.get(x).get("TITLE").setLever(true);

        objects.get(x).put("HOLDER", new Object(
                new ImageIcon("src/gui/assets/holder gantt.png").getImage(),
                53, 118, 867, 542, 1
        ));

        objects.get(x).put("HEADER", new Object(
                new ImageIcon("src/gui/assets/Frame 2.png").getImage(),
                72, 374, 828, 29, 2
        ));
    }
}
