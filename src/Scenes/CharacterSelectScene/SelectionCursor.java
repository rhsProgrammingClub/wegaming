import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.plaf.basic.BasicSplitPaneUI.KeyboardUpLeftHandler;

import ky.Asset;
import ky.Entity;
import ky.Vector2D;

public class SelectionCursor extends Entity {

    public boolean selected = false;
    private int player = 0;
    private Asset cursorAsset;
    private boolean liftedLeft = true;
    private boolean liftedRight = true;
    private int leftKey;
    private int rightKey;

    public SelectionCursor(Vector2D position, int layer, int player) {
        super(position, layer, "cursor");
        this.player = player;

        cursorAsset = new Asset("assets/test.png", new Vector2D(0, 0), 4);
        // p1Cursor = new Asset("assets/misc/p1Cursor.png", new Vector2D(2, 2), 4);
        // p2Cursor = new Asset("assets/misc/p2Cursor.png", new Vector2D(2, 2), 4);
        cursorAsset.setVisible(true);
        add(cursorAsset);
        setVisible(true);

        if (player == 1) {
            leftKey = KeyEvent.VK_A;
            rightKey = KeyEvent.VK_D;
        } else if (player == 2) {
            leftKey = KeyEvent.VK_LEFT;
            rightKey = KeyEvent.VK_RIGHT;
        }

    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (!keyCodes.contains(leftKey)) {
            liftedLeft = true;
        }
        if (!keyCodes.contains(rightKey)) {
            liftedRight = true;
        }
        if (!selected) {
            if (keyCodes.contains(rightKey) && liftedRight) {
                this.addPos(100,0);
                liftedRight = false;
            }
            if (keyCodes.contains(leftKey) && liftedLeft) {
                this.addPos(-100,0);
                liftedLeft = false;
            }
        }
    }
}
