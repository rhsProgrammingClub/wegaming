import java.util.ArrayList;

import ky.AnimationAsset;
import ky.Asset;
import ky.CollisionEntity;
import ky.Vector2D;

public class testCharacter extends Character {

    public testCharacter() {
        super(100, 400, 1000, "characterName", 3);
        maxHP = 2000;
        HP = 2000;
        // setIcon(new Asset("assets/Characters/testcharacter/icon.png", new Vector2D(0, 0), 3));
        /* 
        characterAnimation = new AnimationAsset(images, position, animationTime, layer);
        characterAnimation.setVisible(true);
        add(characterAnimation);
        */
    }

    public void update(double deltaT, ArrayList<Integer> keyCodes) {

    }

    public void onCollision(CollisionEntity collidingEntity) {

    }
    
}
