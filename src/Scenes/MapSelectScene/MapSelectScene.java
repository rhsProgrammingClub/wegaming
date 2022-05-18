import ky.Vector2D;
import ky.Asset;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import ky.Text;
import java.awt.Color;
import java.awt.Font;

public class MapSelectScene extends Scene{
    
    int curselect = 0;
    static int finalselect = 0;
    double finalsizes[] = {0.2, 0.4, 0.6, 0.8};
    Text[] displayname = new Text[4];
    Asset[] mapassets = new Asset[4];
    Asset selectionCursor;
    Asset selectionConfirm;
    Asset mapPreview;
    final int width = 4;
    boolean left = true;
    boolean right = true;


    public MapSelectScene(Main main){
        super(main);
    }

    @Override
    public void initialize(){
    
        //values will increase as more maps are made
        for(int i = 0; i < width; i++){
            mapassets[i] = new Asset("assets/test.png", 
            new Vector2D(Main.width * finalsizes[i], Main.height * 0.2), 
            200, 100,
            0);
            mapassets[i].setVisible(true);
            add(mapassets[i]);

            displayname[i] = new Text("N/A",
            new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
            Color.black,
            new Vector2D(Main.width * 0.85, Main.height*0.85),
            300,
            30,
            5);
            displayname[i].setVisible(false);
            add(displayname[i]);

        }

        mapassets[0].setImage("assets/maps/default.png");
        mapassets[1].setImage("assets/maps/platformer.png");
        mapassets[2].setImage("assets/maps/staircase.png");
        mapassets[3].setImage("assets/maps/reversestaircase.png");

        displayname[0].setText("Default");
        displayname[1].setText("Platformer");
        displayname[2].setText("Staircase");
        displayname[3].setText("Reverse Staircase");

        selectionCursor = new Asset("assets/misc/map_select_cursor.png", new Vector2D(0, 0), 1);
        selectionCursor.setVisible(true);
        selectionCursor.setPos(mapassets[curselect].getPos());
        add(selectionCursor);

        selectionConfirm = new Asset("assets/misc/map_select_confirm.png", new Vector2D(0, 0), 1);
        selectionConfirm.setVisible(true);
        selectionConfirm.setPos(mapassets[finalselect].getX(), mapassets[finalselect].getY() + 200);
        add(selectionConfirm);
        
        displayname[curselect].setVisible(true);
    
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes){
        if(keyCodes.contains(KeyEvent.VK_ESCAPE)){
            main.setScene(1);
        }

        if(!keyCodes.contains(KeyEvent.VK_LEFT)){
            left = true;
        }
        if(!keyCodes.contains(KeyEvent.VK_RIGHT)){
            right = true;
        }

        if(keyCodes.contains(KeyEvent.VK_LEFT) && left){
            left = false;
            displayname[curselect].setVisible(false);
            if(curselect-1 >= 0){
                curselect--;  
            }else{
                curselect = width-1;
            }
            displayname[curselect].setVisible(true);
            selectionCursor.setPos(mapassets[curselect].getPos());
        }

        if(keyCodes.contains(KeyEvent.VK_RIGHT) && right){
            right = false;
            displayname[curselect].setVisible(false);
            if(curselect+1 < width){
                curselect++;
            }else{
                curselect = 0;
            }
            displayname[curselect].setVisible(true);
            selectionCursor.setPos(mapassets[curselect].getPos());
        }

        if(keyCodes.contains(KeyEvent.VK_ENTER)){
            finalselect = curselect;
            selectionConfirm.setPos(mapassets[finalselect].getX(), mapassets[finalselect].getY() + 200);
        }
        
    }

    static public int getmap(){
        return finalselect;
    }


}
