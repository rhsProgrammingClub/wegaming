import ky.Vector2D;
import ky.Asset;
import java.util.ArrayList;
import java.awt.event.KeyEvent;

import ky.Text;
import java.awt.Color;
import java.awt.Font;

public class MapSelectScene extends Scene{
    
    int curselect [] = {0,0};
    static int finalselect[] = {0,0};
    double finalsizes[] = {0.2, 0.4, 0.6, 0.8};
    Text[][] displayname = new Text[4][4];
    Asset[][] mapassets = new Asset[4][4];
    final int width = 4;
    final int height = 1;
    boolean left = true;
    boolean right = true;
    boolean down = true;
    boolean up = true;


    public MapSelectScene(Main main){
        super(main);
    }

    @Override
    public void initialize(){
        

        //values will increase as more maps are made
        for(int x = 0; x < height; x++){
            for(int y = 0; y < width; y++){
                mapassets[x][y] = new Asset("assets/test.png", 
                new Vector2D(Main.width * finalsizes[y], Main.height * 0.2), 
                200, 200,
                5);
                mapassets[x][y].setVisible(true);
                add(mapassets[x][y]);

                displayname[x][y] = new Text("N/A",
                new Font("assets/JetBrainsMono-Bold.ttf", Font.BOLD, 20),
                Color.black,
                new Vector2D(Main.width * 0.85, Main.height*0.85),
                300,
                30,
                5);
                displayname[x][y].setVisible(false);
                add(displayname[x][y]);

            }
        }

        displayname[0][0].setText("Default");
        displayname[0][1].setText("Platformer");
        displayname[0][2].setText("Staircase");
        displayname[0][3].setText("Reverse Staircase");
        mapassets[0][0].rescale(0.5);
        displayname[0][0].setVisible(true);
    
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
        if(!keyCodes.contains(KeyEvent.VK_DOWN)){
            down = true;
        }
        if(!keyCodes.contains(KeyEvent.VK_UP)){
            up = true;
        }

        if(keyCodes.contains(KeyEvent.VK_LEFT) && left){
            left = false;
            displayname[curselect[0]][curselect[1]].setVisible(false);
            mapassets[curselect[0]][curselect[1]].rescale(2);
            if(curselect[1]-1 >= 0){
                curselect[1]--;  
            }else{
                curselect[1] = width-1;
            }
            mapassets[curselect[0]][curselect[1]].rescale(0.5);
            displayname[curselect[0]][curselect[1]].setVisible(true);
            //displayname.setText(mapnames[curselect[0]][curselect[1]]);
        }
        if(keyCodes.contains(KeyEvent.VK_RIGHT) && right){
            right = false;
            displayname[curselect[0]][curselect[1]].setVisible(false);
            mapassets[curselect[0]][curselect[1]].rescale(2);
            if(curselect[1]+1 < width){
                curselect[1]++;
            }else{
                curselect[1] = 0;
            }
            mapassets[curselect[0]][curselect[1]].rescale(0.5);
            displayname[curselect[0]][curselect[1]].setVisible(true);
            //displayname.setText(mapnames[curselect[0]][curselect[1]]);
        }
        if(keyCodes.contains(KeyEvent.VK_DOWN) && down){
            down = false;
            displayname[curselect[0]][curselect[1]].setVisible(false);
            mapassets[curselect[0]][curselect[1]].rescale(2);
            if(curselect[0]+1 < height){
                curselect[0]++;
            }else{
                curselect[0] = 0;
            }
            mapassets[curselect[0]][curselect[1]].rescale(0.5);
            displayname[curselect[0]][curselect[1]].setVisible(true);
            //displayname.setText(mapnames[curselect[0]][curselect[1]]);
        }
        if(keyCodes.contains(KeyEvent.VK_UP) && up){
            up = false;
            displayname[curselect[0]][curselect[1]].setVisible(false);
            mapassets[curselect[0]][curselect[1]].rescale(2);
            if(curselect[0]-1 >= 0){
                curselect[0]--;
            }else{
                curselect[0] = height-1;
            }
            mapassets[curselect[0]][curselect[1]].rescale(0.5);
            displayname[curselect[0]][curselect[1]].setVisible(true);
            //displayname.setText(mapnames[curselect[0]][curselect[1]]);
        }

        if(keyCodes.contains(KeyEvent.VK_ENTER)){
            finalselect[0] = curselect[0];
            finalselect[1] = curselect[1];
        }
        
    }

    static public int[] getmap(){
        return finalselect;
    }


}
