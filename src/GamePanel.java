import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements  Runnable{  //implements Runnable because we want it to run on a thread

    //static is used if for some reason we had a lot of instances of our GamePanel class then
    //all would share just one variable, they wouldnt each have their own individual game width.
    // final keyword prohibits us from accidentally modifying our game width later on in the program.
    //also final keyword makes it run a little faster and it is a good practice. Also final naming convention
    //is to make all letters in our variable name all uppercase so its easily identifiable.
    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));

    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);

    //BALL diameter
    static final int BALL_DIAMETER = 20;

    //PADDLE DIMENSIONS
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;


    // declaring instances
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle1;
    Paddle paddle2;
    Ball ball;
    Score score;


    //here we instatiate the game
    GamePanel(){
        newPaddles();
        newBall();
        score = new Score(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);


        //this is coming from the Runnable Interface
        gameThread = new Thread(this);
        gameThread.start();
    }

    //in case we need a new ball
    public  void newBall(){

    }
    //in case we need newpaddles
    public  void newPaddles(){

    }
    public void paint(Graphics g){
        //image has dimensions
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        //we call draw method
        draw(graphics);
        g.drawImage(image, 0,0, this);
    }
    public void draw(Graphics g){

    }
    //we move things after each iteration of our game loop
    public void move(){

    }
    public void checkCollision(){

    }
    public void run(){

    }

    //inner class / action listener
    public class AL extends  KeyAdapter{
        public void keyPressed(KeyEvent e){

        }
        public void keyReleased(KeyEvent e){

        }
    }
}
