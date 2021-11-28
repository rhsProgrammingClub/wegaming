import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class Sword extends DamageEntity {

    Asset swordAsset;

    public Sword(Vector2D position, int collisionBoxWidth, int collisionBoxHeight, int layer, int player, double damage) {
        super(position, collisionBoxWidth, collisionBoxHeight, layer, player, damage);
        setBreaks(false);
        swordAsset = new Asset(
                        new String[] {"assets/Characters/testcharacter/sword.png", 
                        "assets/Characters/testcharacter/swordBoosted.png"},
                        new Vector2D(0, 0), 120, 40, 5);

        swordAsset.setVisible(true);
        add(swordAsset);

        setVisible(true);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
    }
    
}