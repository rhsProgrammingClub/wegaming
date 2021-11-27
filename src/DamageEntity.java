import java.util.ArrayList;

import ky.CollisionEntity;
import ky.Vector2D;

public class DamageEntity extends CollisionEntity {

    private double damage;
    private double knockback;
    private int player;
    private boolean breaks;
    public boolean canDamage = false;

    public DamageEntity(Vector2D position, int collisionBoxWidth, int collisionBoxHeight, int layer, int player, double damage) {
        super(position, collisionBoxWidth, collisionBoxHeight, layer);
        this.player = player;
        this.damage=damage;
        this.knockback=0;
    }

    public DamageEntity(Vector2D position, int collisionBoxWidth, int collisionBoxHeight, int layer, int player, double damage, double knockback) {
        super(position, collisionBoxWidth, collisionBoxHeight, layer);
        this.player = player;
        this.damage = damage;
        this.knockback = knockback;
    }

    public boolean getBreaks () {
        return breaks;
    }

    public void setBreaks (boolean breaks) {
        this.breaks = breaks;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getKnockback() {
        return knockback;
    }

    public void setKnockback(double knockback) {
        this.knockback = knockback;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
    }

    @Override
    public void onCollision(CollisionEntity collidingEntity) {

    }
    
}
