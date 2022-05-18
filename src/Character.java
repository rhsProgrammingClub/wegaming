import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public abstract class Character extends CollisionEntity {

    ArrayList<CollisionEntity> entities = new ArrayList<CollisionEntity>();

    private int player = 1;
    protected double maxHP;
    protected double HP;
    protected double speed = 100000;
    protected double jumpHeight = 1200;
    protected double gravity = 3000;
    protected double maxVelocity = 700;
    protected double abilityCooldown = -1;
    protected double ultimateCooldown = -1;
    protected double curAbilityCooldown = -1;
    protected double curUltCooldown = -1;
    protected int lives = 3;
    private double defense = 0;

    protected Asset characterAsset;
    protected Asset icon;
    protected Asset abilityIcon;
    protected Asset ultIcon;
    private boolean flipped = false;

    private PlayerInput playerInput;
    protected boolean onGround = false;
    protected Main main;

    private File characterFile;

    protected enum Status {
        IDLE, ATTACKING, ABILITY, ULTIMATE, JUMPING
    }

    protected enum Direction {
        RIGHT(1), LEFT(-1);

        private final int value;

        private Direction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    protected Status status = Status.IDLE;
    protected Direction direction = Direction.RIGHT;

    public abstract void initialize();

    public Character(Vector2D position, int width, int height, double maxHp, Main main) {
        super(position, width, height, 3, "character");
        this.main = main;
        this.maxHP = maxHp;
        HP = maxHp;
        setCollision(false);
        setPlayer(player);
    }

    public void setStats(String filename) {
        characterFile = new File(filename);
        Scanner statScan;
        try {
            statScan = new Scanner(characterFile);
        } catch (FileNotFoundException ex) {
            return;
        }
        String statType;
        double statValue;
        while (statScan.hasNextLine()) {
            statType = statScan.next();
            statValue = statScan.nextDouble();
            switch (statType) {
                case "HP:":
                    maxHP = statValue;
                    break;
                case "JUMP_HEIGHT:":
                    jumpHeight = statValue;
                    break;
                case "GRAVITY:":
                    gravity = statValue;
                    break;
                case "SPEED:":
                    speed = statValue;
                    break;
                case "MAX_SPEED:":
                    maxVelocity = statValue;
                    break;
                case "DEFENSE:":
                    defense = statValue;
                    break;
                case "ULT_CD:":
                    ultimateCooldown = statValue;
                    break;
                case "ABILITY_CD:":
                    abilityCooldown = statValue;
                    break;
            }
        }
        HP = maxHP;
        curAbilityCooldown = abilityCooldown;
        curUltCooldown = ultimateCooldown;

        statScan.close();
    }

    public void setPlayer(int player) {
        this.player = player;
        if (player == 1) {
            playerInput = PlayerInput.PLAYER_ONE_INPUT;
        } else if (player == 2) {
            playerInput = PlayerInput.PLAYER_TWO_INPUT;
        }
    }

    public int getPlayer() {
        return player;
    }

    public CollisionEntity[] getEntities() {
        return entities.toArray(new CollisionEntity[entities.size()]);
    }

    public void add(CollisionEntity entity) {
        entities.add(entity);
    }

    public void setIcon(Asset icon) {
        this.icon = icon;
    }

    public Asset getIcon() {
        return icon;
    }

    @Override
    public void onCollision(CollisionEntity ce) {
        if (ce.getName().equals("ground")) {
            setVel(getVel().getX(), 0);
            setPos(new Vector2D(getX(), ce.getYCollisionBox().getY() - getCollisionBox().height / 2));
            onGround = true;
        } else if (ce.getName().equals("platform")) {
            if(getVel().getY() > 0 && getY() + getCollisionBox().width/4 < ce.getY()) { // its inverted, velocity downwards is positive while upwards is negative
                setVel(getVel().getX(), 0);
                setPos(new Vector2D(getX(),  ce.getYCollisionBox().getY() - getCollisionBox().height / 2));
                onGround = true;
            }
        }
    }

    public void setDefense(double val) {
        defense = val;
        if (val > 1)
            defense = 1;
    }

    public double getDefense() {
        return defense;
    }

    public void updateCharacter(double deltaT, ArrayList<Integer> keyCodes) {}

    // do not override update in child classes, change updateCharacter instead
    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (main.currentScene instanceof GameScene) {
            if (((GameScene) main.currentScene).paused) {
                addPos(new Vector2D(-getVel().getX()*deltaT, -getVel().getY()*deltaT));
                // this.setVel(new Vector2D(0, 0));
                return;
            }
        }
        if (HP <= 0) {
            lives--;
            HP = maxHP;
        }
        curAbilityCooldown -= deltaT;
        curUltCooldown -= deltaT;
        if (keyCodes.contains(playerInput.upKey.get()))
            jump();
        if (keyCodes.contains(playerInput.attackKey.get()))
            basicAttack();
        if (keyCodes.contains(playerInput.basicAbilityKey.get())) {
            if (curAbilityCooldown <= 0) {
                curAbilityCooldown = abilityCooldown;
                basicAbility();
            }
        }
        if (keyCodes.contains(playerInput.ultimateKey.get())) {
            if (curUltCooldown <= 0) {
                curUltCooldown = ultimateCooldown;
                ultimate();
            }
        }

        if (Math.abs(getVel().getX()) <= 50) {
            setVel(0, getVel().getY());
        }

        if (keyCodes.contains(playerInput.rightKey.get())) {
            if (Math.abs(getVel().getX()) + deltaT*speed > maxVelocity) {
                if (Math.abs(getVel().getX()) < maxVelocity) {
                    setVel(new Vector2D(maxVelocity, getVel().getY()));
                }
            } else {
                addVel(new Vector2D(deltaT*speed, 0));
            }
            direction = Direction.RIGHT;
            if (flipped && characterAsset != null) {
                characterAsset.flipHorizontal();
                flipped = false;
            }
        }
        if (keyCodes.contains(playerInput.leftKey.get())) {
            if (Math.abs(getVel().getX()) + deltaT*speed > maxVelocity) {
                if (Math.abs(getVel().getX()) < maxVelocity) {
                    setVel(new Vector2D(-maxVelocity, getVel().getY()));
                }
            } else {
                addVel(new Vector2D(-deltaT*speed, 0));
            }
            direction = Direction.LEFT;
            if (!flipped && characterAsset != null) {
                characterAsset.flipHorizontal();
                flipped = true;
            }
        }

        // reduce velocity
        if (!onGround) {
            if (getVel().getX() != 0) {
                addVel(new Vector2D((getVel().getX() > 0 ? -1 : 1) * deltaT * 1200, 0));
            }
            addVel(new Vector2D(0, deltaT*gravity));
        } else {
            if (getVel().getX() != 0) {
                addVel(new Vector2D((getVel().getX() > 0 ? -1 : 1) * deltaT * 1700, 0));
            }
        }

        // cant go off map, might get rid of later
        if (getX() + getVel().getX() * deltaT - getCollisionBox().width / 2 < 0) {
            setVel(new Vector2D(0, getVel().getY()));
            setPos(new Vector2D(getCollisionBox().width / 2, getY()));
        }
        if (getX() + getVel().getX() * deltaT + getCollisionBox().width / 2 > Main.width) {
            setVel(new Vector2D(0, getVel().getY()));
            setPos(new Vector2D(Main.width - getCollisionBox().width / 2, getY()));
        }
        if (getY() + getVel().getY() * deltaT - getCollisionBox().height / 2 < 0) {
            setVel(new Vector2D(getVel().getX(), 0));
            setPos(new Vector2D(getX(), getCollisionBox().height / 2));
        }

        updateCharacter(deltaT, keyCodes);
        onGround = false;
    }

    protected void jump() {
        if (onGround) {
            setVel(getVel().getX(), -jumpHeight);
            onGround = false;
        }
    }

    protected abstract void basicAttack();

    protected abstract void basicAbility();

    protected abstract void ultimate();

}
