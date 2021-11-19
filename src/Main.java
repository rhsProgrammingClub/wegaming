import java.awt.*;
import java.awt.event.KeyEvent;

import ky.KYscreen;

public class Main extends KYscreen {

    public static int width;
    public static int height;
    GameScene gameScene;

    public Main() {
        super(width, height, false);
    }


    @Override
    public void start() {
        setDebugMode(true);
        setFullScreen(true);
        setCursorVisible(false);
        gameScene = new GameScene();
        add(gameScene);
        
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