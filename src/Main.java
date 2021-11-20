import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import ky.KYscreen;

public class Main extends KYscreen {

    public static int width;
    public static int height;
    public static Character player1;
    public static Character player2;
    GameScene gameScene;
    CharacterSelectScene csScene;
    public static Character[] characters;
    public static int sceneIndex = 1;
    public static Scene[] scenes;

    boolean cSelect = true;
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

        scenes = new Scene[3];
        scenes[0] = new CharacterSelectScene();
        scenes[1] = new CharacterSelectScene();
        scenes[2] = new GameScene();

        characters = new Character[4];  // different characters
        characters[0] = new testCharacter();
        characters[1] = new testCharacter();
        characters[2] = new testCharacter();
        characters[3] = new testCharacter();
        currentScene = scenes[sceneIndex];
        currentScene.initialize();
        setScene(currentScene);
    }

    @Override
    public void update() {
        ArrayList <Integer> clonedKeyCodes = activeKeyCodes;
        currentScene.update(deltaT, clonedKeyCodes);
        if (currentScene.changeScene() != sceneIndex) {
            System.out.println("Changed scenes.");
            sceneIndex = currentScene.changeScene();
            currentScene = scenes[sceneIndex];
            currentScene.initialize();
            setScene(currentScene);
        }
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