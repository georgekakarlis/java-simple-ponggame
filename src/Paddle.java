import java.awt.*;
import java.awt.event.*;

public class Paddle extends Rectangle{

    // id 1 would be for paddle1 and id 2 would be for paddle2
    int id;

    //the paddle should move up and down when we press a button
    int yVelocity;


    //variable holding the speed of the paddles. if we want it faster or slower we change that.
    int speed = 10;

    Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
        this.id=id;
    }


    // we call these methods inside the AL extended class in GamePanel
    public void keyPressed(KeyEvent e){
            switch (id) {
                case 1:
                    // when someone press W it executes this code
                    if (e.getKeyCode() == KeyEvent.VK_W) {
                            setYDirection(-speed);
                            move();
                    }
                    // when someone press S it executes this code
                    if (e.getKeyCode() == KeyEvent.VK_S) {
                        setYDirection(speed);
                        move();
                    }
                    break;
                case 2:
                    // when someone press W it executes this code and goes up or down
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        setYDirection(-speed);
                        move();
                    }
                    // when someone press S it executes this code
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        setYDirection(speed);
                        move();
                    }
                    break;
            }
    }

    //kinda the same with keypress but we switched to 0 from speed.
    public void keyReleased(KeyEvent e) {
        switch(id) {
            case 1:
                if(e.getKeyCode()==KeyEvent.VK_W) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_S) {
                    setYDirection(0);
                }
                break;
            case 2:
                if(e.getKeyCode()==KeyEvent.VK_UP) {
                    setYDirection(0);
                }
                if(e.getKeyCode()==KeyEvent.VK_DOWN) {
                    setYDirection(0);
                }
                break;
        }
    }
    //Y direction because they move only up and down
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }
    public void move(){
            y = y + yVelocity;
    }
    public void draw(Graphics g){
        if (id == 1)
            g.setColor(Color.BLUE);
        else
            g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
