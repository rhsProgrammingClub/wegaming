import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class TestCharacter extends Character {

    Sword sword;
    Status status = Status.IDLE;

    public TestCharacter () {
        super(new Vector2D(0, 0), 150, 275, 2000, 3);
        // super(position, collisionBoxWidth, collisionBoxHeight, layer, "damageEntity");
        // sword = new Sword(new Vector2D(0,0), 120, 40, 5);
        sword = new Sword(new Vector2D(getX(), getY()), 120, 40, 4);
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
        sword = new Sword(new Vector2D(getX(), getY()), 120, 40, 4);
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
        if (status == Status.ATTACKING) {
            sword.setPos(new Vector2D(tempOffSet*direction.getValue() +eee*deltaT*direction.getValue(), 0));
            sword.addPos(this.getPos());
            tempOffSet += eee*deltaT;
            if (tempOffSet >= 120) {
                eee=-1000;
            }
            if (tempOffSet <= 0) {
                eee=1000;
                status=Status.IDLE;
            }
        } else {
            sword.setPos(this.getPos());
        }
    }

    @Override
    protected void basicAttack() {
        if (status == Status.IDLE) {
            status = Status.ATTACKING;
        }
    }

    @Override
    protected void basicAbility() {
        // TODO Auto-generated method stub
        
    }

    @Override
    protected void ultimate() {

        
    }
    
}
