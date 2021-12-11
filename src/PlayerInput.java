/*
    A class dedicated to managing the specific inputs for a player. A player should be bound by their index, not necessarily an object.
*/

import java.awt.event.KeyEvent;

public class PlayerInput {

    public static final PlayerInput PLAYER_ONE_INPUT = new PlayerInput(KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_U, KeyEvent.VK_I, KeyEvent.VK_O);
    public static final PlayerInput PLAYER_TWO_INPUT = new PlayerInput(KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_NUMPAD1, KeyEvent.VK_NUMPAD2, KeyEvent.VK_NUMPAD3);

    public int playerIndex = 0; // Player 1 and 2
    
    public int leftKey = KeyEvent.VK_LEFT;
    public int rightKey = KeyEvent.VK_RIGHT;
    public int upKey = KeyEvent.VK_UP;
    public int downKey = KeyEvent.VK_DOWN;
    public int attackKey = KeyEvent.VK_F; 

    public int ultimateKey; // i really dont want to update the constructor. welp
    public int basicAbilityKey; // same here

    public PlayerInput(int left, int right, int up, int down, int attack, int ability, int ultimate) {
        leftKey=left;
        rightKey=right;
        upKey=up;
        downKey=down;
        attackKey=attack;
        basicAbilityKey = ability;
        ultimateKey = ultimate;
    }
}
