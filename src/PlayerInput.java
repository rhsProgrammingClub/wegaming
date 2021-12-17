/*
    A class dedicated to managing the specific inputs for a player. A player should be bound by their index, not necessarily an object.
*/

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static java.awt.event.KeyEvent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PlayerInput {

    public static PlayerInput PLAYER_ONE_INPUT = new PlayerInput(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_Q, KeyEvent.VK_E, KeyEvent.VK_F);
    public static PlayerInput PLAYER_TWO_INPUT = new PlayerInput(KeyEvent.VK_J, KeyEvent.VK_L, KeyEvent.VK_I, KeyEvent.VK_K, KeyEvent.VK_U, KeyEvent.VK_O, KeyEvent.VK_P);

    public static ArrayList<Integer> usedKeys;

    public int playerIndex = 0; // Player 1 and 2
    
    public AtomicInteger leftKey = new AtomicInteger(KeyEvent.VK_LEFT);
    public AtomicInteger rightKey = new AtomicInteger(KeyEvent.VK_RIGHT);
    public AtomicInteger upKey = new AtomicInteger(KeyEvent.VK_UP);
    public AtomicInteger downKey = new AtomicInteger(KeyEvent.VK_DOWN);
    public AtomicInteger attackKey = new AtomicInteger(KeyEvent.VK_F); 

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

    public char getKey (int keyNum) {
        if (keyNum >= 7) keyNum-=7;
        switch (keyNum) {
            case 0:
                return KeyEvent.getKeyText(leftKey.get()).charAt(0);
            case 1:
                return KeyEvent.getKeyText(rightKey.get()).charAt(0);
            case 2:
                return KeyEvent.getKeyText(upKey.get()).charAt(0);
            case 3:
                return KeyEvent.getKeyText(downKey.get()).charAt(0);
            case 4:
                return KeyEvent.getKeyText(attackKey.get()).charAt(0);
            case 5:
                return KeyEvent.getKeyText(basicAbilityKey.get()).charAt(0);
            case 6:
                return KeyEvent.getKeyText(ultimateKey.get()).charAt(0);
            default:
                System.out.println("oh?");
                return 'L';
        }
    }

    public boolean setKey (int keyNum, int key) {
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

    private boolean setKey(AtomicInteger pointer, char newKey) {
        int newInt = charToInt(newKey);
        if(usedKeys.contains(newInt)) {return false;}
        pointer.set(newInt);
        return true;
    }

    private boolean setKey(AtomicInteger pointer, int newKey) {
        if(usedKeys.contains(newKey)) return false;
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

    public int charToInt(char character) {
        switch (character) {
            case 'a': return (VK_A);
            case 'b': return (VK_B);
            case 'c': return (VK_C);
            case 'd': return (VK_D);
            case 'e': return (VK_E);
            case 'f': return (VK_F);
            case 'g': return (VK_G);
            case 'h': return (VK_H);
            case 'i': return (VK_I);
            case 'j': return (VK_J);
            case 'k': return (VK_K);
            case 'l': return (VK_L);
            case 'm': return (VK_M);
            case 'n': return (VK_N);
            case 'o': return (VK_O);
            case 'p': return (VK_P);
            case 'q': return (VK_Q);
            case 'r': return (VK_R);
            case 's': return (VK_S);
            case 't': return (VK_T);
            case 'u': return (VK_U);
            case 'v': return (VK_V);
            case 'w': return (VK_W);
            case 'x': return (VK_X);
            case 'y': return (VK_Y);
            case 'z': return (VK_Z);
        }
        return 0;
    }

    public AtomicInteger charToAtomInt(char x) {
        return new AtomicInteger(charToInt(x));
    }

}
