import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class Ground extends CollisionEntity {

    private Asset groundAsset;

    public Ground(double x, double y, int collisionBoxWidth, int collisionBoxHeight, String name) {
        super(x, y, collisionBoxWidth, collisionBoxHeight, 2, name);

        groundAsset = new Asset("assets/misc/ground.png", new Vector2D(0, 0), collisionBoxWidth, collisionBoxHeight, 0);
        groundAsset.setVisible(true);
        add(groundAsset);

        setVisible(true);
    }

    Asset getAsset() {
        return groundAsset;
    }

}
