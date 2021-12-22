import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;
import java.awt.Font;
import ky.Vector2D;

public class KeybindButton extends Button {

    private AtomicInteger pointer;
    private InputSettingsScene inScene;

    public KeybindButton(Vector2D position, InputSettingsScene inScene, AtomicInteger pointer, Main main) {
        super(position, main);
        this.pointer = pointer;
        this.inScene = inScene;

        setText(KeyEvent.getKeyText(this.pointer.get()));
        text.setFont(new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 34));
    }

    @Override
    protected void action() {
        inScene.setKeybindbutton(this); // set the keybind button to us

        // positioning lol
        inScene.selectedKeybind.setPos(getPos());
        inScene.selectedKeybind.addPos(150, 0);
    }

    public void setKey(int keyEvent) {
        if (PlayerInput.setKey(pointer, keyEvent)) {
            setText(KeyEvent.getKeyText(pointer.get()));
        } else {
            System.out.println(KeyEvent.getKeyText(keyEvent) + " is already binded.");
        }
    }
}
