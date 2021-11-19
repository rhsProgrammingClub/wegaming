package CharacterSelectScene;

import ky.Asset;
import ky.Scene;
import ky.Vector2D;

public class CharacterSelectScene extends Scene {

    SelectionCursor p1Cursor;
    SelectionCursor p2Cursor;
    Asset test;

    public CharacterSelectScene () {
        p1Cursor = new SelectionCursor(new Vector2D(30, 30), 4, 1);
        p2Cursor = new SelectionCursor(new Vector2D(1000, 500), 4, 2);
        test = new Asset("assets/test.png", new Vector2D(300,312), 3);
        p1Cursor.setVisible(true);
        p2Cursor.setVisible(true);
        add(p1Cursor);
        add(p2Cursor);
        test.setVisible(true);
        add(test);
    }

}
