import java.awt.event.KeyEvent;
import java.util.ArrayList;
import ky.AnimationAsset;
import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public abstract class Character extends CollisionEntity {

    // CollisionEn
    ArrayList<CollisionEntity> entities = new ArrayList<CollisionEntity>();

    int player=1;
    protected double maxHP;
    protected double HP;
    protected double speed = 10000;
    protected double jumpHeight = 1200;
    protected double attackRange;
    protected double gravity = 3000;

    protected AnimationAsset characterAnimation;
    protected Asset icon;

    private int basicAttackKey;
    private int basicAbilityKey;
    private int ultimateKey;
    private int jumpKey;
    private int rightKey;
    private int leftKey;
    private int downKey;
    protected boolean canJump = false;

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

    protected Status status = Status.ATTACKING;
    protected Direction direction = Direction.RIGHT;

    public abstract void initialize ();

    public Character(Vector2D position, int width, int height, double maxHp, int layer) {
        super(position, width, height, layer, "character");
        this.maxHP = maxHp;
        HP = maxHp;
        setCollision(false);
        setPlayer(player);
    }

    public void setPlayer (int player) {
        this.player = player;
        if (player == 1) {
            basicAttackKey = KeyEvent.VK_E;
            basicAbilityKey = KeyEvent.VK_C;
            ultimateKey = KeyEvent.VK_Q;
            jumpKey = KeyEvent.VK_W;
            rightKey = KeyEvent.VK_D;
            leftKey = KeyEvent.VK_A;
            downKey = KeyEvent.VK_S;

        } else if (player == 2) {
            basicAttackKey = KeyEvent.VK_O;
            basicAbilityKey = KeyEvent.VK_COMMA;
            ultimateKey = KeyEvent.VK_U;
            jumpKey = KeyEvent.VK_I;
            rightKey = KeyEvent.VK_L;
            leftKey = KeyEvent.VK_J;
            downKey = KeyEvent.VK_K;

        }
    }

    public CollisionEntity[] getEntities () {
		return entities.toArray(new CollisionEntity[entities.size()]);
    }

    public void add (CollisionEntity entity) {
        entities.add(entity);
        // System.out.println(position.getX());
        // entity.setPos(entity.getX() + position.getX(), entity.getY() + position.getY());
    }

    public void setIcon (Asset icon) {
        this.icon = icon;
    }

    public Asset getIcon () {
        return icon;
    }

    @Override
    public void onCollision(CollisionEntity ce) {
        if (ce.getName().equals("ground")) {
            setVel(new Vector2D(0, 0));
            setPos(new Vector2D(getX(), ce.getYCollisionBox().getY()-getCollisionBox().height/2));
            canJump = true;
        }
        if (ce instanceof DamageEntity && ce.isVisible()) {
            if (((DamageEntity)ce).getPlayer() != this.player) {
                if (((DamageEntity)ce).canDamage) {
                    this.HP-=((DamageEntity)ce).getDamage();
                    ce.setVisible(!((DamageEntity)ce).getBreaks());
                    ((DamageEntity)ce).canDamage=false;
                }
            }
        }
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        // for (CollisionEntity e : entities) {
        //     e.setPos(e.getX() + getX(), e.getY() + getY());
        // }
        if (player==2) {
            System.out.println(HP);
        }
        // if (HP <= 0) {
        // }
        if (keyCodes.contains(jumpKey)) jump();
        if (keyCodes.contains(basicAttackKey)) basicAttack();
        if (keyCodes.contains(basicAbilityKey)) basicAbility();
        if (keyCodes.contains(ultimateKey)) ultimate();

        if (getVel().getX() < 100) {
            if (keyCodes.contains(rightKey)) {
                addVel(new Vector2D(deltaT * speed, 0));
                direction = Direction.RIGHT;
            }
            if (keyCodes.contains(leftKey)) {
                addVel(new Vector2D(-deltaT * speed, 0));
                direction = Direction.LEFT;
            }
        }
        // addVel(new Vector2D(-getVel().getX() * deltaT * 100, 0));
        
        if (!canJump)
            addVel(new Vector2D(0, deltaT * gravity));

    }

    protected void jump () {
        if (canJump) {
            setVel(new Vector2D(0, -jumpHeight));
            canJump = false;
        }
    }

    protected abstract void basicAttack ();
    protected abstract void basicAbility ();
    protected abstract void ultimate ();

}