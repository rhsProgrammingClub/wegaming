import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class Sword extends CollisionEntity{

    Asset swordAsset;

    public Sword(Vector2D position, int collisionBoxWidth, int collisionBoxHeight, int layer) {
        super(position, collisionBoxWidth, collisionBoxHeight, layer, "damageEntity");
        swordAsset = new Asset("assets/Characters/testcharacter/sword.png",
                        new Vector2D(30, 0), 120, 40, 5);
        swordAsset.setVisible(true);
        add(swordAsset);

        setVisible(true);
    }
    
}