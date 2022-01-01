import java.util.ArrayList;

import ky.Asset;
import ky.Entity;
import ky.Vector2D;

public class HealthBar extends Entity {

    private final int totalBars = 250;

    private double maxHP;
    private Asset[] healthBarAssets;
    private Asset healthBarBorder;
    private int numBars = totalBars;
    private Character character;
    private int lastBar;

    public HealthBar(Vector2D position, Character character) {
        super(position, 5);
        this.maxHP = character.maxHP;
        healthBarAssets = new Asset[totalBars];
        healthBarBorder = new Asset("assets/misc/healthborder.png", new Vector2D(0, 0), 4);
        healthBarBorder.setVisible(true);
        add(healthBarBorder);
        for (int i = 0; i < totalBars; i++) {
            healthBarAssets[i] = new Asset("assets/misc/health.png", new Vector2D(-250 + i * (healthBarBorder.getWidth()/totalBars), 0), 5);
            healthBarAssets[i].setVisible(true);
            add(healthBarAssets[i]);
        }
        this.character = character;
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        numBars = (int) Math.round((character.HP / (maxHP / totalBars)));
        if (numBars < 0) {
            numBars = 0;
        }
        if (numBars == totalBars) {
            for (Asset healthBar : healthBarAssets) {
                healthBar.setVisible(true);
            }
        }
        if (numBars < lastBar) {
            for (int i = numBars; i < lastBar; i++) {
                healthBarAssets[i].setVisible(false);
            }
        }
        else if (numBars > lastBar) {
            for (int i = lastBar; i < numBars; i++) {
                healthBarAssets[i].setVisible(true);
            }
        }
        lastBar = numBars;
    }

}
