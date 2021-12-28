import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;
public class ASword extends DamageEntity{
    public Asset assassinsword;
    
    public ASword(Vector2D position, int player){
        super(position, 120, 40, 2, player, 200);
        //temporary sword pic, not that we need a new sword pic anyway
        assassinsword = new Asset("assets/characters/stabbyrobot/sword.png", new Vector2D(0,0), 4); 

        assassinsword.setVisible(true);
        add(assassinsword);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
    }
}

