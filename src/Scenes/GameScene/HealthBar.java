import java.util.ArrayList;

import ky.Asset;
import ky.Entity;
import ky.Vector2D;

public class HealthBar extends Entity {

    private double maxHP;
    private Asset[] healthBarAssets;
    private Asset healthBarBorder;
    private int numBars=100;
    private Character character;
    private int lastBar;

    public HealthBar(Vector2D position, Character character) {
        super(position, 5);
        this.maxHP = character.maxHP;
        healthBarAssets = new Asset[100];
        healthBarBorder = new Asset("assets/misc/healthBar.png", new Vector2D(0, 0), 4);
        healthBarBorder.setVisible(true);
        add(healthBarBorder);
        for (int e=0; e<100; e++) {
            healthBarAssets[e] = new Asset("assets/misc/health.png", new Vector2D(-250+e*5, 0), 5);
            healthBarAssets[e].setVisible(true);
            add(healthBarAssets[e]);
        }
        this.character = character;
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        numBars = (int)(character.HP / (maxHP/100));
        if (numBars<0) {
            numBars = 0;
        }
        if (numBars == 100) {
            for (Asset healthBar : healthBarAssets) {
                healthBar.setVisible(true);
            }
        }
        for (int i=numBars; i<lastBar; i++) {
            healthBarAssets[i].setVisible(false);
        }
        lastBar = (int)(character.HP / (maxHP/100));
    }

}
