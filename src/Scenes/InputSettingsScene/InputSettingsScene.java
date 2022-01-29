import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import ky.Text;
import ky.Vector2D;
import ky.Entity;
import ky.Asset;

public class InputSettingsScene extends Scene {

    private Text[] p1InputInfo = new Text[7];
    private Text[] p2InputInfo = new Text[7];
    private KeybindButton[] p1Buttons = new KeybindButton[7];
    private KeybindButton[] p2Buttons = new KeybindButton[7];
    private Button exitButton;
    private Font textFont;

    public KeybindButton currentButton;
    protected Entity selectedKeybind = new Entity(0, 0, 5);

    public InputSettingsScene(Main main) {
        super(main);
    }

    @Override
    public void initialize() {
        AtomicInteger[] p1Binds = PlayerInput.PLAYER_ONE_INPUT.orderedInputs();
        AtomicInteger[] p2Binds = PlayerInput.PLAYER_TWO_INPUT.orderedInputs();

        textFont = new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 46);

        // player 1 inputs
        for (int i = 0; i < 7; i++) {
            p1Buttons[i] = new KeybindButton(new Vector2D(400, 100 + i * 100), this, p1Binds[i], main);
            add(p1Buttons[i]);

            p1InputInfo[i] = new Text(
                    "L",
                    textFont,
                    Color.black,
                    new Vector2D(200, 100 + i * 100),
                    200,
                    100,
                    5);

            p1InputInfo[i].setVisible(true);
            add(p1InputInfo[i]);

        }

        // player two inputs
        for (int i = 0; i < 7; i++) {
            p2Buttons[i] = new KeybindButton(new Vector2D(1100, 100 + i * 100), this, p2Binds[i], main);
            add(p2Buttons[i]);

            p2InputInfo[i] = new Text(
                    "L",
                    textFont,
                    Color.black,
                    new Vector2D(1330, 100 + i * 100),
                    200,
                    100,
                    5);

            p2InputInfo[i].setVisible(true);
            add(p2InputInfo[i]);
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

        exitButton = new Button(new Vector2D(750, 600), main) {
            @Override
            protected void onClick() {
                setKeybindbutton(null);
                main.setScene(1);
            }
        };
        exitButton.setText("Exit");
        add(exitButton);

        Asset skAsset = new Asset("assets/misc/selected_keybind.png", new Vector2D(0, 0), 0);
        skAsset.setVisible(true);
        selectedKeybind.add(skAsset);
        add(selectedKeybind);
        selectedKeybind.setVisible(false);

    }

    public void setKeybindbutton(KeybindButton newButton) {
        currentButton = newButton;
        selectedKeybind.setVisible(newButton == null ? false : true);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.size() > 0 && currentButton != null) {
            if (keyCodes.get(0) == KeyEvent.VK_ESCAPE) {
                setKeybindbutton(null);
            } else {
                currentButton.setKey(keyCodes.get(0));
                setKeybindbutton(null);
            }
        }
    }

}
