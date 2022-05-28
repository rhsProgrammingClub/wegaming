import ky.Vector2D;
import ky.Asset;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import ky.Text;
import java.awt.Color;
import java.awt.Font;

public class MapSelectScene extends Scene {

    int curselect = 0;
    static int finalselect = 0;
    double finalsizes[] = { 0.2, 0.4, 0.6, 0.8 };
    Text[] displayname = new Text[4];
    Asset[] mapassets = new Asset[4];
    Asset selectionCursor;
    Asset selectionConfirm;
    Asset mapPreview;
    final int width = 4;

    int player1Left;
    int player1Right;
    int player2Left;
    int player2Right;
    boolean left1 = true;
    boolean right1 = true;
    boolean left2 = true;
    boolean right2 = true;

    public MapSelectScene(Main main) {
        super(main);
    }

    @Override
    public void initialize() {

        // values will increase as more maps are made
        for (int i = 0; i < width; i++) {
            mapassets[i] = new Asset("assets/test.png",
                    new Vector2D(Main.width * finalsizes[i], Main.height * 0.2),
                    200, 100,
                    0);
            mapassets[i].setVisible(true);
            add(mapassets[i]);

            displayname[i] = new Text("N/A",
                    new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
                    Color.black,
                    new Vector2D(Main.width * 0.85, Main.height * 0.85),
                    300,
                    30,
                    5);
            displayname[i].setVisible(false);
            add(displayname[i]);

        }

        mapassets[0].setImage("assets/maps/default.png");
        mapassets[1].setImage("assets/maps/platformer.png");
        mapassets[2].setImage("assets/maps/staircase.png");
        mapassets[3].setImage("assets/maps/reversestaircase.png");

        displayname[0].setText("Default");
        displayname[1].setText("Platformer");
        displayname[2].setText("Staircase");
        displayname[3].setText("Reverse Staircase");

        selectionCursor = new Asset("assets/misc/map_select_cursor.png", new Vector2D(0, 0), 1);
        selectionCursor.setVisible(true);
        selectionCursor.setPos(mapassets[curselect].getPos());
        add(selectionCursor);

        selectionConfirm = new Asset("assets/misc/map_select_confirm.png", new Vector2D(0, 0), 1);
        selectionConfirm.setVisible(true);
        selectionConfirm.setPos(mapassets[curselect].getX(), mapassets[curselect].getY() + 200);
        add(selectionConfirm);

        displayname[curselect].setVisible(true);

        player1Left = PlayerInput.PLAYER_ONE_INPUT.leftKey.get();
        player1Right = PlayerInput.PLAYER_ONE_INPUT.rightKey.get();
        player2Left = PlayerInput.PLAYER_TWO_INPUT.leftKey.get();
        player2Right = PlayerInput.PLAYER_TWO_INPUT.rightKey.get();
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.contains(KeyEvent.VK_ESCAPE)) {
            main.setScene(1);
        }

        if (!keyCodes.contains(player1Left)) {
            left1 = true;
        }
        if (!keyCodes.contains(player1Right)) {
            right1 = true;
        }
        if (!keyCodes.contains(player2Left)) {
            left2 = true;
        }
        if (!keyCodes.contains(player2Right)) {
            right2 = true;
        }

        if (keyCodes.contains(player1Left) && left1) {
            left1 = false;
            moveMapSelection(-1);
        }
        if (keyCodes.contains(player1Right) && right1) {
            right1 = false;
            moveMapSelection(1);
        }
        if (keyCodes.contains(player2Left) && left2) {
            left2 = false;
            moveMapSelection(-1);
        }
        if (keyCodes.contains(player2Right) && right2) {
            right2 = false;
            moveMapSelection(1);
        }

        if (keyCodes.contains(PlayerInput.PLAYER_ONE_INPUT.attackKey.get()) ||
                keyCodes.contains(PlayerInput.PLAYER_TWO_INPUT.attackKey.get())) {

            try {
                // just so that doesn't immediately lock in character after
                // give player time to stop holding lock in button
                Thread.sleep(300);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
            main.setScene(2);
        }

    }

    void moveMapSelection(int diff) {
        displayname[curselect].setVisible(false);
        if (curselect + diff < 0) {
            curselect = width + (curselect + diff);
        } else {
            curselect += diff;
            curselect %= width;
        }
        displayname[curselect].setVisible(true);
        selectionCursor.setPos(mapassets[curselect].getPos());
        selectionConfirm.setPos(mapassets[curselect].getX(), mapassets[curselect].getY() + 200);
        finalselect = curselect;
    }

    public static int getmap() {
        return finalselect;
    }

}
