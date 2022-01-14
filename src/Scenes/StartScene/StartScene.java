import ky.Asset;
import java.util.ArrayList;
import java.awt.event.KeyEvent;
import ky.Vector2D;

public class StartScene extends Scene {

    Asset startscene; 

    public StartScene(Main main) {
        super(main);
    }

    @Override
    public void initialize(){
        startscene = new Asset("assets/startscene.png", new Vector2D(width*0.5,height*0.5), width, height, 1);
        add(startscene);
        startscene.setVisible(true);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        if (keyCodes.contains(KeyEvent.VK_SPACE)){
            main.setScene(1);
        }
    }
}
