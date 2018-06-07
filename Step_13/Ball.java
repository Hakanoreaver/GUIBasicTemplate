package Step_13;

public class Ball extends Sprite {

    double angle = 320;
    boolean up = true;
    boolean bounced = true;

    public Ball(int x, int y) {
        super(x,y);
        this.loadImage("image");
        width = 30;
        height = 30;
    }

    public void move() {
        if (x < 0) {
            reflectLeft();
        }
        else if (x > 370 ) {
            reflectRight();
        }

        if (y < 0) {
            reflectDown();
        }

        if (y > 400) setVisible(false);

        double angleInRadians = Math.toRadians(angle);
        x += Math.round(Math.cos(angleInRadians)* 10);
        y += Math.round(Math.sin(angleInRadians)* 10);


    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setUp(boolean up){
        this.up = up;
    }

    public void platformLeft() {
        if (angle < 90) {
            angle = 360 - angle + 20;
        }
        else angle = 360 - angle - 20;
        bounced = true;

    }

    public void platformRight() {
        if (angle < 90) {
            angle = 360 - angle - 20;
        }
        else angle = 360 - angle + 20;
        bounced = true;
    }

    public void platformMiddle() {
        angle = 360 - angle;
        bounced = true;
    }

    public void reflectLeft() {
        if (up) {
            x += 5;
            angle = 360 - (angle - 180);
        }
        else {
            x += 5;
            angle = 180 - angle;
        }
        bounced = false;
    }

    public void reflectRight() {
        if (up) {
            x -= 5;
            angle = 180 + (360 - angle);
        }
        else {
            x -= 5;
            angle = 180 - angle;
        }
        bounced = false;
    }

    public void reflectDown() {
        angle = 360 - angle;
        bounced = false;
    }
}
