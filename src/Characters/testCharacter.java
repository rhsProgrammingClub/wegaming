import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class TestCharacter extends Character {

    Sword sword;
    int status = 0; // nothing, basic attack, ablility, ult, jumping

    public TestCharacter () {
        super(new Vector2D(0, 0), 150, 275, 2000, 3);
        // super(position, collisionBoxWidth, collisionBoxHeight, layer, "damageEntity");
        sword = new Sword(new Vector2D(0,0), 120, 40, 5);
        sword.setVisible(true);
        add(sword);
    }

    public TestCharacter (Vector2D position) {
        super(position, 250, 300, 2000, 3);
        // setIcon(new Asset("assets/Characters/testcharacter/icon.png", new Vector2D(0, 0), 3));
        /* 
        characterAnimation = new AnimationAsset(images, position, animationTime, layer);
        characterAnimation.setVisible(true);
        add(characterAnimation);
        */
    }

    public void onCollision(CollisionEntity collidingEntity) {
        if (collidingEntity.getName().equals("ground")) {
            setVel(new Vector2D(0, 0));
            setPos(new Vector2D(getX(), collidingEntity.getYCollisionBox().getY()-getCollisionBox().height/2));
            canJump = true;
        }
    }

    double tempOffSet = 0;
    double eee=1000;

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        super.update(deltaT, keyCodes);
        if (status == 1) {
            sword.setPos(new Vector2D(tempOffSet+eee*deltaT, 0));
            
            tempOffSet += eee*deltaT;
            if (tempOffSet >= 120) {
                eee=-1000;
            }
            if (tempOffSet <= 0) {
                eee=1000;
                status=0;
            }
        }
    }

    @Override
    protected void basicAttack() {
        if (status == 0) {
            status = 1;
        }
    }

    @Override
    protected void basicAbility() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void ultimate() {
        // TODO Auto-generated method stub
        
    }
    
}
