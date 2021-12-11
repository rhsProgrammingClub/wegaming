import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class OtherTestCharacter extends Character {

    double attackTime;
    Rocket[] rockets;
    int curRocket=0;

    public OtherTestCharacter() {
        super(new Vector2D(0, 0), 150, 200, 1000, 3);
    }

    public OtherTestCharacter(Vector2D position){
        super(position, 150, 200, 1000, 3);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        super.update(deltaT, keyCodes);
        if (status == Status.ATTACKING) {
            attackTime-=deltaT;
            if (attackTime <= 0) {
                status  = Status.IDLE;
            }
        }
    }

    @Override
    public void initialize() {
        jumpHeight = 600;
        gravity = 800;
        ultimateCooldown = 1;
        abilityCooldown = 5;
        curUltCooldown = ultimateCooldown;
        curAbilityCooldown = curAbilityCooldown;

        characterAsset = new Asset(new String[] {
                                   "assets/Characters/otheTest/Spaceship_normal.png",
                                   "assets/Characters/otheTest/Spaceship_enraged.png"
                                }, new Vector2D(0, 0), 3);

        characterAsset.setVisible(true);
        characterAsset.rescale(2);
        add(characterAsset);

        rockets = new Rocket[10];
        for (int i=0; i<10; i++)
        {
            rockets[i] = new Rocket(new Vector2D(0, 0), getPlayer());
            add(rockets[i]);
        }
    }

    @Override
    protected void basicAttack() {
        if (status == Status.IDLE) {
            status = Status.ATTACKING;
            attackTime = 2;
            // do something
        }
    }

    @Override
    protected void basicAbility() {
        if (curRocket >= rockets.length) {
            curRocket = 0;
        }
        rockets[curRocket].setPos(getPos());
        rockets[curRocket].homing = false;
        rockets[curRocket].setActive();
        curRocket++;
    }

    @Override
    protected void ultimate() {
        for (int i=0; i<5; i++) {
            if (curRocket >= rockets.length) {
                curRocket = 0;
            }
            rockets[curRocket].setPos(new Vector2D(getX(), getY() - 250 + i*100));
            rockets[curRocket].setVisible(true);
            rockets[curRocket].canDamage = true;
            rockets[curRocket].homing = true;
            curRocket++;
        }
    }
    
}
