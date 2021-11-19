import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import CharacterSelectScene.CharacterSelectScene;
import ky.KYscreen;

public class Main extends KYscreen {

    public static int width;
    public static int height;
    private Character player1;
    private Character player2;
    GameScene gameScene;
    CharacterSelectScene csScene;
    ArrayList <Character> characters;

    boolean cSelect = true;

    public Main() {
        super(width, height, false);
    }


    @Override
    public void start() {
        setDebugMode(true);
        setFullScreen(true);
        setCursorVisible(false);

        csScene = new CharacterSelectScene();
        setScene(csScene);
        // dispose();
        // gameScene = new GameScene(player1, player2);
        // setScene(gameScene);
        
    }

    @Override
    public void update() {

    }


    @Override
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }

    @Override
    public void keyReleased(int keyCode) {
        
    }

    @Override
    public void keyTyped(int keyCode) {
        
    }


    public static void main (String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        GraphicsEnvironment gEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gDevice = gEnvironment.getDefaultScreenDevice();
        width = gDevice.getDisplayMode().getWidth();
        height = gDevice.getDisplayMode().getHeight();
        new Main();
    }

}