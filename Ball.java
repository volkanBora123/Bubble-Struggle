/* Volkan Bora Seki 2021400156 16.04.2023
This class have some constants, static ballist and functions that determines ball's properties, draw the ball and add or
put out elements from the balllists.
 */
import java.awt.*;
import java.util.ArrayList;
public class Ball {
    double x,y,radius,vStarty;
    static double loopTime = 36; // a double variable that changes at every end of loop that measured time has passed.

    public static void setloopTime(double time) {
        loopTime = time;
    }

    double vx =  ((Environment.scaleX - radius*2)*50*2/3)/(15000.0);
    double gravitionalConstant = 0.000003 * Environment.scaleY;
    int level;
    double maxHeightZeroLevel = Player.playerHeight *1.4;
    double maxheight;
    private static final double minPossibleRadius = Environment.scaleY * 0.0175;
    private void setLevelandRadius(int level)  // determines the radius, max height ball can reach and level according to input.
    {
        this.level = level;
        this.radius = minPossibleRadius * Math.pow(2,level);
        this.maxheight = maxHeightZeroLevel * Math.pow(1.4,level);
    }

    public static double getMaxy(int level) // I got these constants from throwing the balls from max height and printed when
    // they hit the floor.
    {
        if (level == 0) return 0.10001132307705331;
        if (level == 1) return 0.12818215384632076;
        if (level == 2) return 0.16912246153868177;
        return 2;
    }
        private double getmMaxVy() // the same function but turns negative values.
        {
        if (level == 0) return -0.10001132307705331 ;
        if (level == 1) return -0.12818215384632076;
        if (level == 2) return -0.16912246153868177;
        return -2;
    }
    public void setVx(double vx) {
        this.vx = vx;
    }//sets ball's speed's x component equal to input.

    private void setX(double x) {
        this.x = x;
    }//sets ball's coordinate's x component equal to input.

    private void setY(double y) {
        this.y = y;
    } //sets ball's coordinate's y component equal to input.
    public boolean intersectChecker(double xrec,double yrec,double halfwidth,double halfheight) // a function that checks
    // whether a circle and a rectangle intersects or not
    {
        double xDistance = Math.min(Math.abs(x-xrec-halfwidth),Math.abs(x-xrec+halfwidth));
        double yDistance = Math.min(Math.abs(y-yrec-halfheight),Math.abs(y-yrec+halfheight));
        double Distance = 100000;
        if(x > xrec - halfwidth && x < xrec + halfwidth){
            if(y > yrec + yDistance || y < yrec - yDistance) {
                Distance = yDistance;
            }else Distance = 0;
        }
        if (x <= xrec - halfwidth || x >= xrec + halfwidth){
            if( y < yrec + halfheight && y > yrec - halfheight )Distance = xDistance;
            if( y >= yrec + halfheight || y <= yrec - halfheight)Distance = Math.sqrt(xDistance * xDistance + yDistance * yDistance);
        }
        return Distance < radius;
    }

    public static ArrayList<Ball> balllist = new ArrayList<>(); // a static ArrayList that contains all balls.
    public void draw()//updates the coordinate and draws the ball.
    {
     if(x +radius +  vx > Environment.scaleX) vx = -1*vx;
     if(x - radius +vx < 0) vx = -1*vx;
     x += vx;
     vStarty -= 2.95384615385*gravitionalConstant*loopTime; // acceleration times time passed is the definition of rate of change of velocity,since there
        // are sudden jumps, I equaled the times with the video ones and find the constant =  2.95384615385.
     if(y-radius+vStarty<0) {
         vStarty = -1 * getmMaxVy();
     }
     StdDraw.setPenColor(Color.white);
     StdDraw.setPenRadius(0.001);

     y += vStarty;
     StdDraw.picture(x,y,"ball.png",radius*2,radius*2);
     StdDraw.circle(x,y,radius);
    }
    public void setvStarty(double vStarty) {this.vStarty = vStarty;} // vStartY is the start velocity but code do it
    // all calculations on it.
    public static void addNewBall(double x, double y, int level, double vStarty)// adds new "level" level ball at (x,y) with
    // velocity y equals vStartY.
    {
        Ball newball = new Ball();
        newball.setX(x);
        newball.setY(y);
        newball.setvStarty(vStarty);
        newball.setLevelandRadius(level);
        balllist.add(newball);
    }

    public static double getMaxHeight(int level) {
        return Player.playerHeight * 1.4 *Math.pow(1.75,level);
    } // public function returns maxHeight at "level" level

    public static void addNegativeBall(double x, double y, int level, double vStarty)//add a new ball goes left at beginning.
    {
        Ball newball = new Ball();
        newball.setX(x);
        newball.setY(y);
        newball.setvStarty(vStarty);
        newball.setLevelandRadius(level);
        newball.setVx(-newball.vx);
        balllist.add(newball);
    }
}
