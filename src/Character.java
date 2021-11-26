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

    private PlayerInput playerInput;
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
        if(player == 1) {playerInput = PlayerInput.PLAYER_ONE_INPUT; }
        else if(player == 2) {playerInput = PlayerInput.PLAYER_TWO_INPUT;}
    }


    public void setIcon (Asset icon) {
        this.icon = icon;
    }

    public Asset getIcon () {
        return icon;
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.contains(playerInput.upKey))
            jump();
        if (keyCodes.contains(playerInput.attackKey))
            basicAttack();
        if (keyCodes.contains(playerInput.basicAbilityKey))
            basicAbility();
        if (keyCodes.contains(playerInput.ultimateKey))
            ultimate();
        
        if (keyCodes.contains(playerInput.rightKey)) {
            addPos(new Vector2D(deltaT * speed, 0));
        }
        if (keyCodes.contains(playerInput.leftKey)) {
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
