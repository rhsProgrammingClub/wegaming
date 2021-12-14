/*
    A class dedicated to managing the specific inputs for a player. A player should be bound by their index, not necessarily an object.
*/

import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class PlayerInput {

    public static PlayerInput PLAYER_ONE_INPUT = new PlayerInput(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_U, KeyEvent.VK_I, KeyEvent.VK_O);
    public static PlayerInput PLAYER_TWO_INPUT = new PlayerInput(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3);

    public static ArrayList<Integer> usedKeys;

    public int playerIndex = 0; // Player 1 and 2
    
    public int leftKey = KeyEvent.VK_LEFT;
    public int rightKey = KeyEvent.VK_RIGHT;
    public int upKey = KeyEvent.VK_UP;
    public int downKey = KeyEvent.VK_DOWN;
    public int attackKey = KeyEvent.VK_F; 

    public int ultimateKey; // i really dont want to update the constructor. welp
    public int basicAbilityKey; // same here

    public PlayerInput(int left, int right, int up, int down, int attack, int ability, int ultimate) {
        if (usedKeys == null) {
            usedKeys = new ArrayList<Integer>();
        }
        // leftKey=left;
        // rightKey=right;
        // upKey=up;
        // downKey=down;
        // attackKey=attack;
        // basicAbilityKey = ability;
        // ultimateKey = ultimate;
        setLeft(left);
        setRight(right);
        setUp(up);
        setDown(down);
        setAttack(attack);
        setAbility(ability);
        setUltimate(ultimate);
    }

    

    boolean setKey (int keyNum, int key) {
        if (keyNum >= 7) keyNum-=7;
        switch (keyNum) {
            case 0:
                return setLeft(key);
            case 1:
                return setRight(key);
            case 2:
                return setUp(key);
            case 3:
                return setDown(key);
            case 4:
                return setAttack(key);
            case 5:
                return setAbility(key);
            case 6:
                return setUltimate(key);
            default:
                System.out.println("oh?");
                return false;
        }
    }

    private boolean setLeft (int key) {
        if (usedKeys.contains(key)) {
            return false;
        }
        usedKeys.remove((Integer)leftKey);
        leftKey = key;
        usedKeys.add(leftKey);
        return true;
    }

    private boolean setRight (int key) {
        if (usedKeys.contains(key)) {
            return false;
        }
        usedKeys.remove((Integer)rightKey);
        rightKey = key;
        usedKeys.add(rightKey);
        return true;
    }

    private boolean setUp (int key) {
        if (usedKeys.contains(key)) {
            return false;
        }
        usedKeys.remove((Integer)upKey);
        upKey = key;
        usedKeys.add(upKey);
        return true;
    }

    private boolean setDown (int key) {
        if (usedKeys.contains(key)) {
            return false;
        }
        usedKeys.remove((Integer)downKey);
        downKey = key;
        usedKeys.add(downKey);
        return true;
    }

    private boolean setAttack (int key) {
        if (usedKeys.contains(key)) {
            return false;
        }
        usedKeys.remove((Integer)attackKey);
        attackKey = key;
        usedKeys.add(attackKey);
        return true;
    }

    private boolean setAbility (int key) {
        if (usedKeys.contains(key)) {
            return false;
        }
        usedKeys.remove((Integer)basicAbilityKey);
        basicAbilityKey = key;
        usedKeys.add(basicAbilityKey);
        return true;
    }

    private boolean setUltimate (int key) {
        if (usedKeys.contains(key)) {
            return false;
        }
        usedKeys.remove((Integer)ultimateKey);
        ultimateKey = key;
        usedKeys.add(ultimateKey);
        return true;
    }

}
