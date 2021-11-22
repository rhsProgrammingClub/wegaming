import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import ky.Asset;
import ky.Text;
import ky.Vector2D;

public class GameScene extends Scene {

    static Ground ground;
    static Character player1;
    static Character player2;
    Text fpsText;
    double tTime = 0;
    int frames = 0;
    Asset p1HealthBar;
    Asset p2HealthBar;

    public GameScene () {
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        tTime += deltaT;

        if (tTime >= 1) {
            tTime = 0;
            fpsText.setText("FPS: " + frames);
            frames=0;
        }
        frames++;
    }

    @Override
    public int changeScene() {
        return 2;
    }

    @Override
    public void initialize() {
        // ground = new Ground(Main.width/2, Main.height*0.75, (int)(Main.width*0.6), (int)(Main.height*0.1));
        ground = new Ground(width*0.5, height*0.9, width, (int)(height*0.2));
        add(ground);
        player1 = Main.player1;
        player2 = Main.player2;

        player1.setPlayer(1);
        player2.setPlayer(2);

        player1.setPos(new Vector2D(player1.getCollisionBox().getWidth()*0.6, height-player1.getCollisionBox().getHeight()*0.55));
        player2.setPos(new Vector2D(width-player2.getCollisionBox().getWidth()*0.6, height-player2.getCollisionBox().getHeight()*0.55));


        player1.setVisible(true);
        player2.setVisible(true);
        add(player1);
        add(player2);

        // p1HealthBar = new Asset("assets/misc/healthBar.png", new Vector2D(0, 0), 4);
        // p1HealthBar.setPos(p1HealthBar.getWidth()/2, p1HealthBar.getHeight()/2 + height*0.1);
        // p2HealthBar = new Asset("assets/misc/healthBar.png", new Vector2D(width, height), 4);
        // p2HealthBar.setPos(width-p1HealthBar.getWidth()/2, p1HealthBar.getHeight()/2 + height*0.1);

        fpsText = new Text("FPS: ", new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 30) 
                        ,Color.BLACK, new Vector2D(100, 20), 200, 40, 4);
        fpsText.setVisible(true);
        add(fpsText);
        
    }
}
