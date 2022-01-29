import java.util.ArrayList;

import ky.Asset;
import ky.AudioPlayer;
import ky.Vector2D;

public class StabbyRobot extends Character {

    Sword sword;
    AudioPlayer knifeSound;

    public StabbyRobot(Main main) {
        super(new Vector2D(0, 0), 75, 140, 1700, main);
    }

    public StabbyRobot(Vector2D position, Main main) {
        super(position, 75, 140, 1700, main);
    }

    @Override
    public void initialize() {
        // setIcon(new Asset("assets/characters/stabbyrobot/icon.png", new Vector2D(0,
        // 0), 3));
        /*
         * characterAnimation = new AnimationAsset(images, position, animationTime,
         * layer);
         * characterAnimation.setVisible(true);
         * add(characterAnimation);
         */
        sword = new Sword(main, new Vector2D(getX(), getY()), 4, getPlayer(), 175);
        add(sword);
        abilityCooldown = 5;
        ultimateCooldown = 20;
        curAbilityCooldown = abilityCooldown;
        curUltCooldown = ultimateCooldown;
        setDefense(0.25);

        abilityIcon = new Asset("assets/characters/stabbyrobot/ability_icon.png", new Vector2D(0, 0), 4);
        ultIcon = new Asset("assets/characters/stabbyrobot/ult_icon.png", new Vector2D(0, 0), 4);

        knifeSound = new AudioPlayer("assets/SFX/knife_swing.wav");
    }

    double tempOffSet = 0;
    double eee = 500;

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

        sword.knockbackDir = direction.getValue();

        if (sword.getDamage() == 275) {
            abilityUpTime -= deltaT;
            if (abilityUpTime <= 0) {
                sword.setDamage(175);
                sword.swordAsset.setImageIndex(0);
                abilityUpTime = 2;
            }
        }
        if (sword.getDamage() == 400) {
            ultimateUpTime -= deltaT;
            if (ultimateUpTime <= 0) {
                sword.setDamage(175);
                sword.setCollisionBoxDimensions(sword.getCollisionBox().width / 2, sword.getCollisionBox().height / 2);
                sword.swordAsset.setImageIndex(0);
                sword.swordAsset.rescale(0.5);
                ultimateUpTime = 5;
            }
        }

        if (status == Status.ATTACKING) {
            sword.setPos(new Vector2D(tempOffSet * direction.getValue() + eee * deltaT * direction.getValue(), 0));
            sword.addPos(this.getPos());
            tempOffSet += eee * deltaT;
            if (tempOffSet >= 120) {
                tempOffSet = 120;
                eee = -500;
            }
            if (tempOffSet <= 0) {
                tempOffSet = 0;
                eee = 500;
                status = Status.IDLE;
                sword.canDamage = false; // Reset the canDamage state so we can't just walk into the enemy after
                                         // swinging to hit them
            }
        } else {
            sword.setPos(this.getPos());
        }
    }

    @Override
    protected void basicAttack() {
        if (status == Status.IDLE) {
            knifeSound.reset();
            knifeSound.play();
            status = Status.ATTACKING;
            sword.canDamage = true;
        }
    }

    @Override
    protected void basicAbility() {
        if (sword.getDamage() == 175) {
            sword.swordAsset.setImageIndex(1);
            abilityUpTime = 2;
            sword.setDamage(275);
        }
    }

    @Override
    protected void ultimate() {
        ultimateCooldown = 20;
        sword.setCollisionBoxDimensions(sword.getCollisionBox().width * 2, sword.getCollisionBox().height * 2);
        sword.swordAsset.rescale(2);
        sword.swordAsset.setImageIndex(2);
        sword.setDamage(400);
        ultimateUpTime = 5;
    }

}
