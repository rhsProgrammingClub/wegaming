import java.util.ArrayList;

import ky.AnimationAsset;
import ky.CollisionEntity;

public class Character extends CollisionEntity {

    double maxHP;
    double HP;

    AnimationAsset characterAnimation;

    public Character(int collisionBoxWidth, int collisionBoxHeight, double maxHp, String name, int layer) {
        super(0, 0, collisionBoxWidth, collisionBoxHeight, layer, name);
        setCollision(false);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {

    }

    @Override
    public void onCollision(CollisionEntity ce) {
        if (ce.getName().equals("ground")) { // stop falling
            setVel(getVel().getX(), 0);
        }
    }

}