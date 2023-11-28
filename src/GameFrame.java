import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame {


    GamePanel panel;

    //constructor
    // we instantiate this instance withing our constructor
    GameFrame(){
        panel = new GamePanel();
        this.add(panel);
        this.setTitle("Pong Game");
        this.setResizable(false);
        this.setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
