package CharacterSelectScene;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ky.Asset;
import ky.Entity;
import ky.Vector2D;

public class SelectionCursor extends Entity {

    private int player = 0;
    private Asset cursorAsset;

    public SelectionCursor(Vector2D position, int layer, int player) {
        super(position, layer, "cursor");
        this.player = player;

        cursorAsset = new Asset("assets/test.png", new Vector2D(0, 0), 4);
        // p1Cursor = new Asset("assets/misc/p1Cursor.png", new Vector2D(2, 2), 4);
        // p2Cursor = new Asset("assets/misc/p2Cursor.png", new Vector2D(2, 2), 4);
        cursorAsset.setVisible(true);
        add(cursorAsset);
        setVisible(true);

    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (player == 1) {
            if (keyCodes.contains(KeyEvent.VK_D)) {
                this.addPos(100,0);
            }
            if (keyCodes.contains(KeyEvent.VK_A)) {
                this.addPos(-100,0);
            }
        } else if (player == 2) {
            if (keyCodes.contains(KeyEvent.VK_RIGHT)) {
                this.addPos(100,0);
            }
            if (keyCodes.contains(KeyEvent.VK_LEFT)) {
                this.addPos(-100,0);
            }
        } else {

        }
    }
}
