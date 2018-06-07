package Step_13;

public class Tile extends Sprite {
    int health;

    public Tile (int x, int y, int health) {
        super(x, y);
        this.health = health;
        width = 50;
        height = 20;
    }

    public int getHealth() {
        return health;
    }

    public void minusHealth() {
        health--;
    }
}
