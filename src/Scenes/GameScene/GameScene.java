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
    HealthBar p1HealthBar;
    HealthBar p2HealthBar;
    CooldownBar abilityBarP1;
    CooldownBar abilityBarP2;
    CooldownBar ultBarP1;
    CooldownBar ultBarP2;
    Asset[] p1LivesDisplay;
    Asset[] p2LivesDisplay;

    public GameScene(Main main) {
        super(main);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (player1.lives <= 0 || player2.lives <= 0) {
            main.setScene(4);
        }

        for (int i = 2; i >= 0; i--) {
            if (player1.lives < i + 1) {
                p1LivesDisplay[i].setVisible(false);
            }
            if (player2.lives < i + 1) {
                p2LivesDisplay[i].setVisible(false);
            }
        }

        tTime += deltaT;
        if (tTime >= 1) {
            tTime = 0;
            fpsText.setText("FPS: " + frames);
            frames = 0;
        }
        frames++;
    }

    @Override
    public void initialize() {
        main.resetCharacters();

        // ground = new Ground(Main.width/2, Main.height*0.75, (int)(Main.width*0.6),
        // (int)(Main.height*0.1));
        ground = new Ground(width * 0.5, height * 0.9, width, (int) (height * 0.2));
        add(ground);
        player1 = main.player1;
        player2 = main.player2;

        player1.setPlayer(1);
        player2.setPlayer(2);

        player1.setPos(new Vector2D(player1.getCollisionBox().getWidth() * 0.6,
                height - player1.getCollisionBox().getHeight() * 0.55));
        player2.setPos(new Vector2D(width - player2.getCollisionBox().getWidth() * 0.6,
                height - player2.getCollisionBox().getHeight() * 0.55));

        player1.setVisible(true);
        player2.setVisible(true);
        player1.initialize();
        player2.initialize();
        add(player1);
        add(player2);

        p1HealthBar = new HealthBar(new Vector2D(300, 80), main.player1);
        p2HealthBar = new HealthBar(new Vector2D(Main.width - 300, 80), main.player2);
        p1HealthBar.setVisible(true);
        p2HealthBar.setVisible(true);
        add(p1HealthBar);
        add(p2HealthBar);

        abilityBarP1 = new CooldownBar(new Vector2D(80, 730), player1, false, player1.abilityIcon);
        add(abilityBarP1);
        ultBarP1 = new CooldownBar(new Vector2D(220, 730), player1, true, player1.ultIcon);
        add(ultBarP1);
        abilityBarP2 = new CooldownBar(new Vector2D(1500 - 220, 730), player2, false, player2.abilityIcon);
        add(abilityBarP2);
        ultBarP2 = new CooldownBar(new Vector2D(1500 - 80, 730), player2, true, player2.ultIcon);
        add(ultBarP2);

        fpsText = new Text("FPS: ", new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 30), Color.BLACK,
                new Vector2D(120, 130), 200, 40, 4);
        fpsText.setVisible(true);
        add(fpsText);

        p1LivesDisplay = new Asset[3];
        p2LivesDisplay = new Asset[3];
        for (int i = 0; i < 3; i++) {
            p1LivesDisplay[i] = new Asset(
                    "assets/misc/heart.png",
                    new Vector2D(80 + i * 80, 200),
                    64,
                    64,
                    5);

            p1LivesDisplay[i].setVisible(true);
            add(p1LivesDisplay[i]);

            p2LivesDisplay[i] = new Asset(
                    "assets/misc/heart.png",
                    new Vector2D(1240 + i * 80, 200),
                    64,
                    64,
                    5);

            p2LivesDisplay[i].setVisible(true);
            add(p2LivesDisplay[i]);

        }

        // Asset background = new Asset("assets/backgrounds/background-1.png", new
        // Vector2D(width/2,height/2-200), 1);
        // background.setVisible(true);
        // background.rescale(1.65);
        // add(background);
    }
}
