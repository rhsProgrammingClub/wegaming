import ky.Vector2D;

public class MainMenuScene extends Scene {

    Button startButton;
    Button inputSettingsButton;

    public MainMenuScene(Main main) {
        super(main);
    }

    @Override
    public void initialize() {
        startButton = new Button(new Vector2D(750, 300), main) {
            @Override
            protected void action() {
                main.setScene(2);
            }
        };
        startButton.setText("PLAY");
        add(startButton);

        inputSettingsButton = new Button(new Vector2D(750, 450), main) {
            @Override
            protected void action() {
                main.setScene(5);
            }
        };
        inputSettingsButton.setText("INPUTS");
        add(inputSettingsButton);
    }

    
  
}
