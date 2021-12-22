import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class Flame extends DamageEntity {

    private final double realDamage = 450;
    Asset flameAsset;

    public Flame(Vector2D position, int player) {
        super(position, 256, 200, 2, player, 0);
        setBreaks(false);
        flameAsset = new Asset("assets/characters/boxrobot/flame.png",
                new Vector2D(0, 0), 2);

        flameAsset.setVisible(true);
        flameAsset.rescale(2);
        add(flameAsset);

    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        setDamage(realDamage * deltaT);
    }

    @Override
    public void onCollision(CollisionEntity collidingEntity) {
        super.onCollision(collidingEntity);
        canDamage = true;
    }

}