import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class CharacterSelectScene extends Scene {

    SelectionCursor p1Cursor;
    SelectionCursor p2Cursor;
    Asset test;
    Asset p1CharacterArt;
    Asset p2CharacterArt;
    int sceneIndex = 1;

    public CharacterSelectScene () {
        p1Cursor = new SelectionCursor(new Vector2D(width*0.5 - 110, height*0.75), 4, 1);
        p2Cursor = new SelectionCursor(new Vector2D(width*0.5 + 110, height*0.75), 4, 2);
        // p1CharacterArt = new Asset(image, position, 1);
        // p2CharacterArt = new Asset(image, position, 1);
        p1Cursor.setVisible(true);
        p2Cursor.setVisible(true);
        add(p1Cursor);
        add(p2Cursor);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (p1Cursor.selected && p2Cursor.selected) {
            sceneIndex = 2;
        }
    }

    @Override
    public int changeScene() {
        return sceneIndex;
    }

}