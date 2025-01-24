import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class game extends JPanel implements MouseMotionListener {
    static final int gamewidth = 640, gameheight = 480;
    private ball gameBall;
    private paddle userPaddle, pcPaddle;
    private int userScore, pcScore;
    private int userMouseY;
    private int bounceCount;
    private String winnerMessage = null; // Added to display the winner

    public game() {
        gameBall = new ball(300, 200, 3, 3, 3, Color.white, 10);
        userPaddle = new paddle(10, 200, 75, 3, Color.white);
        pcPaddle = new paddle(610, 200, 75, 3, Color.white);

        userMouseY = 0;
        userScore = 0;
        pcScore = 0;
        bounceCount = 0;

        addMouseMotionListener(this); // Listen for mouse motion events
    }

    public void reset() {
        try {
            Thread.sleep(1000); // Pause for a second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gameBall.setX(300);
        gameBall.setY(200);
        gameBall.setCx(3);
        gameBall.setCy(3);
        gameBall.setSpeed(3);
        bounceCount = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); // Call superclass method to clear the screen

        // Draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, gamewidth, gameheight);

        // Draw the score
        g.setColor(Color.WHITE);
        g.drawString("Score - User [ " + userScore + " ]   PC [ " + pcScore + " ]", 250, 20);

        // Draw the winner message if the game is over
        if (winnerMessage != null) {
            g.drawString(winnerMessage, gamewidth / 2 - 50, gameheight / 2);
        }

        // Draw the ball and paddles
        gameBall.paint(g);
        userPaddle.paint(g);
        pcPaddle.paint(g);
    }

    private void displayWinner(String message) {
        winnerMessage = message; // Set the winner message
        repaint(); // Trigger repaint to display the message
    }

    public void gameLogic() {
        if (winnerMessage != null) {
            return; // Stop updating logic if the game is over
        }

        // Move the ball one frame
        gameBall.animation();

        gameBall.bounceOffEdges(0, gameheight);
        userPaddle.moveTowards(userMouseY);
        pcPaddle.moveTowards(gameBall.getY());

        if (pcPaddle.checkCollision(gameBall) || userPaddle.checkCollision(gameBall)) {
            gameBall.reverseX(); // Reverse ball direction on collision
            bounceCount++;
        }

        if (bounceCount == 5) {
            bounceCount = 0;
            gameBall.increaseSpeed(); // Increase ball speed every 5 bounces
        }

        if (gameBall.getX() < 0) {
            pcScore++;
            reset();
        } else if (gameBall.getX() > gamewidth) {
            userScore++;
            reset();
        }

        if (userScore == 5 || pcScore == 5) {
            displayWinner(userScore == 5 ? "User Wins!" : "PC Wins!");
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {
        userMouseY = e.getY(); // Update paddle position based on mouse movement
    }
}
