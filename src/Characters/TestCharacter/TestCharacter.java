import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class TestCharacter extends Character {

    Sword sword;

    public TestCharacter () {
        super(new Vector2D(0, 0), 150, 275, 2000, 3);
    }

    public TestCharacter (Vector2D position) {
        super(position, 150, 275, 2000, 3);
    }

    @Override
    public void initialize() {
        // setIcon(new Asset("assets/Characters/testcharacter/icon.png", new Vector2D(0, 0), 3));
        /* 
        characterAnimation = new AnimationAsset(images, position, animationTime, layer);
        characterAnimation.setVisible(true);
        add(characterAnimation);
        */
        // super(position, collisionBoxWidth, collisionBoxHeight, layer, "damageEntity");
        // sword = new Sword(new Vector2D(0,0), 120, 40, 5);
        sword = new Sword(new Vector2D(getX(), getY()), 4, getPlayer(), 100);
        add(sword);
        abilityCooldown = 5;
        ultimateCooldown = 20;
        curAbilityCooldown = abilityCooldown;
        curUltCooldown = ultimateCooldown;
    }

    public void onCollision(CollisionEntity collidingEntity) {
        super.onCollision(collidingEntity);
    }

    double tempOffSet = 0;
    double eee=1000;

    double abilityUpTime = 2;
    double ultimateUpTime = 5;

    Direction lastDirection = Direction.RIGHT;

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        super.update(deltaT, keyCodes);
        
        if (direction != lastDirection) {
            sword.swordAsset.flipHorizontal();
            lastDirection = direction;
        }

        if (sword.getDamage() == 200) {
            abilityUpTime -= deltaT;
            if (abilityUpTime <= 0) {
                sword.setDamage(100);
                sword.swordAsset.setImageIndex(0);
                abilityUpTime = 2;
            }
        }
        if (sword.getDamage() == 250) {
            ultimateUpTime -= deltaT;
            if (ultimateUpTime <= 0) {
                sword.setDamage(100);
                sword.setCollisionBoxDimensions(sword.getCollisionBox().width/2, sword.getCollisionBox().height/2);
                sword.swordAsset.setImageIndex(0);
                sword.swordAsset.rescale(0.5);
                ultimateUpTime=5;
            }
        }

        if (status == Status.ATTACKING) {
            sword.setPos(new Vector2D(tempOffSet*direction.getValue() +eee*deltaT*direction.getValue(), 0));
            sword.addPos(this.getPos());
            tempOffSet += eee*deltaT;
            if (tempOffSet >= 120) {
                tempOffSet = 120;
                eee=-1000;
            }
            if (tempOffSet <= 0) {
                tempOffSet = 0;
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
            sword.canDamage=true;
        }
    }

    @Override
    protected void basicAbility() {
        if (sword.getDamage() == 100) {
            sword.swordAsset.setImageIndex(1);
            abilityUpTime = 2;
            sword.setDamage(200);
        }
    }

    @Override
    protected void ultimate() {
        ultimateCooldown=20;
        sword.setCollisionBoxDimensions(sword.getCollisionBox().width * 2, sword.getCollisionBox().height * 2);
        sword.swordAsset.rescale(2);
        sword.swordAsset.setImageIndex(2);
        sword.setDamage(250);
        ultimateUpTime = 5;
    }
    
}
