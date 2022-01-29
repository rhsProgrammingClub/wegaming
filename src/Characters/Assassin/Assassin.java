import java.util.ArrayList;

import ky.Asset;
import ky.AudioPlayer;
import ky.Vector2D;
public class Assassin extends Character {

    private ASword asword;
    AudioPlayer swordswing;
    AudioPlayer healthsfx;
    AudioPlayer teleport;
    Direction lastDirection;
    private Asset healthup;
    boolean canReduceCD;

    double ultuptime;
    double offset = 0;
    double temp = 1000;

    public Assassin(Main main){
        super(new Vector2D(0, 0), 75, 100, 1000, main);
    }

    public Assassin(Vector2D position, Main main){
        super(position, 75, 100, 1000, main);
    }

    @Override
    public void initialize(){
        setDefense(0);
        jumpHeight = 800;
        gravity = 1300;
        ultimateCooldown = 15;
        abilityCooldown = 6.9;
        speed = 159000;

        curAbilityCooldown = abilityCooldown;
        curUltCooldown = ultimateCooldown;

        asword = new ASword(main, new Vector2D(0, 0), getPlayer());
        asword.setVisible(true);
        add(asword);

        

        //rocket = new Rocket ("assets/characters/assassin/freezerocket.png", new Vector2D(0, 0), getPlayer(), main, 200, 700);
        //add(rocket);
        //was originally gonna make a knockback rocket as ability but changed it to heal instead

        if(getPlayer() == 1){
            characterAsset = new Asset(new String[]{
                "assets/characters/assassin/assassinp1.png",
                "assets/characters/assassin/assassinragep2.png"
            }, new Vector2D(0, 0), 128, 128, 3);
        }else{
            characterAsset = new Asset(new String[]{
                "assets/characters/assassin/assassinp2.png",
                "assets/characters/assassin/assassinragep2.png"
            }, new Vector2D(0, 0), 128, 128, 3);
        }
        
        characterAsset.setVisible(true);
        add(characterAsset);
        healthup = new Asset("assets/characters/assassin/healthup.png", new Vector2D(0, 0), 3);
        healthup.setVisible(false);
        add(healthup);
        characterAsset.flipHorizontal();
        lastDirection = Direction.RIGHT;

        swordswing = new AudioPlayer("assets/SFX/knife_swing.wav");
        healthsfx = new AudioPlayer("assets/SFX/healthup.wav");
        teleport = new AudioPlayer("assets/SFX/teleport.wav");

        abilityIcon = new Asset("assets/characters/assassin/healthup.png", new Vector2D(0, 0), 130, 130, 3);
        ultIcon = new Asset("assets/characters/assassin/assassindash.png", new Vector2D(0, 0), 130, 130, 3);

    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes){
        super.update(deltaT, keyCodes);

        if (!asword.canDamage && canReduceCD) {
            curUltCooldown-=1.5;
            if (curUltCooldown < 0)
                curUltCooldown = 0;
            canReduceCD = false;
        }

        if(direction != lastDirection){
            asword.assassinsword.flipHorizontal();
            lastDirection = direction;
        }

        if(status == Status.ATTACKING){
            asword.setPos(new Vector2D(offset * direction.getValue() + temp * deltaT * direction.getValue(), 0));
            asword.addPos(this.getPos());
            offset += temp * deltaT;
            if(offset >= 120){
                offset = 120;
                temp = -500;
            }

            if(offset <= 0){
                offset = 0;
                temp = 500;
                status = Status.IDLE;
                asword.canDamage = false;
                canReduceCD = false;
            }
        }else{
            asword.setPos(this.getPos());
        }

        if(ultuptime > 0){
            ultuptime -= deltaT;
        }else if(asword.getDamage() >= 350){
            asword.setDamage(250);
            characterAsset.setImageIndex(0);
        }
    }

    @Override
    protected void basicAttack(){
        if(status == Status.IDLE){
            swordswing.reset();
            swordswing.play();
            status = Status.ATTACKING;
            canReduceCD = true;
            asword.canDamage = true;
        }
    }

    @Override
    protected void basicAbility(){
        healthsfx.reset();
        healthsfx.play();
        HP+=300;
        if (HP > maxHP) {
            HP = maxHP;
        }
        
    }

    @Override
    protected void ultimate(){
        teleport.reset();
        teleport.play();
        ultuptime = 4;
        asword.setDamage(400);
        characterAsset.setImageIndex(1);
        if(lastDirection == Direction.RIGHT){
            setPos(getX()+700, getY());
        }else if(lastDirection == Direction.LEFT){
            setPos(getX()-700, getY());
        }
    }

}
