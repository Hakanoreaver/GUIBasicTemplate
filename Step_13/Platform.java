package Step_13;

import java.awt.event.KeyEvent;
public class Platform extends Sprite {

    private int move;

    public Platform(int x, int y) {
        super(x,y);
        this.loadImage("image");
        width = 70;
        height = 10;
    }

    public void move() {

        if (x < 0) {
            move = 0;
            x = 0;
        }
        else if (x > 322) {
            move = 0;
            x = 322;
        }
        else x += move;
    };

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT ){
            move = 10;
        }

        if (key == KeyEvent.VK_LEFT ){
            move = -10;
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT ){
            move = 0;
        }

        if (key == KeyEvent.VK_LEFT ){
            move = 0;
        }
    }

}
