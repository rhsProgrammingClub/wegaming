import java.awt.event.KeyEvent;
import java.util.ArrayList;
import ky.AnimationAsset;
import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public abstract class Character extends CollisionEntity {

    // CollisionEn
    ArrayList<CollisionEntity> entities = new ArrayList<CollisionEntity>();

    private int player=1;
    protected double maxHP;
    protected double HP;
    protected double speed = 100000;
    protected double jumpHeight = 1200;
    protected double attackRange;
    protected double gravity = 3000;
    private double maxVelocity = 700;
    protected double abilityCooldown=-1;
    protected double ultimateCooldown=-1;
    protected int lives=3;

    protected Asset characterAsset;
    protected Asset icon;

    private PlayerInput playerInput;
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

    protected Status status = Status.IDLE;
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
        if(player == 1) {playerInput = PlayerInput.PLAYER_ONE_INPUT; }
        else if(player == 2) {playerInput = PlayerInput.PLAYER_TWO_INPUT;}
    }

    public int getPlayer () {
        return player;
    }

    public CollisionEntity[] getEntities () {
		return entities.toArray(new CollisionEntity[entities.size()]);
    }

    public void add (CollisionEntity entity) {
        entities.add(entity);
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
            setVel(new Vector2D(getVel().getX(), 0));
            setPos(new Vector2D(getX(), ce.getYCollisionBox().getY()-getCollisionBox().height/2));
            canJump = true;
        }

        // relys on damage entity instead

        // if (ce instanceof DamageEntity && ce.isVisible()) {
        //     if (((DamageEntity)ce).getPlayer() != this.player) {
        //         if (((DamageEntity)ce).canDamage) {
        //             this.HP-=((DamageEntity)ce).getDamage();
        //             ce.setVisible(!((DamageEntity)ce).getBreaks());
        //             ((DamageEntity)ce).canDamage=false;
        //         }
        //     }
        // }
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (HP <= 0) {
            if (lives > 0) {
                lives--;
                HP = maxHP;
            }
        }
        if (keyCodes.contains(playerInput.upKey)) jump();
        if (keyCodes.contains(playerInput.attackKey)) basicAttack();
        if (keyCodes.contains(playerInput.basicAbilityKey)) basicAbility();
        if (keyCodes.contains(playerInput.ultimateKey)) ultimate();

        if (Math.abs(getVel().getX()) <= 50) {
            setVel(new Vector2D(0, getVel().getY()));
        }

        // if (Math.abs(getVel().getX()) < 400) {
        if (keyCodes.contains(playerInput.rightKey)) {
            addVel(new Vector2D(deltaT * speed, 0));
            direction = Direction.RIGHT;
        }
        if (keyCodes.contains(playerInput.leftKey)) {
            addVel(new Vector2D(-deltaT * speed, 0));
            direction = Direction.LEFT;
        }

        setVel((Math.abs(getVel().getX()) > maxVelocity)
                ? ((getVel().getX()>0) ? 1 : -1) * maxVelocity: getVel().getX(),
                getVel().getY());

        
        if (!canJump) {
            addVel(new Vector2D(-getVel().getX() * deltaT * 1.5, deltaT * gravity));
        } else {
            if (getVel().getX() != 0) {
                addVel(new Vector2D(((getVel().getX() > 0) ? -1 : 1) * deltaT * 1700, 0));
            }
        }

    }

    protected void jump () {
        if (canJump) {
            setVel(new Vector2D(getVel().getX(), -jumpHeight));
            canJump = false;
        }
    }

    protected abstract void basicAttack ();
    protected abstract void basicAbility ();
    protected abstract void ultimate ();

}
