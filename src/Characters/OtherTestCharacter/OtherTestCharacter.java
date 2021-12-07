import java.util.ArrayList;

import ky.Vector2D;

public class OtherTestCharacter extends Character {

    double ultTime;
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
        ultTime-=deltaT;
    }

    @Override
    public void initialize() {
        jumpHeight = 800;
        gravity = 1200;
        ultimateCooldown = 10;
        rockets = new Rocket[10];
        for (int i=0; i<10; i++)
        {
            rockets[i] = new Rocket(new Vector2D(0, 0), getPlayer());
            add(rockets[i]);
        }
    }

    @Override
    protected void basicAttack() {
        
    }

    @Override
    protected void basicAbility() {
        
    }

    @Override
    protected void ultimate() {
        if (ultTime <= 0) {
            for (int i=0; i<5; i++) {
                if (curRocket >= rockets.length) {
                    curRocket = 0;
                }
                rockets[curRocket].setPos(new Vector2D(getX(), getY() - 250 + i*100));
                rockets[curRocket].setVisible(true);
                rockets[curRocket].canDamage = true;
                curRocket++;
            }
            ultTime = ultimateCooldown;
        }
    }
    
}
