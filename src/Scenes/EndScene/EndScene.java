import java.awt.Color;
import java.awt.Font;

import ky.Text;
import ky.Vector2D;

public class EndScene extends Scene {

    int winningPlayer = 0;
    Text text;

    public EndScene () {
    }

    @Override
    public int changeScene() {
        return 3;
    }

    @Override
    public void initialize() {
        if (Main.player1.lives <= 0) {
            winningPlayer = 2;
        }
        if (Main.player2.lives <= 0) {
            winningPlayer = 1;
        }
        text = new Text("Player _ Wins", new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 50),
                        Color.BLACK, new Vector2D(600, 200), 400, 70, 5);
        
        if (winningPlayer == 1) {
            text.setText("Player 1 Wins");
        }
        if (winningPlayer == 2) {
            text.setText("Player 2 Wins");
        }
        text.setVisible(true);
        add(text);
    }
    
}