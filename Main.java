/* Volkan Bora Seki 2021400156 16.04.2023
The main function. Runs the game from environment class. Checks if player won or not. Writes the right message and checks
if player wants to play again. If so, loop starts from the beginning and makes ballist empty list. Else just exits.
 */
import java.awt.*;
import java.awt.event.KeyEvent;
public class Main {
    public static void main(String[] args){
        // arranging canvas.
        StdDraw.setCanvasSize(Environment.canvasWidth,Environment.canvasHeight);
        StdDraw.setXscale(0,Environment.scaleX);
        StdDraw.setYscale(Environment.yMin,Environment.yMax);
        StdDraw.enableDoubleBuffering();
        while (true){
            boolean gameResult = Environment.game(); // plays the game and takes if player won as a boolean.
            // Drawing the common things
            StdDraw.setPenColor(Color.black);
            StdDraw.picture(Environment.scaleX / 2, Environment.scaleY/2.18,"game_screen.png",Environment.scaleX / 3.18,Environment.scaleY/4);
            Font font = new Font("Helvatica",Font.ITALIC,15);
            StdDraw.setFont(font);
            StdDraw.text(Environment.scaleX/2, Environment.scaleY/2.3,"To Replay Click “Y”");
            StdDraw.text(Environment.scaleX/2, Environment.scaleY/2.6,"To Quit Click “N”");
            Font font2 = new Font("Helvatica",Font.BOLD,30);
            StdDraw.setFont(font2);
            // if player won, texts you won, else texts game over
            if (gameResult) {
                StdDraw.text(Environment.scaleX/2,Environment.scaleY/2,"You Won!");
            }
            if (!gameResult) {
                StdDraw.text(Environment.scaleX/2,Environment.scaleY/2,"Game Over!");
            }
            boolean pressed = true;
            StdDraw.show();
            while (pressed) // a while loop makes canvas wait until user press y or n, exits or passing throughout to
                // start another game in loop.
            {
                if (StdDraw.isKeyPressed(KeyEvent.VK_Y)){
                    pressed = false;
                    Ball.balllist.clear();
                }
                if (StdDraw.isKeyPressed(KeyEvent.VK_N)){
                    System.exit(1);
                }
            }
        }
    }
}
