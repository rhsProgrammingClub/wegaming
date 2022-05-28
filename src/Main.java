import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.event.MouseInputListener;

import ky.AudioPlayer;
import ky.KYscreen;
import ky.Vector2D;

public class Main extends KYscreen implements MouseInputListener {

    public static int width = 1500;
    public static int height = 800;
    public Character player1;
    public Character player2;
    public Character[][] characters;
    public int sceneIndex = 0;
    public Scene[] scenes;
    public AudioPlayer testAudio;
    public AudioPlayer backgroundMusic;
    public Vector2D mousePos;
    public boolean mousePressed = false;

    boolean cSelect = true;
    Scene currentScene;

    public Main() {
        super(width, height, false);
    }

    @Override
    public void start() {
        mousePos = new Vector2D(0, 0);
        setTitle("Packing Some Smack!");
        // setDebugMode(true);
        addMouseListener(this);
        addMouseMotionListener(this);
        // backgroundMusic = new AudioPlayer("assets/SFX/background_music_test.wav");
        // backgroundMusic.setVolume(-8);
        // backgroundMusic.setLoop(true);
        // backgroundMusic.play();

        scenes = new Scene[8];
        scenes[0] = new StartScene(this);
        scenes[1] = new MainMenuScene(this);
        scenes[2] = new CharacterSelectScene(this);
        scenes[3] = new GameScene(this);
        scenes[4] = new EndScene(this);
        scenes[5] = new InputSettingsScene(this);
        scenes[6] = new CharacterInfoScene(this);
        scenes[7] = new MapSelectScene(this);

        resetCharacters();

        currentScene = scenes[sceneIndex];
        setScene(currentScene);
    }

    @Override
    public void update() {
        if (currentScene == null)
            return; // we should probably throw and error
        ArrayList<Integer> clonedKeyCodes = activeKeyCodes;
        currentScene.update(deltaT, clonedKeyCodes);
    }

    public void resetCharacters() {
        characters = new Character[5][2]; // different characters
        characters[0][0] = new Spaceship(this);
        characters[1][0] = new StabbyRobot(this);
        characters[2][0] = new BoxRobot(this);
        characters[3][0] = new Assassin(this);
        characters[0][1] = new Spaceship(this);
        characters[1][1] = new StabbyRobot(this);
        characters[2][1] = new BoxRobot(this);
        characters[3][1] = new Assassin(this);
    }

    public void setScene(Scene scene) {
        // if(!scene.hasInitialized) {

        scene.delete(); // just reload that stuff lmfao
        scene.initialize(); // re-initialize it xD

        // scene.hasInitialized = true;
        // }

        assetLayers = scene.getAssetLayers();
        entityLayers = scene.getEntityLayers();
        collisionEntities = scene.getCollisionEntities();
        currentScene = scene;

        if (currentScene instanceof GameScene || currentScene instanceof CharacterSelectScene) {
            setCursorVisible(false);
        } else {
            setCursorVisible(true);
        }
    }

    public void setScene(int index) {
        setScene(scenes[index]);
    }

    @Override
    public void keyPressed(int keyCode) {
        currentScene.keyPressed(keyCode);
    }

    @Override
    public void keyReleased(int keyCode) {
        currentScene.keyReleased(keyCode);
    }

    @Override
    public void keyTyped(int keyCode) {
        currentScene.keyTyped(keyCode);
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

    public Character getOtherPlayer(int player) {
        return player == 1 ? player1 : player2;
    }

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1.0");
        new Main();
    }

}