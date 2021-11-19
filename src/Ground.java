import ky.AnimationAsset;
import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class Ground extends CollisionEntity {

    private Asset groundAsset;

    public Ground(double x, double y, int collisionBoxWidth, int collisionBoxHeight) {
        super(x, y, collisionBoxWidth, collisionBoxHeight, 3, "ground");
        // groundAsset = new Asset("assets/ground.png", new Vector2D(0, 0), 2);
        // groundAsset.setVisible(true);
        // groundAsset.rescale(1920/Main.width);
        // add(groundAsset);
        setVisible(true);
    }

    Asset getAsset () {
        return groundAsset;
    }

}
