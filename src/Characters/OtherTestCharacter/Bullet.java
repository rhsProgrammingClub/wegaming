import ky.Vector2D;

public class Bullet extends DamageEntity {

    public Bullet(Vector2D position, int width, int height, int player) {
        super(position, width, height, 5, player, 500);
    }



  
}
