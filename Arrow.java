/* Volkan Bora Seki 2021400156 16.04.2023
This class has some funtions that determines arrows activeness, coordinates and draw it.
also time which the arrow is shot is stored here.
 */
public class Arrow {

    double x;
    double y = -4.5;
    boolean arrowActive = false;
    double startTime;

    public void setStartTime(double startTime){this.startTime = startTime;}// a method that sets arrow's throwing time

    public void setY(double y) {this.y = y;}// a method that sets arrow's coordinate's y component
    public void draw(double time)// a method that draws the canvas the arrow. Arrow starts from y = -4.5 point and
    // goes up according to time passed.bar and player will be drawn on the arrow so arrow will show like starts from 0
    // and goes up
    {
        double currentY = y + time / 1500;
        setY(currentY);
        if(currentY <= 4.5 ) {
            StdDraw.picture(x,currentY,"arrow.png",0.2,9);
        }
        else {
            setArrowActive(false);
            setY(-4.5);
        }
    }

    public void setArrowActive(boolean arrowActive) {this.arrowActive = arrowActive;}// a method that determines
    // if arrow is active or not.
    public void setX(double x) {this.x = x;} // a method that determines arrow's coordinate's x component.
}
