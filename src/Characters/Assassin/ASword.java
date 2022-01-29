import ky.Asset;
import ky.Vector2D;
public class ASword extends DamageEntity{
    public Asset assassinsword;
    
    public ASword(Main main, Vector2D position, int player){
        super(main, position, 60, 20, 2, player, 200);
        //temporary sword pic, not that we need a new sword pic anyway
        assassinsword = new Asset("assets/characters/stabbyrobot/sword.png", new Vector2D(0,0), 4); 

        assassinsword.setVisible(true);
        add(assassinsword);
    }

}

