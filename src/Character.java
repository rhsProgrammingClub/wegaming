import java.util.ArrayList;

import ky.AnimationAsset;
import ky.Asset;
import ky.CollisionEntity;

public class Character extends CollisionEntity {

    double maxHP;
    double HP;
    int player=1;

    protected AnimationAsset characterAnimation;
    protected Asset icon;

    public Character(int collisionBoxWidth, int collisionBoxHeight, double maxHp, String name, int layer) {
        super(0, 0, collisionBoxWidth, collisionBoxHeight, layer, name);
        setCollision(false);
    
    }

    public void setIcon (Asset icon) {
        this.icon = icon;
    }

    public Asset getIcon () {
        return icon;
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