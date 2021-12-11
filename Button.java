import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import ky.Asset;
import ky.Entity;
import ky.Text;
import ky.Vector2D;

public abstract class Button extends Entity {

    protected Text text;
    protected Asset buttonAsset;

    public Button(String buttonAsset, Vector2D position) {
        super(position, 4);
        try {
            ImageIO.read(new File(buttonAsset));
            this.buttonAsset = new Asset(buttonAsset, new Vector2D(0, 0), 5);
        } catch (IOException ex) {
            this.buttonAsset = new Asset("assets/misc/button.png", new Vector2D(0, 0), 5);
        }
        this.buttonAsset.setVisible(true);
        add(this.buttonAsset);
        setVisible(true);
    }

    protected void setText (String text) {
        this.text = new Text(
                text,
                new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 50),
                Color.white,
                new Vector2D(20, 0), 
                200, 
                70, 
                5);
        add(this.text);
        this.text.setVisible(true);
    }

    protected abstract void action ();

    private boolean hasClicked = false;

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (Main.mousePos.getX() >= getX() - buttonAsset.getWidth()/2 && 
            Main.mousePos.getX() <= getX() + buttonAsset.getWidth()/2) {
            if (Main.mousePos.getY() >= getY() - buttonAsset.getHeight()/2 && 
            Main.mousePos.getY() <= getY() + buttonAsset.getHeight()/2) {
                if (!hasClicked && Main.mousePressed) {
                    action();
                }
            }
        }
        hasClicked = Main.mousePressed;
    }

}