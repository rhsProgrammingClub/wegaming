import ky.CollisionEntity;
import ky.Scene;

public class GameScene extends Scene {

    static Ground ground;
    static testCharacter p1;

    public GameScene () {
        ground = new Ground(Main.width/2, Main.height*0.75, (int)(Main.width*0.6), (int)(Main.height*0.1));
        add(ground);
    }
}
