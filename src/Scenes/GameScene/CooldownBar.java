import java.util.ArrayList;

import ky.Asset;
import ky.Entity;
import ky.Vector2D;

public class CooldownBar extends Entity {

    private final int totalCovers = 12;

    private double maxCooldown;
    private double curCooldown;    
    private Asset[] cooldownCovers;
    private int numCovered=0;
    private Character character;
    private boolean isUltCooldown;

    public CooldownBar(Vector2D position, Character character, boolean isUlt, Asset icon) {
        super(position, 5);
        this.character = character;
        this.isUltCooldown = isUlt;
        if (isUlt) {
            this.maxCooldown = character.ultimateCooldown;
        } else {
            this.maxCooldown = character.abilityCooldown;
        }
        this.curCooldown = this.maxCooldown;
        if (icon != null) {
            icon.setVisible(true);
            add(icon);
        }
        setVisible(true);
        cooldownCovers = new Asset[totalCovers];
        for (int i=0; i<totalCovers; i++) {
            cooldownCovers[i] = new Asset("assets/misc/cooldown_bar.png", new Vector2D(0, 0), 5);
            cooldownCovers[i].rotate(i * (360/totalCovers) + (360/totalCovers/2));
            add(cooldownCovers[i]);
        }
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {

        if (isUltCooldown) curCooldown = character.curUltCooldown;
        else curCooldown = character.curAbilityCooldown;

        if (curCooldown >= 0) {
            if (numCovered != (int)(curCooldown / (maxCooldown/totalCovers))) {
                numCovered = (int)(curCooldown / (maxCooldown/totalCovers));
                for (int i=0; i<totalCovers; i++) {
                    if (i <= numCovered) {
                        cooldownCovers[i].setVisible(true);
                    } else {
                        cooldownCovers[i].setVisible(false);
                    }
                }
            }
        } else {
            cooldownCovers[0].setVisible(false);
            curCooldown = 0;
        }
    }

}
