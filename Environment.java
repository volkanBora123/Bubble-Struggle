/* Volkan Bora Seki 2021400156 16.04.2023
This class is the most important class. Game is playing here.
2 main functions here, one get the time has passed since from beginning.
Other is the game function. Puts the beginning balls, setting new bar arrow and player, draws background, bar,arrow player
and balls, shot the arrows and check the arrow shot or not. Checks time is run out or not and checks if there are balls or not.
game function returns a booleans which points player won or not.
 */
import java.awt.event.KeyEvent;
public class Environment {
    public static double scaleX = 16;
    public static double yMin = -1;
    public static double yMax = 9;
    public static int canvasWidth = 800;
    public static int canvasHeight = 500;
    public static double gameDuration = 40000;
    static final int pauseDuration = 15;
    public static double getCurrentTime(double time)// a method that returns time passed from the time took as input.
    {return System.currentTimeMillis() - time;}
    public static double scaleY = Environment.yMax - Environment.yMin+1;
    public static boolean game() // runs the game.
    { // adds the balls at the very beginning
        Ball.addNewBall(scaleX/4,0.5,2,0.147);
        Ball.addNegativeBall(scaleX/3,0.5,1,0.1135);
        Ball.addNewBall(scaleX/4,0.5,0,0.07908);
        Player player = new Player();
        player.setX(8);
        double startingTime = System.currentTimeMillis();
        Arrow arrow = new Arrow();
        Bar bar = new Bar();
        while(bar.finish){// every loop draws the game screen again.
            double loopTime = System.currentTimeMillis();
            StdDraw.clear();
            StdDraw.picture(8,-0.5,"bar.png",16,1);
            StdDraw.picture(8,4.5,"background.png",16,9);
            for(Ball ball : Ball.balllist)// if arrow shot the ball, split the level 1,2 ball and destroys it and the arrow.
            {
                if(ball.intersectChecker(arrow.x,arrow.y,0.1,4.5)) {
                    if(ball.level == 0)Ball.balllist.remove(ball);
                    else{
                        Ball.addNewBall(ball.x,ball.y, ball.level-1,Ball.getMaxy(ball.level -1));
                        Ball.addNegativeBall(ball.x,ball.y, ball.level-1,Ball.getMaxy(ball.level-1));
                        Ball.balllist.remove(ball);
                    }
                    arrow.arrowActive = false;
                    arrow.setY(-4.5);
                    break;
                }
            }
            for(Ball ball : Ball.balllist) ball.draw(); // draw every ball
            if(StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && (!arrow.arrowActive)) // shots the arrow
            {
                arrow.setArrowActive(true);
                arrow.setX(player.x);
                arrow.setStartTime(System.currentTimeMillis());
            }
            if(arrow.arrowActive){ // makes arrow up until the ceiling.
              arrow.draw(getCurrentTime(arrow.startTime));
            }
            player.drawPlayer(); // draw player
            bar.draw(Environment.getCurrentTime(startingTime)); // draw ball
            for (Ball ball: Ball.balllist) // if ball touches the player, returns false (player loses) else does nothing.
                if (ball.intersectChecker(player.x,player.y/2,Player.playerWidth/4-0.1,Player.playerHeight/2))
                // I put the -0.1 since the players shape is not proper and this makes more accurate.
                {
                StdDraw.clear();
                StdDraw.picture(8, 4.5, "background.png", 16, 9);
                player.drawPlayer();
                StdDraw.picture(8, -0.5, "bar.png", 16, 1);
                bar.draw(Environment.getCurrentTime(startingTime));
                return false;}
            StdDraw.show();
            if (Ball.balllist.size() == 0) {return true;} // if there is no ball, returns true means player won.
            StdDraw.pause(pauseDuration);
            Ball.setloopTime(getCurrentTime(loopTime)); // determines the time has passed to use at Ball class.
        }
        // if code here, means time is out and there are balls, player has lost. so draws everything but ball and arrow
        // and returns false.
        StdDraw.clear();
        StdDraw.picture(8, 4.5, "background.png", 16, 9);
        player.drawPlayer();
        StdDraw.picture(8, -0.5, "bar.png", 16, 1);
        bar.draw(Environment.getCurrentTime(startingTime));
        StdDraw.show();
        return false;
    }

}
