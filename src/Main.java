import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import ky.AudioPlayer;
import ky.KYscreen;
import ky.Vector2D;

public class Main extends KYscreen implements MouseInputListener {

    public static int width = 1500;
    public static int height = 800;
    public static Character player1;
    public static Character player2;
    public static Character[][] characters;
    public static int sceneIndex = 0;
    public static Scene[] scenes;
    public static AudioPlayer testAudio;
    public static AudioPlayer backgroundMusic;
    public static Vector2D mousePos = new Vector2D(0,0);
    public static boolean mousePressed=false;

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
        addMouseListener(this);
        addMouseMotionListener(this);
        // testAudio = new AudioPlayer("assets/SFX/idk.wav");
        // testAudio.play();
        // backgroundMusic = new AudioPlayer("assets/SFX/background_music_test.wav");
        // backgroundMusic.setVolume(-8);
        // backgroundMusic.setLoop(true);
        // backgroundMusic.play();

        scenes = new Scene[6];
        scenes[0] = new StartScene();
        scenes[1] = new MainMenuScene();
        scenes[2] = new CharacterSelectScene();
        scenes[3] = new GameScene();
        scenes[4] = new EndScene();
        scenes[5] = new InputSettingsScene();

        resetCharacters();

        currentScene = scenes[sceneIndex];
        currentScene.initialize();
        setScene(currentScene);
    }

    @Override
    public void update() {
        ArrayList <Integer> clonedKeyCodes = activeKeyCodes;
        currentScene.update(deltaT, clonedKeyCodes);
        if (currentScene.changeScene() != sceneIndex) {
            System.out.println("Changed scenes: " + sceneIndex + " to " + currentScene.changeScene());
            scenes[sceneIndex].delete();
            sceneIndex = currentScene.changeScene();
            currentScene = scenes[sceneIndex];
            currentScene.onSceneLoad();
            if (currentScene instanceof GameScene || currentScene instanceof CharacterSelectScene) {
                setCursorVisible(false);
            } else {
                setCursorVisible(true);
            }
            currentScene.initialize();
            setScene(currentScene);
        }
    }

    public static void resetCharacters () {
        characters = new Character[4][2];  // different characters
        characters[0][0] = new OtherTestCharacter();
        characters[1][0] = new TestCharacter();
        characters[2][0] = new TestCharacter();
        characters[3][0] = new TestCharacter();
        characters[0][1] = new OtherTestCharacter();
        characters[1][1] = new TestCharacter();
        characters[2][1] = new TestCharacter();
        characters[3][1] = new TestCharacter();
    }


    @Override
    public void keyPressed(int keyCode) {
        // if (keyCode == KeyEvent.VK_ESCAPE) { 
        //     System.exit(0);
        // }
    }

    @Override
    public void keyReleased(int keyCode) {
        
    }

    @Override
    public void keyTyped(int keyCode) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mousePressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        mousePos = new Vector2D(e.getX(), e.getY());
        mousePressed = false;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        mousePressed = false;
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousePos = new Vector2D(e.getX(), e.getY());
        mousePressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousePos = new Vector2D(e.getX(), e.getY());
        mousePressed = false;
    }

    public static void main (String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        new Main();
    }

}