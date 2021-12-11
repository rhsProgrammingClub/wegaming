import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import ky.Text;
import ky.Vector2D;

public class GameScene extends Scene {

    static Ground ground;
    static Character player1;
    static Character player2;
    Text fpsText;
    double tTime = 0;
    int frames = 0;
    HealthBar p1HealthBar;
    HealthBar p2HealthBar;
    CooldownBar abilityBarP1;
    CooldownBar abilityBarP2;
    CooldownBar ultBarP1;
    CooldownBar ultBarP2;

    public GameScene () {
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (player1.lives <= 0 || player2.lives <= 0) {
            sceneIndex = 3;
        }

        tTime += deltaT;
        if (tTime >= 1) {
            tTime = 0;
            fpsText.setText("FPS: " + frames);
            frames=0;
        }
        frames++;
    }

    @Override
    public void initialize() {
        sceneIndex = 2;
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
        player1.initialize();
        player2.initialize();
        add(player1);
        add(player2);

        p1HealthBar = new HealthBar(new Vector2D(300, 80), Main.player1);
        p2HealthBar = new HealthBar(new Vector2D(Main.width - 300, 80), Main.player2);
        p1HealthBar.setVisible(true);
        p2HealthBar.setVisible(true);
        add(p1HealthBar);
        add(p2HealthBar);

        abilityBarP1 = new CooldownBar(new Vector2D(80, 730), player1, false, player1.abilityIcon);
        add(abilityBarP1);
        ultBarP1 = new CooldownBar(new Vector2D(220, 730), player1, true, player1.ultIcon);
        add(ultBarP1);
        abilityBarP2 = new CooldownBar(new Vector2D(1500-220, 730), player2, false, player2.abilityIcon);
        add(abilityBarP2);
        ultBarP2 = new CooldownBar(new Vector2D(1500-80, 730), player2, true, player2.ultIcon);
        add(ultBarP2);

        fpsText = new Text("FPS: ", new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 30) 
                        ,Color.BLACK, new Vector2D(120, 130), 200, 40, 4);
        fpsText.setVisible(true);
        add(fpsText);
        
    }
}
