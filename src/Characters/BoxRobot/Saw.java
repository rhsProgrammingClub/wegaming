import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class Saw extends DamageEntity {

    private final double realDamage = 250;
    Asset sawAsset;

    public Saw(Main main, Vector2D position, int player) {
        super(main, position, 64, 64, 2, player, 150);
        sawAsset = new Asset("assets/characters/boxrobot/saw.png",
                new Vector2D(0, 0), 64, 64, 2);
        sawAsset.setVisible(true);
        add(sawAsset);
    }

    @Override
    public void updateEntity(double deltaT, ArrayList<Integer> keyCodes) {
        setDamage(realDamage * deltaT);
    }

}
