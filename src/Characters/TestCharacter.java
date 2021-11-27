import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class TestCharacter extends Character {

    Sword sword;
    Status status = Status.IDLE;

    public TestCharacter () {
        super(new Vector2D(0, 0), 150, 275, 2000, 3);
        initialize();
    }

    public TestCharacter (Vector2D position) {
        super(position, 250, 300, 2000, 3);
        // setIcon(new Asset("assets/Characters/testcharacter/icon.png", new Vector2D(0, 0), 3));
        /* 
        characterAnimation = new AnimationAsset(images, position, animationTime, layer);
        characterAnimation.setVisible(true);
        add(characterAnimation);
        */
        initialize();
    }

    @Override
    public void initialize() {
        // super(position, collisionBoxWidth, collisionBoxHeight, layer, "damageEntity");
        // sword = new Sword(new Vector2D(0,0), 120, 40, 5);
        sword = new Sword(new Vector2D(getX(), getY()), 120, 40, 4, player, 100);
        sword.setVisible(true);
        add(sword);
        
    }

    public void onCollision(CollisionEntity collidingEntity) {
        super.onCollision(collidingEntity);
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
                sword.canDamage = false; // Reset the canDamage state so we can't just walk into the enemy after swinging to hit them
            }
        } else {
            sword.setPos(this.getPos());
        }
    }

    @Override
    protected void basicAttack() {
        if (status == Status.IDLE) {
            status = Status.ATTACKING;
            this.sword.canDamage=true;
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
