import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ky.Text;
import ky.Vector2D;

public class EndScene extends Scene {

    int winningPlayer = 0;
    Text winText;
    Text rematchText;

    @Override
    public void initialize() {
        sceneIndex = 4;
        if (Main.player1.lives <= 0) {
            winningPlayer = 2;
        }
        if (Main.player2.lives <= 0) {
            winningPlayer = 1;
        }
        winText = new Text(
                "Player _ Wins", 
                new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 50),
                Color.BLACK, 
                new Vector2D(600, 200), 
                400, 
                70, 
                5);
        
        if (winningPlayer == 1) {
            winText.setText("Player 1 Wins");
        }
        if (winningPlayer == 2) {
            winText.setText("Player 2 Wins");
        }
        winText.setVisible(true);
        add(winText);

        rematchText = new Text(
                "Press SPACE to return to Main Menu.",
                new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 40),
                Color.darkGray,
                new Vector2D(750, 600), 
                800, 
                60, 
                5);
        rematchText.setVisible(true);
        add(rematchText);
}

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.contains(KeyEvent.VK_SPACE)) {
            sceneIndex = 1;
        }
    }
    
}