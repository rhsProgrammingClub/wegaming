
import java.util.ArrayList;

import ky.Text;
import ky.Vector2D;

public class InputSettingsScene extends Scene {

    // private Text inputInfoText;
    private Button[] p1InputButtons;
    private Button[] p2InputButtons;
    private int selectedButton=01;
    private Button exitButton;

    @Override
    public void initialize() {
        sceneIndex = 5;
        p1InputButtons = new Button[7];
        p2InputButtons = new Button[7];
    
        int[] xd = {0}; // cancer workaround btw
        for (int i=0; i<7; i++) {
            p1InputButtons[i] = new Button(new Vector2D(400, 100 + i*100)) {
                int temp = xd[0];
                @Override
                protected void action() {
                    selectedButton = temp;
                }
            };
            add(p1InputButtons[i]);
            xd[0]++;
        }

        for (int i=0; i<7; i++) {
            p2InputButtons[i] = new Button(new Vector2D(1100, 100 + i*100)) {
                int temp = xd[0];
                @Override
                protected void action() {
                    selectedButton = temp;
                }
            };
            add(p2InputButtons[i]);
            xd[0]++;
        }

        exitButton = new Button(new Vector2D(750, 600)) {
            @Override
            protected void action() {
                sceneIndex = 1;
            }
        };

        add(exitButton);


    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.size() > 0) {
            if (selectedButton != -1) {
                if (selectedButton <= 6) {
                    if (!PlayerInput.PLAYER_ONE_INPUT.setKey(selectedButton, keyCodes.get(0))) {
                        System.out.println("Already a binded key");
                    }
                } else {
                    if (!PlayerInput.PLAYER_TWO_INPUT.setKey(selectedButton, keyCodes.get(0))) {
                        System.out.println("Already a binded key");
                    }
                }
                selectedButton = -1;
            }
            
        }
    }

}
