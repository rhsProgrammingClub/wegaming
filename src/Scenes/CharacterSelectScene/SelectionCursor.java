import java.awt.event.KeyEvent;
import java.util.ArrayList;

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
    private int selectKey;
    public int characterIndex;

    public SelectionCursor(Vector2D position, int layer, int player) {
        super(position, layer, "cursor");
        this.player = player;

        cursorAsset = new Asset("assets/test.png", new Vector2D(0, 0), 4);
        // p1Cursor = new Asset("assets/misc/p1Cursor.png", new Vector2D(2, 2), 4);
        // p2Cursor = new Asset("assets/misc/p2Cursor.png", new Vector2D(2, 2), 4);
        cursorAsset.rescale(2);
        cursorAsset.setVisible(true);
        add(cursorAsset);
        setVisible(true);

        if (player == 1) {
            leftKey = KeyEvent.VK_A;
            rightKey = KeyEvent.VK_D;
            selectKey = KeyEvent.VK_E;
            characterIndex = 1;
        } else if (player == 2) {
            leftKey = KeyEvent.VK_J;
            rightKey = KeyEvent.VK_L;
            selectKey = KeyEvent.VK_O;
            characterIndex = 2;
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
        if (keyCodes.contains(selectKey)) {
            selected = true;
            if (player == 1) {
                Main.player1 = Main.characters[characterIndex];
            } else if (player == 2) {
                Main.player2 = Main.characters[characterIndex];
            }
        }
        if (!selected) {
            if (keyCodes.contains(rightKey) && liftedRight) {
                if (characterIndex < 3) {
                    this.addPos(cursorAsset.getWidth()+20,0);
                    liftedRight = false;
                    characterIndex++;
                }
            }
            if (keyCodes.contains(leftKey) && liftedLeft) {
                if (characterIndex > 0) {
                    this.addPos(-(cursorAsset.getWidth()+20),0);
                    liftedLeft = false;
                    characterIndex--;
                }
            }
        }
    }
}
