import ky.CollisionEntity;

public class GameScene extends Scene {

    static Ground ground;

    public GameScene () {
        ground = new Ground(Main.width/2, Main.height*0.75, (int)(Main.width*0.6), (int)(Main.height*0.1));
        add(ground);
    }

    @Override
    public int changeScene() {
        return 2;
    }
}
