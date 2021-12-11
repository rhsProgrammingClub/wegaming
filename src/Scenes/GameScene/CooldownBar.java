import java.util.ArrayList;

import ky.Asset;
import ky.Entity;
import ky.Vector2D;

public class CooldownBar extends Entity {

    private final int totalCovers = 12;

    private double maxCooldown;
    private double curCooldown;    
    private Asset[] cooldownCovers;
    private Asset icon;
    private int numCovered=0;
    private Character character;
    private boolean isUltCooldown;
    // private Character.Status lastStatus = Character.Status.IDLE;

    public CooldownBar(Vector2D position, Character character, boolean isUlt, String icon) {
        super(position, 5);
        this.character = character;
        this.isUltCooldown = isUlt;
        if (isUlt) {
            this.maxCooldown = character.ultimateCooldown;
        } else {
            this.maxCooldown = character.abilityCooldown;
        }
        this.curCooldown = this.maxCooldown;
        // this.icon = new Asset(icon, new Vector2D(0, 0), 5);
        // this.icon.setVisible(true);
        // add(this.icon);
        setVisible(true);
        cooldownCovers = new Asset[totalCovers];
        for (int i=0; i<totalCovers; i++) {
            cooldownCovers[i] = new Asset("assets/misc/cooldown_bar.png", new Vector2D(0, 0), 5);
            cooldownCovers[i].rotate(i * (360/totalCovers) + (360/totalCovers/2));
            add(cooldownCovers[i]);
        }
        countDown();
    }

    public void countDown () {
        System.out.println("did something haha");
        curCooldown = maxCooldown;
        numCovered = totalCovers;
        for (Asset asset : cooldownCovers) {
            asset.setVisible(true);
        }
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (isUltCooldown) {
            curCooldown = character.curUltCooldown;
            if (character.curUltCooldown > 0 && curCooldown <= 0) {
                countDown();
            }
        } else {
            curCooldown = character.curAbilityCooldown;
            if (character.curAbilityCooldown > 0 && curCooldown <= 0) {
                countDown();
            }
        }
        if (curCooldown >= 0) {
            // curCooldown -= deltaT;
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
