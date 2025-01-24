import java.awt.*;

public class ball {
    static final int MAX_SPEED = 7;
    //declare instance variables
    private int x, y, cx, cy, speed, size;
    private Color color;

    //ball constructor assigns values to instance variables
    public ball(int x, int y, int cx, int cy, int speed, Color color, int size) {
        this.x = x;
        this.y = y;
        this.cx = cx;
        this.cy = cy;
        this.speed = speed;
        this.color = color;
        this.size = size;
    }
    public void paint (Graphics g){
        g.setColor(color);

        g.fillOval(x,y,size,size);
    }
    public void animation(){
        x += cx;
        y += cy;
    }
    public void bounceOffEdges(int top, int bottom){
        Sound bounceSound = new Sound("iCloud_ping-pong-ball-100074(1).wav");

        //if the y value is at the bottom of the screen
        if (y > bottom - size){
            reverseY();
            bounceSound.play();
        }
        //if y value is at top of screen
        else if(y < top){
            reverseY();
            bounceSound.play();
        }
    }
    public void reverseX(){cx *= -1;}
    public void reverseY(){cy *= -1;}

    public void increaseSpeed(){
        //make sure current speed is less than max speed before incrementing
        if(speed < MAX_SPEED){
            //increase the speed by one
            speed ++;

            //alternative way to do it
            if(cx > 0){
                cx = speed;
            }
            else if(cx < 0){
                cx = speed * -1;
            }
            if(cy > 0){
                cy = speed;
            }
            else if(cy < 0){
                cy = speed * -1;
            }
        }
    }


    public int getY(){return y;}
    public int getX(){return x;}

    public int getSize() {return size;}

    public void setX(int x) {this.x = x;}

    public void setY(int y) {this.y = y;
    }

    public void setCx(int cx) {this.cx = cx;
    }

    public void setCy(int cy) {this.cy = cy;
    }

    public void setSpeed(int speed) {this.speed = speed;
    }
}
