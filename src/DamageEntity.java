import java.util.ArrayList;

import ky.CollisionEntity;
import ky.Vector2D;

public class DamageEntity extends CollisionEntity {

    private double damage;
    private double knockback;
    private int player;
    private boolean breaks;
    protected int knockbackDir = 0;
    public boolean canDamage = false;

    public DamageEntity(Vector2D position, int collisionBoxWidth, int collisionBoxHeight, int layer, int player,
            double damage) {
        super(position, collisionBoxWidth, collisionBoxHeight, layer);
        this.player = player;
        this.damage = damage;
        this.knockback = 0;
    }

    public DamageEntity(Vector2D position, int collisionBoxWidth, int collisionBoxHeight, int layer, int player,
            double damage, double knockback) {
        super(position, collisionBoxWidth, collisionBoxHeight, layer);
        this.player = player;
        this.damage = damage;
        this.knockback = knockback;
    }

    public boolean getBreaks() {
        return breaks;
    }

    public void setBreaks(boolean breaks) {
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
        if (isVisible() && collidingEntity.isVisible()) {
            if (collidingEntity instanceof Character) {
                if (((Character) collidingEntity).getPlayer() != player) {
                    if (canDamage) {
                        ((Character) collidingEntity).HP -= damage - damage *
                                (((Character) collidingEntity).getDefense());
                        canDamage = false;
                        if (knockback > 0) {
                            if (knockbackDir == 0) { // if no explicit direction
                                int dir = getVel().getX() > 0 ? 1 : -1;
                                ((Character) (collidingEntity)).outsideVelocity.add(dir * knockback, 0);
                            } else {
                                ((Character) (collidingEntity)).outsideVelocity.add(knockbackDir * knockback, 0);
                            }

                        }
                    }

                    if (breaks) {
                        setVisible(false);
                    }
                }
            }
        }
    }

}
