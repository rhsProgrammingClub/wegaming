public class GameScene extends Scene {

    static Ground ground;
    static Character player1;
    static Character player2;

    public GameScene () {
    }

    @Override
    public int changeScene() {
        return 2;
    }

    @Override
    public void initialize() {
        ground = new Ground(Main.width/2, Main.height*0.75, (int)(Main.width*0.6), (int)(Main.height*0.1));
        add(ground);
        player1 = Main.player1;
        player2 = Main.player2;
        player1.setPlayer(1);
        player2.setPlayer(2);
        player1.setVisible(true);
        player2.setVisible(true);
        add(player1);
        add(player2);
        
    }
}
