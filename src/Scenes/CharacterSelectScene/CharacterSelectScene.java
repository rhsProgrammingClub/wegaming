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
    Asset spaceship;
    Asset stabby;
    Asset boxrobot;
    Asset assassin;

    public CharacterSelectScene(Main main) {
        super(main);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (p1Cursor.selected && p2Cursor.selected) {
            main.setScene(3);
        }

        if(keyCodes.contains(KeyEvent.VK_ESCAPE)){
            main.setScene(1);
        }
    }

    @Override
    public void initialize() {
        p1Cursor = new SelectionCursor("assets/misc/p1cursor.png", new Vector2D(width * 0.5 - 110, height * 0.75), 4, 1, main);
        p2Cursor = new SelectionCursor("assets/misc/p2cursor.png", new Vector2D(width * 0.5 + 110, height * 0.75), 4, 2, main);
        // p1CharacterArt = new Asset(image, position, 1);
        // p2CharacterArt = new Asset(image, position, 1);
        spaceship = new Asset("assets/characters/spaceship/spaceship_normal.png",
                new Vector2D(width * 0.5 - 330, height * 0.75), 0);
        stabby = new Asset("assets/characters/stabbyrobot/sword.png", new Vector2D(width * 0.5 - 110, height * 0.75),
                110, 30, 0);
        p1Cursor.setVisible(true);
        boxrobot = new Asset("assets/characters/boxrobot/box_robot.png", new Vector2D(width * 0.5 + 110, height * 0.75),
                110, 110, 0);
        assassin = new Asset("assets/characters/assassin/assassin.png", new Vector2D(width * 0.5 + 330, height * 0.75), 
                110, 110, 0);
        p2Cursor.setVisible(true);
        spaceship.setVisible(true);
        stabby.setVisible(true);
        boxrobot.setVisible(true);
        assassin.setVisible(true);
        add(p1Cursor);
        add(p2Cursor);
        add(spaceship);
        add(stabby);
        add(boxrobot);
        add(assassin);

    }

}