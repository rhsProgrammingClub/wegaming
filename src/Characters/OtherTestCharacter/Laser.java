import ky.Vector2D;

public class Laser extends DamageEntity {

    public Laser(Vector2D position, int player) {
        super(position, 2000, 10, 5, player, 150);
        setBreaks(false);
    }
    
}
