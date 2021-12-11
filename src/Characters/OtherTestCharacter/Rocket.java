import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class Rocket extends DamageEntity{

    Character testChar;
    double speed=2000;
    Asset rocketAsset;
    boolean homing = true;
    double angle = 0;

    public Rocket(Vector2D position, int player) {
        super(position, 64, 128, 3, player, 750, 0);
        testChar = ((player==1) ? Main.player2 : Main.player1);
        setBreaks(true);
        rocketAsset = new Asset("assets/characters/spaceship/rocket.png", new Vector2D(0, 0), 3);
        rocketAsset.setVisible(true);
        add(rocketAsset);
    }

    public void setActive() {
        angle = ((testChar.equals(Main.player2)) ? Main.player1.direction.getValue() : Main.player2.direction.getValue());
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
