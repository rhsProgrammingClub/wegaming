import ky.Vector2D;

public class OtherTestCharacter extends Character {

    public OtherTestCharacter() {
        super(new Vector2D(0, 0), 150, 200, 1000, 3);
        initialize();
    }

    public OtherTestCharacter(Vector2D position){
        super(position, 150, 200, 1000, 3);
        initialize();
    }

    @Override
    public void initialize() {
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
