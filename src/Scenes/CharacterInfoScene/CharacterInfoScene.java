import ky.Vector2D;
import ky.Text;
import java.awt.Color;
import java.awt.Font;

import ky.Asset;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

public class CharacterInfoScene extends Scene{
    
    private Text[] stabbyrobot = new Text[12];
    private Text[] spaceship = new Text[12];
    private Text[] boxrobot = new Text[12];
    private Text[] assassin = new Text[12];

    Asset logostabby;
    Asset logoship;
    Asset logobox;
    Asset logoassassin;
    
    public CharacterInfoScene (Main main){
        super(main);
    }
    @Override
    public void initialize(){

        logoship = new Asset("assets/characters/spaceship/spaceship_normal.png",
                new Vector2D(width * 0.5 - 150, height * 0.9), 0);
        logostabby = new Asset("assets/characters/stabbyrobot/sword.png", new Vector2D(width * 0.5 - 500, height * 0.9),
                110, 30, 0);
        logobox = new Asset("assets/characters/boxrobot/box_robot.png", new Vector2D(width * 0.5 + 150, height * 0.9),
                110, 110, 0);
        logoassassin = new Asset("assets/characters/assassin/assassin.png", new Vector2D(width * 0.5 + 500, height * 0.9), 
                110, 110, 0);

        logoship.setVisible(true);
        logostabby.setVisible(true);
        logobox.setVisible(true);
        logoassassin.setVisible(true);
        add(logoship);
        add(logostabby);
        add(logobox);
        add(logoassassin);

        //initialize all text
        for(int x = 0; x < 12; x++){
            stabbyrobot[x] = new Text("N/A", 
            new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
            Color.black,
            new Vector2D(width * 0.5 - 500, 100 + x * 50),
            300,
            100,
            5);
            stabbyrobot[x].setVisible(true);
            add(stabbyrobot[x]);

            spaceship[x] = new Text("N/A", 
            new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
            Color.black,
            new Vector2D(width * 0.5 - 150, 100 + x * 50),
            300,
            100,
            5);
            spaceship[x].setVisible(true);
            add(spaceship[x]);

            boxrobot[x] = new Text("N/A", 
            new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
            Color.black,
            new Vector2D(width * 0.5 + 150, 100 + x * 50),
            300,
            100,
            5);
            boxrobot[x].setVisible(true);
            add(boxrobot[x]);

            assassin[x] = new Text("N/A", 
            new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
            Color.black,
            new Vector2D(width * 0.5 + 500, 100 + x * 50),
            300,
            100,
            5);
            assassin[x].setVisible(true);
            add(assassin[x]);
        }

        stabbyrobot[0].setText("Health: 1700");
        stabbyrobot[1].setText("Speed: 100 000");
        stabbyrobot[2].setText("Jump: 1200");
        stabbyrobot[3].setText("Hitbox: 150x275");
        stabbyrobot[4].setText("Defense: 0.25");
        stabbyrobot[5].setText("Damage: 175 (melee)");
        stabbyrobot[6].setText("Ablt: dmg = 275");
        stabbyrobot[7].setText("Ablt cd: 5 secs");
        stabbyrobot[8].setText("Ablt dur: 2 secs");
        stabbyrobot[9].setText("Ult: dmg = 400");
        stabbyrobot[10].setText("Ult cd: 20 secs");
        stabbyrobot[11].setText("Ult dur: 5 secs");

        spaceship[0].setText("Health: 1200");
        spaceship[1].setText("Speed: 100 000");
        spaceship[2].setText("Jump: 600");
        spaceship[3].setText("Hitbox: 150x200");
        spaceship[4].setText("Defense: 0.1");
        spaceship[5].setText("Damage: 150 (range; pierce)");
        spaceship[6].setText("Ablt: 500dmg rocket");
        spaceship[7].setText("Ablt cd: 6 secs");
        spaceship[9].setText("Ult: 4x250dmg homing rockets");
        spaceship[10].setText("Ult cd: 15 secs");

        boxrobot[0].setText("Health: 1800");
        boxrobot[1].setText("Speed: 40 000");
        boxrobot[2].setText("Jump: 500");
        boxrobot[3].setText("Hitbox: 256x256");
        boxrobot[4].setText("Defense: 0.4");
        boxrobot[5].setText("Damage: 250dmg/sec (saw)");
        boxrobot[6].setText("Ablt: 450dmg/sec (flame)");
        boxrobot[7].setText("Ablt cd: 4 secs");
        boxrobot[8].setText("Ablt dur: 2.5 secs");
        boxrobot[9].setText("Ult: defense = 0.8");
        boxrobot[10].setText("Ult cd: 8 secs");
        boxrobot[11].setText("Ult dur: 1.5 secs");

        assassin[0].setText("Health: 1000");
        assassin[1].setText("Speed: 150 000");
        assassin[2].setText("Jump: 800");
        assassin[3].setText("Hitbox: 150x200");
        assassin[4].setText("Defenese: 0");
        assassin[5].setText("Damage: 200 (melee)");
        assassin[6].setText("Ablt: gain 300 hp");
        assassin[7].setText("Ablt cd: 8 secs");
        assassin[9].setText("Ult: dash 700+dmg = 400");
        assassin[10].setText("Ult cd: 16 secs");
        assassin[11].setText("Ult dur: 4 secs");

        
    }   

    public void update(double deltaT, ArrayList<Integer> keyCodes){
        if(keyCodes.contains(KeyEvent.VK_ESCAPE)){
            main.setScene(1);
        }
    }


}
