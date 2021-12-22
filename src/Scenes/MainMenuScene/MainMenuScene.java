import ky.Vector2D;

public class MainMenuScene extends Scene {

    Button startButton;
    Button inputSettingsButton;
    Button exitButton;

    public MainMenuScene(Main main) {
        super(main);
    }

    @Override
    public void initialize() {
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
