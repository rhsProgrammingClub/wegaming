import ky.Scene;

public class GameScene extends Scene {

    static Ground ground;

    public GameScene (Character player1, Character player2) {
        ground = new Ground(Main.width/2, Main.height*0.75, (int)(Main.width*0.6), (int)(Main.height*0.1));
        add(ground);
    }
}
