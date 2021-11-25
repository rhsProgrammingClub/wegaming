import java.util.ArrayList;

import ky.CollisionEntity;
import ky.Vector2D;

public class DamageEntity extends CollisionEntity {

    public double damage;
    public double knockback;
    public boolean active = false;

    public DamageEntity(Vector2D position, int collisionBoxWidth, int collisionBoxHeight, int layer, String name) {
        super(position, collisionBoxWidth, collisionBoxHeight, layer, name);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
    }

    @Override
    public void onCollision(CollisionEntity collidingEntity) {

    }
    
}
