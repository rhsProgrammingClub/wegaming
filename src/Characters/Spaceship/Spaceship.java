import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;
import ky.AudioPlayer;

public class Spaceship extends Character {

    double attackTime;
    double enrageTime = 1;
    Rocket[] rockets;
    Laser laser;
    int curRocket = 0;
    AudioPlayer zapSound;
    AudioPlayer rocketwhoosh;

    public Spaceship(Main main) {
        super(new Vector2D(0, 0), 75, 100, 1200, main);
    }

    public Spaceship(Vector2D position, Main main) {
        super(position, 75, 100, 1200, main);
    }

    @Override
    public void updateCharacter(double deltaT, ArrayList<Integer> keyCodes) {
        if (status == Status.ATTACKING) {
            attackTime -= deltaT;
            if (attackTime <= 0) {
                laser.canDamage = false;
                laser.setVisible(false);
                status = Status.IDLE;
            }
        }

        if (characterAsset.getImageIndex() == 1) {
            enrageTime -= deltaT;
            if (enrageTime <= 0) {
                enrageTime = 1;
                characterAsset.setImageIndex(0);
            }
        }
    }

    @Override
    public void initialize() {
        setStats("characterStats/spaceship.stats");

        /*characterAsset = new Asset(new String[] {
                "assets/characters/spaceship/spaceship_normal.png",
                "assets/characters/spaceship/spaceship_enraged.png"
        }, new Vector2D(0, 0), 3);*/

        if(getPlayer() == 1){
            characterAsset = new Asset(new String[] {
                "assets/characters/spaceship/spaceship_p1.png",
                "assets/characters/spaceship/spaceship_enraged_p1.png"
            }, new Vector2D(0, 0), 64, 64, 3);
        }else{
            characterAsset = new Asset(new String[] {
                "assets/characters/spaceship/spaceship_p2.png",
                "assets/characters/spaceship/spaceship_enraged_p2.png"
            }, new Vector2D(0, 0), 64, 64, 3);
        }

        characterAsset.setVisible(true);
        characterAsset.rescale(2);
        add(characterAsset);

        rockets = new Rocket[10];
        for (int i = 0; i < 10; i++) {
            rockets[i] = new Rocket(main, "assets/characters/spaceship/rocket.png", new Vector2D(0, 0), getPlayer(), 500);
            add(rockets[i]);
        }
        laser = new Laser(main, new Vector2D(0, 0), getPlayer());
        add(laser);

        zapSound = new AudioPlayer("assets/SFX/zap.wav");
        zapSound.setVolume(-30);

        rocketwhoosh = new AudioPlayer("assets/SFX/rocket.wav");

        abilityIcon = new Asset("assets/characters/spaceship/ability_icon.png", new Vector2D(0, 0), 4);
        ultIcon = new Asset("assets/characters/spaceship/ult_icon.png", new Vector2D(0, 0), 4);
    }

    @Override
    protected void basicAttack() {
        if (status == Status.IDLE) {
            status = Status.ATTACKING;
            attackTime = 0.3;
            laser.setPos(new Vector2D(getX() + 1040 * direction.getValue(), getY() + 15));
            laser.setVisible(true);
            laser.canDamage = true;

            zapSound.reset();
            zapSound.play();
        }
    }

    @Override
    protected void basicAbility() {
        rocketwhoosh.reset();
        rocketwhoosh.play();
        if (curRocket >= rockets.length) {
            curRocket = 0;
        }
        rockets[curRocket].setDamage(500);
        rockets[curRocket].setPos(getPos());
        rockets[curRocket].homing = false;
        rockets[curRocket].setActive();
        curRocket++;
    }

    @Override
    protected void ultimate() {
        rocketwhoosh.reset();
        rocketwhoosh.play();
        characterAsset.setImageIndex(1);
        for (int i = 0; i < 4; i++) {
            if (curRocket >= rockets.length) {
                curRocket = 0;
            }
            rockets[curRocket].setDamage(250);
            rockets[curRocket].setPos(new Vector2D(getX(), getY() - 250 + i * 100));
            rockets[curRocket].setVisible(true);
            rockets[curRocket].canDamage = true;
            rockets[curRocket].homing = true;
            curRocket++;
        }
    }

}
