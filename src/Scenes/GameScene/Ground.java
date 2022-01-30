import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class Ground extends CollisionEntity {

    private Asset groundAsset;

    public Ground(double x, double y, int collisionBoxWidth, int collisionBoxHeight, String name) {
        super(x, y, collisionBoxWidth, collisionBoxHeight, 3, name);
         //groundAsset = new Asset("assets/ground.png", new Vector2D(0, 0), 2);
         //groundAsset.setVisible(true);
         //groundAsset.rescale(1920/Main.width);
         //add(groundAsset);
        setVisible(true);

        groundAsset = new Asset("assets/ground.png", new Vector2D(0, 0), collisionBoxWidth, collisionBoxHeight, 0);
        groundAsset.setVisible(true);
        add(groundAsset);
    }

    Asset getAsset() {
        return groundAsset;
    }

}
