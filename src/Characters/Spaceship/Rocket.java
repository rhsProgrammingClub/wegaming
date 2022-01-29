import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;
//import java.awt.image.BufferedImage;

public class Rocket extends DamageEntity {

    Character testChar;
    double speed = 2000;
    Asset rocketAsset;
    boolean homing = true;
    double angle = 0;

    private Main main;

    public Rocket(String image, Vector2D position, int player, Main main, double dmg) {
        super(position, 32, 64, 3, player, dmg, 1500);
        this.main = main;
        testChar = ((player == 1) ? main.player2 : main.player1);
        setBreaks(true);
        rocketAsset = new Asset(image, new Vector2D(0, 0), 64, 64, 3);
        rocketAsset.setVisible(true);
        add(rocketAsset);
    }

    public void setActive() {
        angle = ((testChar.equals(main.player2)) ? main.player1.direction.getValue()
                : main.player2.direction.getValue());
        angle = ((angle == 1) ? 0 : -Math.PI);
        setVisible(true);
        canDamage = true;
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (testChar != null && this.isVisible()) {
            if (homing) {
                angle = Math.atan2(testChar.getY() - getY(),
                        testChar.getX() - getX());

            }
            setVel(new Vector2D(Math.cos(angle) * speed, Math.sin(angle) * speed));
            setRotation(angle * 180 / Math.PI + 90);

        } else {
            setVel(new Vector2D(0, 0));
        }
    }

    @Override
    public void onCollision(CollisionEntity collidingEntity) {
        super.onCollision(collidingEntity);
    }
}
