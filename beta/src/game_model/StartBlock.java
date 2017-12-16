package game_model;

public class StartBlock extends Block {

	public StartBlock(int x, int y) {
        super(x, y);
        isSolid = false;
        boundingShape = new Hitbox(x * 32, y * 32, 32, 32);
	}
}
