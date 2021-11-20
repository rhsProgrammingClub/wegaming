import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ky.AnimationAsset;
import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public abstract class Character extends CollisionEntity {

    double maxHP;
    double HP;
    double speed = 1000;
    double jumpHeight = 1200;
    int player=1;
    protected double attackRange;
    protected double gravity = 3000;

    protected AnimationAsset characterAnimation;
    protected Asset icon;

    protected int basicAttackKey;
    protected int basicAbilityKey;
    protected int ultimateKey;
    protected int jumpKey;
    protected int rightKey;
    protected int leftKey;
    protected int downKey;
    protected boolean canJump = true;

    public void setPlayer (int player) {
        this.player = player;
        if (player == 1) {
            basicAttackKey = KeyEvent.VK_E;
            basicAbilityKey = KeyEvent.VK_Q;
            ultimateKey = KeyEvent.VK_X;
            jumpKey = KeyEvent.VK_W;
            rightKey = KeyEvent.VK_D;
            leftKey = KeyEvent.VK_A;
            downKey = KeyEvent.VK_S;

        } else if (player == 2) {
            basicAttackKey = KeyEvent.VK_O;
            basicAbilityKey = KeyEvent.VK_U;
            ultimateKey = KeyEvent.VK_COMMA;
            jumpKey = KeyEvent.VK_I;
            rightKey = KeyEvent.VK_L;
            leftKey = KeyEvent.VK_J;
            downKey = KeyEvent.VK_K;

        }
    }

    public Character(int width, int height, double maxHp, String name, int layer, int player) {
        super(0, 0, width, height, layer, name);
        setCollision(false);
        this.player = player;
        setPlayer(player);
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