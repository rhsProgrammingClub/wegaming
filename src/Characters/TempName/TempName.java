import java.util.ArrayList;

import ky.Asset;
import ky.Vector2D;

public class TempName extends Character {

    private Flame flame;

    public TempName(Vector2D position, Main main) {
        super(position, 300, 300, 1500, main);
    }

    public TempName(Main main) {
        super(new Vector2D(0, 0), 300, 300, 1500, main);
    }

    @Override
    public void update(double deltaT, ArrayList<Integer> keyCodes) {
        super.update(deltaT, keyCodes);
        flame.setPos(new Vector2D(getX() + 100 * direction.getValue(), getY()));
    }

    @Override
    public void initialize() {
        setDefense(0.4);
        characterAsset = new Asset(new String[] {
                "assets/characters/spaceship/spaceship_normal.png",
                "assets/characters/spaceship/spaceship_enraged.png"
        }, new Vector2D(0, 0), 3);

        characterAsset.setVisible(true);
        add(characterAsset);

        flame = new Flame(new Vector2D(0, 0), 40, 20, 4, getPlayer());
        flame.setVisible(true);
        add(flame);
    }

    @Override
    protected void basicAttack() {

    }

    @Override
    protected void basicAbility() {

    }

    @Override
    protected void ultimate() {

    }

}
