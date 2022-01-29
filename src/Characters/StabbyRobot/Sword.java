import ky.Asset;
import ky.Vector2D;

public class Sword extends DamageEntity {

    public Asset swordAsset;

    public Sword(Main main, Vector2D position, int player, double damage) {
        super(main, position, 60, 20, 2, player, damage, 500);
        swordAsset = new Asset(
                new String[] { "assets/characters/stabbyrobot/sword.png",
                        "assets/characters/stabbyrobot/sword_boosted.png",
                        "assets/characters/stabbyrobot/sword_ult.png" },
                new Vector2D(0, 0), 60, 20, 0);

        swordAsset.setVisible(true);
        add(swordAsset);
        setBreaks(false);
        setVisible(true);
    }
}