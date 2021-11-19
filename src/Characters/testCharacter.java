import java.util.ArrayList;

import ky.AnimationAsset;
import ky.CollisionEntity;

public class testCharacter extends Character {

    public testCharacter() {
        super(100, 400, 1000, "characterName", 3);
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
