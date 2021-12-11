import ky.Vector2D;

public class Laser extends DamageEntity {

    public Laser(Vector2D position, int width, int height, int player) {
        super(position, width, height, 5, player, 100);
        setBreaks(false);
    }

  
}
