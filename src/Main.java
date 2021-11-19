import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ky.Asset;
import ky.CollisionEntity;
import ky.Entity;
import ky.KYscreen;

public class Main extends KYscreen {

    public static int width;
    public static int height;
    Scene currentScene;

    public Main() {
        super(width, height, false);
    }

	public void setScene (Scene scene) {
        assetLayers = scene.getAssetLayers();
        entityLayers = scene.getEntityLayers();
        collisionEntities = scene.getCollisionEntities();
	}

    @Override
    public void start() {
        setDebugMode(true);
        setFullScreen(true);
        setCursorVisible(false);
        currentScene = new GameScene();
        setScene(currentScene);
        
    }

    @Override
    public void update() {
        ArrayList <Integer> clonedKeyCodes = activeKeyCodes;
        currentScene.update(deltaT, clonedKeyCodes);
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