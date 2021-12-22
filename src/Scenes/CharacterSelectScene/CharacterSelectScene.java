import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class CharacterSelectScene extends Scene {

    SelectionCursor p1Cursor;
    SelectionCursor p2Cursor;

    Asset test;
    Asset p1CharacterArt;
    Asset p2CharacterArt;
    Asset spaceship = new Asset ("assets/Characters/spaceship/spaceship_normal.png", new Vector2D(width * 0.5 - 330, height * 0.75), 0);
    Asset stabby = new Asset ("assets/Characters/stabbyrobot/sword.png", new Vector2D(width * 0.5 - 110, height * 0.75), 110, 30, 0);
    Asset boxrobot = new Asset("assets/Characters/boxrobot/box_robot.png", new Vector2D(width * 0.5 + 110, height * 0.75), 110, 110, 0);

    public CharacterSelectScene(Main main) {
        super(main);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (p1Cursor.selected && p2Cursor.selected) {
            main.setScene(3);
        }
    }

    @Override
    public void initialize() {
        p1Cursor = new SelectionCursor(new Vector2D(width * 0.5 - 110, height * 0.75), 4, 1, main);
        p2Cursor = new SelectionCursor(new Vector2D(width * 0.5 + 110, height * 0.75), 4, 2, main);
        // p1CharacterArt = new Asset(image, position, 1);
        // p2CharacterArt = new Asset(image, position, 1);
        p1Cursor.setVisible(true);
        p2Cursor.setVisible(true);
        spaceship.setVisible(true);
        stabby.setVisible(true);
        boxrobot.setVisible(true);
        add(p1Cursor);
        add(p2Cursor);
        add(spaceship);
        add(stabby);
        add(boxrobot);

    }

}