import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class Laser extends DamageEntity {

    Asset laserAsset;

    public Laser(Main main, Vector2D position, int player) {
        super(main, position, 200, 5, 2, player, 150);
        laserAsset = new Asset("assets/characters/spaceship/laser.png", new Vector2D(0, 0), 0);
        laserAsset.setVisible(true);
        add(laserAsset);
        setBreaks(true);
    }

    public void setActive() {
        Character charObj =getPlayer() == 1 ? main.player1 : main.player2;
        int dir = charObj.direction.getValue();
        setPos(new Vector2D(charObj.getX() + 220 * dir, charObj.getY() + 15));
        setVisible(true);
        canDamage = true;
        setVel(new Vector2D(Main.width * 4 * dir, 0));
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        super.update(deltaT, keyCodes);
        if (getVel().getX() != 0) {
            if (getPos().getX() - getCollisionBox().width / 2 > Main.width ||
                    getPos().getX() < -getCollisionBox().width/2) {
                setVel(new Vector2D(0, 0));
            }
        }
    }
}
