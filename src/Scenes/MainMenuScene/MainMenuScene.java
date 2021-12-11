import ky.Vector2D;

public class MainMenuScene extends Scene {

    Button startButton;

    @Override
    public void initialize() {
        sceneIndex = 1;
        startButton = new Button("asses/misc/button.png", 
            new Vector2D(750, 300)) {
            @Override
            protected void action() {
                sceneIndex=2;
            }
        };
        startButton.setText("PLAY");
        add(startButton);
    }

    
  
}
