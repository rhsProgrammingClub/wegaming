import ky.Asset;
import ky.Text;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import ky.Vector2D;

public class StartScene extends Scene {

    Asset startscene; 
    Text space;
    Boolean blinking;
    double blinkuptime;

    public StartScene(Main main) {
        super(main);
    }

    @Override
    public void initialize(){
        startscene = new Asset("assets/packingsomesmack.png", new Vector2D(width*0.5,height*0.5+50), width, height, 1);
        add(startscene);
        startscene.setVisible(true);
        space = new Text("Press Space to Start", 
        new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
        Color.black,
        new Vector2D(Main.width * 0.5, Main.height * 0.95),
        400,
        50,
        5);
        space.setVisible(true);
        add(space);
        blinking = true;
        blinkuptime = 0.5;
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.contains(KeyEvent.VK_SPACE)){
            main.setScene(1);
        }
        blinkuptime-=deltaT;
        if(blinking){
            if(blinkuptime <= 0){
                blinking = false;
                blinkuptime = 0.5;
                space.setVisible(false);
            }
        }else if(!blinking){
            if(blinkuptime <= 0){
                blinking = true;
                blinkuptime = 0.5;
                space.setVisible(true);
            }
        }
    }
}
