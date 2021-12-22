import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class Sword extends DamageEntity {

    public Asset swordAsset;

    public Sword(Vector2D position, int layer, int player, double damage) {
        super(position, 120, 40, layer, player, damage);
        swordAsset = new Asset(
                new String[] { "assets/characters/testcharacter/sword.png",
                        "assets/characters/testcharacter/sword_boosted.png",
                        "assets/characters/testcharacter/sword_ult.png" },
                new Vector2D(0, 0), 120, 40, 5);

        swordAsset.setVisible(true);
        add(swordAsset);
        setBreaks(false);
        setVisible(true);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
    }

}