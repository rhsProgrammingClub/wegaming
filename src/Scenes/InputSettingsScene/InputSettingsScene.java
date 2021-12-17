import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import ky.Text;
import ky.Vector2D;

public class InputSettingsScene extends Scene {

    private Text[] p1InputInfo;
    private Text[] p2InputInfo;
    private Button[] p1InputButtons;
    private Button[] p2InputButtons;
    private int selectedButton=-1;
    private Button exitButton;

    @Override
    public void initialize() {
        sceneIndex = 5;
        p1InputButtons = new Button[7];
        p2InputButtons = new Button[7];
        p1InputInfo = new Text[7];
        p2InputInfo = new Text[7];

        int[] xd = { 0 }; // cancer workaround btw
        for (int i = 0; i < 7; i++) {
            p1InputButtons[i] = new Button(new Vector2D(400, 100 + i * 100)) {
                int temp = xd[0];

                @Override
                protected void action() {
                    selectedButton = temp;
                }
            };
            p1InputButtons[i].setText(PlayerInput.PLAYER_ONE_INPUT.getKey(xd[0]));
            add(p1InputButtons[i]);

            p1InputInfo[i] = new Text(
                    "L",
                    new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 46),
                    Color.black,
                    new Vector2D(200, 100 + i * 100),
                    200,
                    100,
                    5);

            p1InputInfo[i].setVisible(true);
            add(p1InputInfo[i]);

            xd[0]++;
        }

        for (int i = 0; i < 7; i++) {
            p2InputButtons[i] = new Button(new Vector2D(1100, 100 + i * 100)) {
                int temp = xd[0];

                @Override
                protected void action() {
                    selectedButton = temp;
                }
            };
            p2InputButtons[i].setText(PlayerInput.PLAYER_TWO_INPUT.getKey(xd[0]));
            add(p2InputButtons[i]);

            p2InputInfo[i] = new Text(
                    "L",
                    new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 46),
                    Color.black,
                    new Vector2D(1330, 100 + i * 100),
                    200,
                    100,
                    5);

            p2InputInfo[i].setVisible(true);
            add(p2InputInfo[i]);

            xd[0]++;
        }

        p1InputInfo[0].setText("LEFT");
        p2InputInfo[0].setText("LEFT");
        p1InputInfo[1].setText("RIGHT");
        p2InputInfo[1].setText("RIGHT");
        p1InputInfo[2].setText("UP");
        p2InputInfo[2].setText("UP");
        p1InputInfo[3].setText("DOWN");
        p2InputInfo[3].setText("DOWN");
        p1InputInfo[4].setText("ATTACK");
        p2InputInfo[4].setText("ATTACK");
        p1InputInfo[5].setText("ABILITY");
        p2InputInfo[5].setText("ABILITY");
        p1InputInfo[6].setText("ULT");
        p2InputInfo[6].setText("ULT");

        exitButton = new Button(new Vector2D(750, 600)) {
            @Override
            protected void action() {
                sceneIndex = 1;
            }
        };
        exitButton.setText("Exit");
        add(exitButton);

    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.size() > 0) {
            if (selectedButton != -1) {
                if (selectedButton <= 6) {
                    if (PlayerInput.PLAYER_ONE_INPUT.setKey(selectedButton, keyCodes.get(0))) {
                        p1InputButtons[selectedButton].setText(PlayerInput.PLAYER_ONE_INPUT.getKey(selectedButton));
                    } else {
                        System.out.println("Already a binded key");
                    }
                } else {
                    if (PlayerInput.PLAYER_TWO_INPUT.setKey(selectedButton, keyCodes.get(0))) {
                        p2InputButtons[selectedButton - 7].setText(PlayerInput.PLAYER_ONE_INPUT.getKey(selectedButton));
                    } else {
                        System.out.println("Already a binded key");
                    }
                }
                selectedButton = -1;
            }

        }
    }

}
