import java.util.ArrayList;

import ky.CollisionEntity;
import ky.Vector2D;

public class Flame extends DamageEntity {

    private final double realDamage = 250;

    public Flame(Vector2D position, int collisionBoxWidth, int collisionBoxHeight, int layer, int player) {
        super(position, collisionBoxWidth, collisionBoxHeight, layer, player, 0);
        setBreaks(false);
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