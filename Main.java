
import javax.swing.*;
import java.awt.EventQueue;

/**
 * Created by Hakanoreaver on 4/6/18.
 */
public class Main extends JFrame{

    public Main() {
        initUI();
    }

    private void initUI() {

        add(new Board());

        setSize(400, 300);
        setResizable(false);

        setTitle("Display a White Circle");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                Main ex = new Main();
                ex.setVisible(true);
            }
        });
    }
}
