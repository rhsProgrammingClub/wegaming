import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class Sword extends DamageEntity {

    public Asset swordAsset;

    public Sword(Vector2D position, int layer, int player, double damage) {
        super(position, 60, 20, layer, player, damage, 400);
        swordAsset = new Asset(
                new String[] { "assets/characters/stabbyrobot/sword.png",
                        "assets/characters/stabbyrobot/sword_boosted.png",
                        "assets/characters/stabbyrobot/sword_ult.png" },
                new Vector2D(0, 0), 60, 20, 5);

        swordAsset.setVisible(true);
        add(swordAsset);
        setBreaks(false);
        setVisible(true);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
    }

}