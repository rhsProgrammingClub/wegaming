import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class CharacterSelectScene extends Scene {

    SelectionCursor p1Cursor;
    SelectionCursor p2Cursor;
    Asset test;

    public CharacterSelectScene () {
        p1Cursor = new SelectionCursor(new Vector2D(width*0.2, height*0.75), 4, 1);
        p2Cursor = new SelectionCursor(new Vector2D(width*0.8, height*0.75), 4, 2);
        test = new Asset("assets/test.png", new Vector2D(300,312), 3);
        p1Cursor.setVisible(true);
        p2Cursor.setVisible(true);
        add(p1Cursor);
        add(p2Cursor);
        test.setVisible(true);
        add(test);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.contains(KeyEvent.VK_C)) {
            p1Cursor.selected=true;
        }
        // if (keyCodes.contains(KeyEvent.VK_A)) {
        // }
        // if (keyCodes.contains(KeyEvent.VK_LEFT)) {

        // }

        // if (keyCodes.contains(KeyEvent.VK_D)) {

        // }
        // if (keyCodes.contains(KeyEvent.VK_RIGHT)) {

        // }
    }

}