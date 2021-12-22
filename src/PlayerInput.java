
/*
    A class dedicated to managing the specific inputs for a player. A player should be bound by their index, not necessarily an object.
*/
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerInput {

    public static PlayerInput PLAYER_ONE_INPUT = new PlayerInput(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W,
            KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E, KeyEvent.VK_F);
    public static PlayerInput PLAYER_TWO_INPUT = new PlayerInput(KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_I,
            KeyEvent.VK_K, KeyEvent.VK_U, KeyEvent.VK_O, KeyEvent.VK_P);

    public static ArrayList<Integer> usedKeys;

    public int playerIndex = 0; // Player 1 and 2

    public AtomicInteger leftKey = new AtomicInteger(0);
    public AtomicInteger rightKey = new AtomicInteger(0);
    public AtomicInteger upKey = new AtomicInteger(0);
    public AtomicInteger downKey = new AtomicInteger(0);
    public AtomicInteger attackKey = new AtomicInteger(0);

    public AtomicInteger ultimateKey = new AtomicInteger(0); // i really dont want to update the constructor. welp
    public AtomicInteger basicAbilityKey = new AtomicInteger(0); // same here

    public PlayerInput(int left, int right, int up, int down, int attack, int ability, int ultimate) {
        if (usedKeys == null) {
            usedKeys = new ArrayList<Integer>();
        }

        setLeft(left);
        setRight(right);
        setUp(up);
        setDown(down);
        setAttack(attack);
        setAbility(ability);
        setUltimate(ultimate);
    }

    public static boolean setKey(AtomicInteger pointer, int newKey) {
        if (usedKeys.contains(newKey))
            return false;
        usedKeys.remove((Integer) pointer.get());
        usedKeys.add(newKey);
        pointer.set(newKey);
        return true;
    }

    private boolean setLeft(int newKey) {
        return setKey(leftKey, newKey);
    }

    private boolean setRight(int newKey) {
        return setKey(rightKey, newKey);
    }

    private boolean setUp(int newKey) {
        return setKey(upKey, newKey);
    }

    private boolean setDown(int newKey) {
        return setKey(downKey, newKey);
    }

    private boolean setAttack(int newKey) {
        return setKey(attackKey, newKey);
    }

    private boolean setAbility(int newKey) {
        return setKey(basicAbilityKey, newKey);
    }

    private boolean setUltimate(int newKey) {
        return setKey(ultimateKey, newKey);
    }

    public AtomicInteger[] orderedInputs() {
        return new AtomicInteger[] { leftKey, rightKey, upKey, downKey, attackKey, basicAbilityKey, ultimateKey };
    }

}
