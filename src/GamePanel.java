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
        random = new Random();
        ball = new Ball((GAME_WIDTH/2)-(BALL_DIAMETER/2),random.nextInt(GAME_HEIGHT-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }
    //in case we need newpaddles
    public  void newPaddles(){
        //we want them to appear on the middle
        paddle1 = new Paddle(0,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2 = new Paddle(GAME_WIDTH-PADDLE_WIDTH,(GAME_HEIGHT/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }
    public void paint(Graphics g){
        //image has dimensions
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        //we call draw method
        draw(graphics);
        g.drawImage(image, 0,0, this); //this = JPanel called GamePanel
    }
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);
        Toolkit.getDefaultToolkit().sync(); // this helps with animation
    }
    //we move things after each iteration of our game loop

    public void move(){
        // after we added the paddle1/2 and ball the paddles and ball are moving really smooth and fast
            paddle1.move();
            paddle2.move();
            ball.move();
    }

    public void checkCollision(){
        //bounce ball off top % bottom window edges
        if (ball.y <= 0){
            ball.setYDirection(-ball.yVelocity);
        }
        // here we also have to consider the ball's diameter because it check the top left corner (Y)
        if(ball.y >= GAME_HEIGHT - BALL_DIAMETER){
            ball.setYDirection(-ball.yVelocity);
        }

         //this method will compare the two objects to see if there is any collision between the 2
            //bounce ball off paddles
        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
                if(ball.yVelocity>0)
                    ball.yVelocity++; //optional for more difficulty
                else
                    ball.yVelocity--;
                ball.setXDirection(ball.xVelocity);
                ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++; //optional for more difficulty
            if(ball.yVelocity>0)
                ball.yVelocity++; //optional for more difficulty
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }

        //this stops paddles at window edges
        if (paddle1.y <= 0)
            paddle1.y = 0;
        //this is if we move down
        if (paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;

        if (paddle2.y <= 0)
            paddle2.y = 0;
        //this is if we move down
        if (paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        //give a player 1 point and creates new paddles & ball
        if(ball.x <=0) {
            score.player2++;
            newPaddles();
            newBall();
            System.out.println("Player 2: "+score.player2);
        }
        if(ball.x >= GAME_WIDTH-BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
            System.out.println("Player 1: "+score.player1);
        }
    }
    public void run(){
        //simple game loop to get 60fps
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        //simple version with true
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
                //System.out.println("It works!!");
            }
        }
    }

    //inner class / action listener
    public class AL extends  KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
