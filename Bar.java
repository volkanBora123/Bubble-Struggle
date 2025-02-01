/* Volkan Bora Seki 2021400156 16.04.2023
this class have the function that draw the ball and the boolean says if there is more time or not.
 */
import java.awt.Color;
public class Bar {
    boolean finish = true; // a boolean when time passed it will be false.
    void draw(double recentTime) // a method that take the time passed input and determine the colour of the bar and draw it.
    {
        Color tempColor = new Color(225,0,0);
        if(225-225*(int)(recentTime)/40000 > 0) tempColor = new Color(225,225-225*(int)(recentTime)/40000,0);
        StdDraw.setPenColor(tempColor);
        StdDraw.picture(8,-0.5,"bar.png",16,1);
        if(8-8*recentTime/Environment.gameDuration > 0){
            StdDraw.filledRectangle((Environment.scaleX - (16 * recentTime / Environment.gameDuration)) / 2, -0.5, 8 - 8 * recentTime / Environment.gameDuration, 0.25);
        }else finish = false;
        StdDraw.show();
    }
}
