import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import ky.KYscreen;

public class Main extends KYscreen {

    public static int width = 1500;
    public static int height = 800;
    public static Character player1;
    public static Character player2;
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
        setResizable(false);
        setCursorVisible(false);

        scenes = new Scene[4];
        scenes[0] = new CharacterSelectScene();
        scenes[1] = new CharacterSelectScene();
        scenes[2] = new GameScene();
        scenes[3] = new EndScene();

        characters = new Character[4];  // different characters
        characters[0] = new TestCharacter();
        characters[1] = new TestCharacter();
        characters[2] = new TestCharacter();
        characters[3] = new TestCharacter();
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
        new Main();
    }

}