import java.awt.*; //needed for Color
public class paddle {
    //declare instance variables
    private int height, x, y, speed;
    private Color color;
    static final int Pwidth = 15;
    public paddle(int x, int y, int height, int speed, Color color) {
        this.x = x;this.y = y;this.height = height;this.speed = speed;this.color = color;
    }
    public void paint(Graphics g) {
        //paint the rectangle for the paddle
        g.setColor(color);
        g.fillRect(x, y, Pwidth, height);

    }

    public void moveTowards(int moveToY) {
        //find the location of the center of the paddle
        int centerY = y + height / 2;
//determine if we need to move more than the speed away from where we are now
        if (Math.abs(centerY - moveToY) > speed) {
            //if the center of the paddle is too far down
            if (centerY > moveToY) {
                //move the paddle up by the speed
                y -= speed;
            }
            //if the center of the paddle is too far up
            if(centerY < moveToY){
                //move the paddle down by speed
                y += speed;
            }
        }
    }

    public boolean checkCollision(ball b){
        int rightX = x + Pwidth;
        int bottomY = y + height;
        Sound bounceSound = new Sound("iCloud_ping-pong-ball-100074(1).wav");

        //check if the Ball is between the x values
        if(b.getX() > (x - b.getSize()) && b.getX() < rightX){
            //check if Ball is between the y values
            if(b.getY() > y && b.getY() < bottomY){
                bounceSound.play();
                //if we get here, we know the ball and the paddle have collided
                return true;
            }
        }
        //if we get here, one of the checks failed, and the ball has not collided
        return false;

    }
}