import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ky.AnimationAsset;
import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public abstract class Character extends CollisionEntity {

    int player=1;
    protected double maxHP;
    protected double HP;
    protected double speed = 1000;
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
            basicAttackKey = KeyEvent.VK_Q;
            basicAbilityKey = KeyEvent.VK_E;
            ultimateKey = KeyEvent.VK_R;
            jumpKey = KeyEvent.VK_W;
            rightKey = KeyEvent.VK_D;
            leftKey = KeyEvent.VK_A;
            downKey = KeyEvent.VK_S;

        } else if (player == 2) {
            basicAttackKey = KeyEvent.VK_U;
            basicAbilityKey = KeyEvent.VK_O;
            ultimateKey = KeyEvent.VK_P;
            jumpKey = KeyEvent.VK_I;
            rightKey = KeyEvent.VK_L;
            leftKey = KeyEvent.VK_J;
            downKey = KeyEvent.VK_K;

        }
    }


    public void setIcon (Asset icon) {
        this.icon = icon;
    }

    public Asset getIcon () {
        return icon;
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.contains(jumpKey))
            jump();
        if (keyCodes.contains(basicAttackKey))
            basicAttack();
        if (keyCodes.contains(basicAbilityKey))
            basicAbility();
        if (keyCodes.contains(ultimateKey))
            ultimate();
        
        if (keyCodes.contains(rightKey)) {
            addPos(new Vector2D(deltaT * speed, 0));
        }
        if (keyCodes.contains(leftKey)) {
            addPos(new Vector2D(-deltaT * speed, 0));
        }
        
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
