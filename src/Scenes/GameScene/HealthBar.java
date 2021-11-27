import java.util.ArrayList;

import ky.Asset;
import ky.Entity;
import ky.Vector2D;

public class HealthBar extends Entity {

    private double maxHP;
    private int player;
    private Asset[] healthBarAssets;
    private Asset healthBarBorder;
    private int numBars=100;
    private boolean flipped = false;

    public HealthBar(Vector2D position, double maxHP, boolean flipped) {
        super(position, 5);
        this.maxHP = maxHP;
        this.flipped = flipped;
        healthBarAssets = new Asset[100];
        healthBarBorder = new Asset("assets/misc/healthBar.png", new Vector2D(0, 0), 4);
        healthBarBorder.setVisible(true);
        add(healthBarBorder);
        for (int e=0; e<100; e++) {
            healthBarAssets[e] = new Asset("assets/misc/health.png", new Vector2D(-250+e*5, 0), 5);
            healthBarAssets[e].setVisible(true);
            add(healthBarAssets[e]);
        }
    }

    public void setHP (double HP) {
        numBars = (int)(HP / (maxHP/100));
        if (flipped) {
            for (int e=0; e<100; e++) {
                if (e <= numBars) {
                    healthBarAssets[e].setVisible(true);
                } else {
                    healthBarAssets[e].setVisible(false);
                }
            }
        } else {
            for (int e=99; e>=0; e--) {
                if (e>=numBars) {
                    healthBarAssets[e].setVisible(false);
                } else {
                    healthBarAssets[e].setVisible(true);
                }
            }
        }
    }

}
