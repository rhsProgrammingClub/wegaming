package StartScene;
import ky.Asset;
import ky.KYscreen;
import java.awt.image.BufferedImage;
import java.awt.event.KeyEvent;
import ky.Vector2D;

public class StartScene extends KYscreen{

    public StartScene(int width, int height, Boolean resizeable){
        super(width, height, resizeable);
    }

    @Override
    public void keyPressed(int keyCode){}
    
    @Override
    public void keyTyped(int keyCde){}

    @Override
    public void start(){};

    @Override
    public void update(){};

    @Override 
    public void keyReleased(int keyCode) {}

    Asset startscene = new Asset("assets/startscene.png", new Vector2D(0,0), 0);

    public void initialize(){
        startscene.setVisible(true);

        if(getKeyStatus(KeyEvent.VK_SPACE)){
            startscene.setVisible(false);
        }
    }
    //dunno what im doin here just delete if bad
    //uncomment after changing return indexs
    //@Override
    /*public int changescene(){
        return 1;
    }*/
    
}
