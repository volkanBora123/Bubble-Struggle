/* Volkan Bora Seki 2021400156 16.04.2023
THis class have the coordinates of player, functions to change players location and draw the player
also there are constants about player.
 */
import java.awt.event.KeyEvent;
public class Player {
    double x;
    double y = playerHeight;
    public void setX(double x) {this.x = x;} // a method that sets player's abscissa to x

    private final int period = 6000;
    public static double playerHeight = 9/8.0;
    public static double playerRatio = 37.0/27.0;
    public static double playerWidth = playerHeight * playerRatio;
    private final double vx = 5*(Environment.scaleX-2*playerWidth)*50/(6.0*period); // the constant speed of player
    public void drawPlayer()// a method that draws player at the canvas
    {
        playerMovementCheck();
        StdDraw.picture(x,y/2,"player_back.png",Player.playerWidth/2,Player.playerHeight);
    }


     private void playerMovementCheck()// a method that change the players position according to key inputs.//
     {
        if(StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)){
            if(x + vx + Player.playerWidth/4 < Environment.scaleX){
                setX(x+ vx);
            }
        }
        if(StdDraw.isKeyPressed(KeyEvent.VK_LEFT)){
            if(x - Player.playerWidth/4 - vx > 0){
                setX(x - vx);
            }
        }
    }
}
