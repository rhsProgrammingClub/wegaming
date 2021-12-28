import ky.Vector2D;

public class MainMenuScene extends Scene {

    Button characterinfo;
    Button startButton;
    Button inputSettingsButton;
    Button exitButton;

    public MainMenuScene(Main main) {
        super(main);
    }

    @Override
    public void initialize() {
        characterinfo = new Button(new Vector2D(Main.width / 2, Main.height * 0.3), main) {
            @Override
            protected void action(){
                main.setScene(6);
            }
        };
        characterinfo.setText("CHAR INFO");
        add(characterinfo);

        startButton = new Button(new Vector2D(Main.width / 2, Main.height * 0.5), main) {
            @Override
            protected void action() {
                main.setScene(2);
            }
        };
        startButton.setText("PLAY");
        add(startButton);

        inputSettingsButton = new Button(new Vector2D(Main.width / 2, Main.height * 0.7), main) {
            @Override
            protected void action() {
                main.setScene(5);
            }
        };
        inputSettingsButton.setText("INPUTS");
        add(inputSettingsButton);

        exitButton = new Button(new Vector2D(Main.width / 2, Main.height * 0.9), main) {
            @Override
            protected void action() {
                System.exit(0);
            }
        };
        exitButton.setText("EXIT");
        add(exitButton);
    }

}
