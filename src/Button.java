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
    private Main main;

    public Button (Vector2D position, Main main) {
        super(position, 4);
        this.main = main;
        this.buttonAsset = new Asset(
                new String[]{"assets/misc/button.png", 
                "assets/misc/button_hovered.png"}, 
                new Vector2D(0, 0), 5);

        this.buttonAsset.setVisible(true);
        add(this.buttonAsset);
        setVisible(true);
    }

    public Button(String buttonAsset, String hoveredButtonAsset, Vector2D position) {
        super(position, 4);
        try {
            ImageIO.read(new File(buttonAsset));
            ImageIO.read(new File(hoveredButtonAsset));
            this.buttonAsset = new Asset(
                    new String[]{buttonAsset, hoveredButtonAsset}, 
                    new Vector2D(0, 0), 5);
        } catch (IOException ex) {
            this.buttonAsset = new Asset(
                    new String[]{"assets/misc/button.png", 
                    "assets/misc/button_hovered.png"}, 
                    new Vector2D(0, 0), 5);
        }
        this.buttonAsset.setVisible(true);
        add(this.buttonAsset);
        setVisible(true);
    }

    protected void setText (char newText) {
        if (this.text == null) {
            this.text = new Text(
                    String.valueOf(newText),
                    new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 50),
                    Color.white,
                    new Vector2D(20, 0), 
                    200, 
                    70, 
                    5);
            add(this.text);
            this.text.setVisible(true);
        } else {
            this.text.setText(String.valueOf(newText));
            System.out.println("Char: changed to " + newText);
        }
    }

    protected void setText (String newText) {
        if (this.text == null) {
            this.text = new Text(
                    newText,
                    new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 50),
                    Color.white,
                    new Vector2D(20, 0), 
                    200, 
                    70, 
                    5);
            add(this.text);
            this.text.setVisible(true);
        } else {
            this.text.setText(newText);
            System.out.println("String: changed to " + (newText));
        }
    }

    protected abstract void action ();

    private boolean hasClicked = false;

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (main.mousePos.getX() >= getX() - buttonAsset.getWidth()/2 && 
                main.mousePos.getX() <= getX() + buttonAsset.getWidth()/2) {
            if (main.mousePos.getY() >= getY() - buttonAsset.getHeight()/2 && 
            main.mousePos.getY() <= getY() + buttonAsset.getHeight()/2) {
                if (!hasClicked && main.mousePressed) {
                    action();
                }
                buttonAsset.setImageIndex(1);
            } else {
                buttonAsset.setImageIndex(0);
            }
        } else {
            buttonAsset.setImageIndex(0);
        }
        hasClicked = main.mousePressed;
    }

}