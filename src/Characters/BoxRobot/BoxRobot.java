import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class BoxRobot extends Character {

    private Flame flame;
    private Saw saw;
    private double flameUpTime;
    private double armourUpTime;
    private int lastDirection;

    public BoxRobot(Vector2D position, Main main) {
        super(position, 256, 256, 1800, main);
    }

    public BoxRobot(Main main) {
        super(new Vector2D(0, 0), 256, 256, 1800, main);

    }

    @Override
    public void initialize() {
        setDefense(0.4);
        abilityCooldown = 4;
        ultimateCooldown = 8;
        jumpHeight = 500;
        speed = 40000;
        maxVelocity = 600;
        curAbilityCooldown = abilityCooldown;
        curUltCooldown = ultimateCooldown;

        characterAsset = new Asset(new String[] {
                "assets/characters/boxrobot/box_robot.png",
                "assets/characters/boxrobot/box_robot_armour.png"
        }, new Vector2D(0, 0), 3);

        characterAsset.setVisible(true);
        add(characterAsset);

        flame = new Flame(new Vector2D(0, 0), getPlayer());
        add(flame);

        saw = new Saw(new Vector2D(0, 0), getPlayer());
        saw.setVisible(true);
        add(saw);

        lastDirection = direction.getValue();

        abilityIcon = new Asset("assets/characters/boxrobot/flame.png", new Vector2D(0, 0), 4);
        ultIcon = new Asset("assets/characters/boxrobot/box_robot_armour.png", new Vector2D(0, 0), 130, 130, 4);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        super.update(deltaT, keyCodes);
        saw.setPos(new Vector2D(getX() + 120 * direction.getValue(), getY() - 15));

        saw.setVisible(status != Status.ABILITY);

        if (status == Status.ATTACKING) {
            saw.sawAsset.rotate(deltaT * 2000);
            saw.canDamage = true;
            status = Status.IDLE;
        }

        if (flameUpTime > 0 && status == Status.ABILITY) {
            flame.setPos(new Vector2D(getX() + 228 * direction.getValue(), getY() - 30));
            flameUpTime -= deltaT;
        } else if (flame.isVisible()) {
            flame.setVisible(false);
            status = Status.IDLE;
        }
        if (armourUpTime > 0) {
            armourUpTime -= deltaT;
        } else if (getDefense() >= 0.8) {
            setDefense(0.5);
            characterAsset.setImageIndex(0);
        }
        if (direction.getValue() != lastDirection) {
            flame.flameAsset.flipHorizontal();
        }
        lastDirection = direction.getValue();

    }

    @Override
    protected void basicAttack() {
        if (status == Status.IDLE) {
            status = Status.ATTACKING;
        }
    }

    @Override
    protected void basicAbility() {
        status = Status.ABILITY;
        flame.setVisible(true);
        flameUpTime = 2.5;
    }

    @Override
    protected void ultimate() {
        setDefense(0.8);
        characterAsset.setImageIndex(1);
        armourUpTime = 1.5;
    }

}
