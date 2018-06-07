package Step_13;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.Random;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private boolean ingame;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 40;
    ArrayList<Tile> tiles;
    Platform platform;
    Ball ball;
    int lives = 3;

    public Board() {

        initBoard();
    }

    private void initBoard() {
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        ingame = true;
        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        initGameObjects();

        timer = new Timer(DELAY, this);
        timer.start();
    }

    private void initGameObjects() {
        tiles = new ArrayList<>();
        tiles.add(new Tile(0, 0, 3));
        tiles.add(new Tile(50, 0, 3));
        tiles.add(new Tile(100, 0, 3));
        tiles.add(new Tile(150, 0, 3));
        tiles.add(new Tile(200, 0, 3));
        tiles.add(new Tile(250, 0, 3));
        tiles.add(new Tile(300, 0, 3));
        tiles.add(new Tile(350, 0, 3));

        tiles.add(new Tile(0, 21, 2));
        tiles.add(new Tile(50, 21, 2));
        tiles.add(new Tile(100, 21, 2));
        tiles.add(new Tile(150, 21, 2));
        tiles.add(new Tile(200, 21, 2));
        tiles.add(new Tile(250, 21, 2));
        tiles.add(new Tile(300, 21, 2));
        tiles.add(new Tile(350, 21, 2));

        tiles.add(new Tile(0, 41, 1));
        tiles.add(new Tile(50, 41, 1));
        tiles.add(new Tile(100, 41, 1));
        tiles.add(new Tile(150, 41, 1));
        tiles.add(new Tile(200, 41, 1));
        tiles.add(new Tile(250, 41, 1));
        tiles.add(new Tile(300, 41, 1));
        tiles.add(new Tile(350, 41, 1));


        platform = new Platform(165, 250);
        ball = new Ball(185, 220);
    }

    private void checkCollisions() {
        Rectangle a = new Rectangle(ball.x, ball.y, ball.width, ball.height);
        Rectangle b = new Rectangle(platform.x, platform.y, platform.width, platform.height);

        if (a.intersects(b)) {
            System.out.println(ball.bounced);
            if (!ball.bounced) {
                if ((platform.getX() - ball.getX()) >= -30 && (platform.getX() - ball.getX()) <= 20) {
                    ball.platformLeft();
                } else if ((platform.getX() - ball.getX()) > 20 && (platform.getX() - ball.getX()) <= 50) {
                    ball.platformMiddle();
                } else {
                    ball.platformRight();
                }
                ball.setUp(true);
            }
        }

        for (Tile t : tiles) {

            if(t.isVisible()) {
                Rectangle c = new Rectangle(t.getX(), t.getY(), t.width, t.height);

                if (a.intersects(c)) {
                    Random r = new Random();
                    int Low = 50;
                    int High = 130;
                    int Result = r.nextInt(High - Low) + Low;
                    System.out.println(Result);
                    ball.setAngle(Result);
                    ball.setUp(false);
                    ball.bounced = false;
                    t.minusHealth();
                }

                if (t.health == 0) t.setVisible(false);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            if (tiles.size() == 0) {
                drawWin(g);
            }
            else drawObjects(g);

        } else drawGameOver(g);
        Toolkit.getDefaultToolkit().sync();
    }
    private void drawWin(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("You Win" , 170, 140);
        timer.stop();
    }
    private void drawGameOver(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("Game Over" , 160, 140);
    }
    private void drawObjects(Graphics g) {

        for (Tile t: tiles) {
            if (t.isVisible()) {
                switch (t.getHealth()) {
                    case 1:
                        g.setColor(Color.YELLOW);
                        break;
                    case 2:
                        g.setColor(Color.BLUE);
                        break;
                    case 3:
                        g.setColor(Color.RED);
                        break;
                }
                g.fillRect(t.getX(), t.getY(), t.width, t.height);
                g.setColor(Color.BLACK);
                g.drawRect(t.getX(), t.getY(), t.width, t.height);
            }

        }

        g.setColor(Color.WHITE);
        g.fillRect(platform.getX(), platform.getY(), platform.width, platform.height);

        g.setColor(Color.GREEN);
        g.fillOval(ball.getX(), ball.getY(), ball.width, ball.height);

        g.setColor(Color.WHITE);
        g.drawString("Lives : " + lives, 330, 240);
    }
    private void checkHealth() {
        for (int i = 0; i < tiles.size(); i++) {
            Tile t = tiles.get(i);
            if(!t.isVisible()){
                tiles.remove(i);
            }
        }

        if (ball.isVisible()){
            ball.move();
        }
        else{
            lives--;
            ball.x = 185;
            ball.y = 220;
            ball.setAngle(270);
            ball.setVisible(true);
        }

        if (lives == 0) ingame = false;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        checkHealth();
        checkCollisions();
        platform.move();
        repaint();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            platform.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            platform.keyPressed(e);
        }

        public void keyHeld() {

        }


    }
}