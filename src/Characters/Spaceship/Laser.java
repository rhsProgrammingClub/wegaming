import ky.Vector2D;

public class Laser extends DamageEntity {

    public Laser(Main main,Vector2D position, int player) {
        super(main, position, 2000, 5, 2, player, 150);
        setBreaks(false);
    }

}
